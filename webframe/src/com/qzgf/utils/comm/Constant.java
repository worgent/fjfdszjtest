package com.qzgf.utils.comm;

/**
 * 
 * @author lsr
 *
 */
public class Constant {
	public static final String VSERION = "1.0.0";
	public static String ROOTPATH = "d://safefile/";//  "/var/soft/photo/""d://safefile/"
	/*在tomcat的conf下的server.xml做下映射如下
	 <Host name="localhost" appBase="webapps"
       unpackWARs="true" autoDeploy="true"
       xmlValidation="false" xmlNamespaceAware="false">
     关键此行 <Context path="/photo" docBase="d://safefile/"/> 
      </Host>
      */
	
	
	public static String SERVLET_MAPPING = "*.do";
	public static String IMG_SMALL_FILEPREFIX = "_Small";
	public static final String CHARSET = "UTF-8";

}
