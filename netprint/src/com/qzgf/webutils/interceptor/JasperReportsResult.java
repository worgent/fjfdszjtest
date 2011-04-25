
package com.qzgf.webutils.interceptor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

import com.ibatis.common.logging.Log;
import com.ibatis.common.logging.LogFactory;
import com.opensymphony.util.TextUtils;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.webwork.dispatcher.WebWorkResultSupport;
import com.opensymphony.webwork.views.jasperreports.JasperReportConstants;
import com.opensymphony.webwork.views.jasperreports.OgnlValueStackDataSource;
import com.opensymphony.webwork.views.jasperreports.OgnlValueStackShadowMap;
import com.opensymphony.xwork.ActionInvocation;
import com.opensymphony.xwork.util.OgnlValueStack;
import com.qzgf.application.netprint.clientmsg.action.ClientmsgAction;


public class JasperReportsResult extends WebWorkResultSupport implements JasperReportConstants {
//StrutsResultSupport  WebWorkResultSupport
    private static final long serialVersionUID = -2523174799621182907L;

    private final static Log LOG = LogFactory.getLog(ClientmsgAction.class);

    protected String dataSource;
    protected String format;
    protected String documentName;
    protected String contentDisposition;
    protected String delimiter;
    protected String imageServletUrl = "/images/";
    protected String timeZone;

    

    /**
     * Names a report parameters map stack value, allowing
     * additional report parameters from the action.
     */
    protected String reportParameters;

    /**
     * Names an exporter parameters map stack value,
     * allowing the use of custom export parameters.
     */
    protected String exportParameters;

    /**
     * Default ctor.
     */
    public JasperReportsResult() {
        super();
    }

    /**
     * Default ctor with location.
     *
     * @param location Result location.
     */
    public JasperReportsResult(String location) {
        //super(location);
        super();
    }

    public String getImageServletUrl() {
        return imageServletUrl;
    }

    public void setImageServletUrl(final String imageServletUrl) {
        this.imageServletUrl = imageServletUrl;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * set time zone id
     *
     * @param timeZone
     */
    public void setTimeZone(final String timeZone) {
        this.timeZone = timeZone;
    }

    public String getReportParameters() {
        return reportParameters;
    }

    public void setReportParameters(String reportParameters) {
        this.reportParameters = reportParameters;
    }

    public String getExportParameters() {
        return exportParameters;
    }

    public void setExportParameters(String exportParameters) {
        this.exportParameters = exportParameters;
    }

    protected void doExecute(String finalLocation, ActionInvocation invocation) throws Exception {
        // Will throw a runtime exception if no "datasource" property. TODO Best place for that is...?
        initializeProperties(invocation);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Creating JasperReport for dataSource = " + dataSource + ", format = " + format);
        }

        HttpServletRequest request = (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) invocation.getInvocationContext().get(ServletActionContext.HTTP_RESPONSE);

        // Handle IE special case: it sends a "contype" request first.
        // TODO Set content type to config settings?
        if ("contype".equals(request.getHeader("User-Agent"))) {
            try {
                response.setContentType("application/pdf");
                response.setContentLength(0);

                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.close();
            } catch (IOException e) {
                LOG.error("Error writing report output", e);
                throw new ServletException(e.getMessage(), e);
            }
            return;
        }

        // Construct the data source for the report.
        OgnlValueStack  stack = invocation.getStack();
        OgnlValueStackDataSource stackDataSource = new OgnlValueStackDataSource(stack, dataSource);

        // Determine the directory that the report file is in and set the reportDirectory parameter
        // For WW 2.1.7:
        //  ServletContext servletContext = ((ServletConfig) invocation.getInvocationContext().get(ServletActionContext.SERVLET_CONFIG)).getServletContext();
        //  ServletContext serrvletContext servletContext = (ServletContext) invocation.getInvocationContext().get(ServletActionContext.SERVLET_CONTEXT);

        ServletContext servletContext = ((ServletConfig) invocation.getInvocationContext().get(ServletActionContext.SERVLET_CONFIG)).getServletContext();
        String systemId = servletContext.getRealPath(finalLocation);
        Map parameters = new OgnlValueStackShadowMap(stack);
        File directory = new File(systemId.substring(0, systemId.lastIndexOf(File.separator)));
        parameters.put("reportDirectory", directory);
        parameters.put(JRParameter.REPORT_LOCALE, invocation.getInvocationContext().getLocale());

        // put timezone in jasper report parameter
        if (timeZone != null) {
            timeZone = conditionalParse(timeZone, invocation);
            final TimeZone tz = TimeZone.getTimeZone(timeZone);
            if (tz != null) {
                // put the report time zone
                parameters.put(JRParameter.REPORT_TIME_ZONE, tz);
            }
        }

        // Add any report parameters from action to param map.
        Map reportParams = (Map) stack.findValue(reportParameters);
        if (reportParams != null) {
            LOG.debug("Found report parameters; adding to parameters...");
            parameters.putAll(reportParams);
        }

        byte[] output;
        JasperPrint jasperPrint;

        // Fill the report and produce a print object
        try {
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(systemId);
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, stackDataSource);
            
            //JasperPrintManager.printReport(jasperPrint, true);//2008-08-25 直接打印,不用预览PDF直接打印 true为弹出打印机选择.false为直接打印.
           // JasperPrintManager.printReport(jasperPrint, true);//2008-08-25 直接打印,不用预览PDF直接打印 true为弹出打印机选择.false为直接打印.
        } catch (JRException e) {
            LOG.error("Error building report for uri " + systemId, e);
            throw new ServletException(e.getMessage(), e);
        }

