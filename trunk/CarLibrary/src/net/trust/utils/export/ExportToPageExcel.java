package net.trust.utils.export;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.trust.utils.PubFunction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ExportToPageExcel implements ExportIface{
	private Log log = LogFactory.getLog(ExportToPageExcel.class);
	
	public boolean export(PrintWriter out, ResultSet rs, String sheetName, String[] fields, String[] colName){
		if (out == null)
			return false;
		if (rs == null) {
			return false;
		}
		
		try {
			int i = 0;
			out.println("<meta http-equiv='Content-Type' content='text/html; charset=GBK'>");
			out.println("<table border='1' cellpadding='1' cellspacing='0'>");
			//页头
//			out.println("	<thead>");
//			out.println("		<tr>");
//			out.println("		<td COLSPAN="+colName.length+" align=center>"+colName[i]+"</td>");
//			out.println("		</tr>");
//			out.println("	</thead>");
			//表头
			out.println("	<thead>");
			out.println("		<tr>");
			for (i = 0; i<colName.length; i++){
				out.println("		<td>"+colName[i]+"</td>");
			}
			out.println("		</tr>");
			out.println("	</thead>");
			out.println("	<tbody>");
			//数据信息
			while (rs.next()){
				out.println("	<tr>");
				
				//处理单据号(第一位,默认是长数据),超过15位,自动转化为科学计数法表示的.数据丢失问题.
				out.println("	<td>"+PubFunction.getNulltoStr(rs.getString(fields[0]))+"&nbsp;</td>");
				//非你第一行数据
				for (i=1; i<fields.length; i++){
						out.println("	<td>"+PubFunction.getNulltoStr(rs.getString(fields[i]))+"</td>");
				}
				
				out.println("	</tr>");
			}
			out.println("	</tbody>");
			//页尾
//			out.println("	<tfoot  align='center'>");
//			out.println("		<tr>");
//			out.println("			<td COLSPAN="+colName.length+" align=center>"+colName[0]+"</td>");
//			out.println("		</tr>");
//			out.println("	</tfoot>");
			
			out.println("</table>");
			
		} catch (SQLException e) {
			if (log.isErrorEnabled())
				log.error(e);
			return false;
		}
		return true;
	}
}
