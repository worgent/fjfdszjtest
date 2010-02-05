package com.qzgf.utils.export;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qzgf.utils.PubFunction;


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
			
			out.println("<table border='1' cellpadding='1' cellspacing='0'>");
			out.println("	<thead>");
			out.println("		<tr>");
			for (i = 0; i<colName.length; i++){
				out.println("		<td>"+colName[i]+"</td>");
			}
			out.println("		</tr>");
			out.println("	</thead>");
			out.println("	<tbody>");
			
			while (rs.next()){
				out.println("	<tr>");
				for (i=0; i<fields.length; i++){
					out.println("	<td>"+PubFunction.getNulltoStr(rs.getString(fields[i]))+"</td>");
				}
				out.println("	</tr>");
			}
			
			out.println("	</tbody>");
			out.println("</table>");
			
		} catch (SQLException e) {
			if (log.isErrorEnabled())
				log.error(e);
			return false;
		}
		return true;
	}
}