        response.setContentType("application/octet-stream"); 
        ServletOutputStream ouputStream = response.getOutputStream(); 

        ObjectOutputStream oos = new ObjectOutputStream(ouputStream); 
        oos.writeObject(jasperPrint);//将JasperPrint对象写入对象输出流中 
        oos.flush(); 
        oos.close(); 

		ouputStream.flush();
		ouputStream.close();

    }

    /**
     * Writes report bytes to response output stream.
     *
     * @param response Current response.
     * @param output   Report bytes to write.
     * @throws ServletException on stream IOException.
     */
    private void writeReport(HttpServletResponse response, byte[] output) throws ServletException {
        ServletOutputStream outputStream = null;
        //InputStream inputStream=null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(output);
            
            ByteArrayInputStream BAIS=null; 
            ObjectInputStream OIS=null;
            BAIS=new ByteArrayInputStream(output);
            //inputStream=new InputStream(BAIS);
            try {
				JasperPrintManager.printReport(BAIS, false);
			} catch (JRException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            //===============
           // JasperPrintManager.printReport(response.get, withPrintDialog)
            //====================
            outputStream.flush();
        } catch (IOException e) {
            LOG.error("Error writing report output", e);
            throw new ServletException(e.getMessage(), e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                LOG.error("Error closing report output stream", e);
                throw new ServletException(e.getMessage(), e);
            }
        }
    }

    /**
     * Sets up result properties, parsing etc.
     *
     * @param invocation Current invocation.
     * @throws Exception on initialization error.
     */
    private void initializeProperties(ActionInvocation invocation) throws Exception {
        if (dataSource == null) {
            String message = "No dataSource specified...";
            LOG.error(message);
            throw new RuntimeException(message);
        }
        dataSource = conditionalParse(dataSource, invocation);

        format = conditionalParse(format, invocation);
        if (!TextUtils.stringSet(format)) {
            format = FORMAT_PDF;
        }

        if (contentDisposition != null) {
            contentDisposition = conditionalParse(contentDisposition, invocation);
        }

        if (documentName != null) {
            documentName = conditionalParse(documentName, invocation);
        }

        reportParameters = conditionalParse(reportParameters, invocation);
        exportParameters = conditionalParse(exportParameters, invocation);
    }

    /**
     * Run a Jasper report to CSV format and put the results in a byte array
     *
     * @param jasperPrint The Print object to render as CSV
     * @param exporter    The exporter to use to export the report
     * @return A CSV formatted report
     * @throws net.sf.jasperreports.engine.JRException
     *          If there is a problem running the report
     */
    private byte[] exportReportToBytes(JasperPrint jasperPrint, JRExporter exporter) throws JRException {
        byte[] output;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);
        if (delimiter != null) {
            exporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, delimiter);
        }

        exporter.exportReport();

        output = baos.toByteArray();

        return output;
    }

}
