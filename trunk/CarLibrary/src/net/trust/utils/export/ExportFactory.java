package net.trust.utils.export;
/**
 * 一个单例模式的工厂类，用于创建导出的具体类，有ExportToExcel,ExportXml等。
 * @author chenf
 * */
public class ExportFactory {
	//private  ExportIface export = null;
	/**
	 * 如果把ExportIface这个类设计成单例模式，可能存在问题，
	 * 个人设想是这样的，如果是单例模式那么如果同时有两个请求，一个请求是需要导出
	 * excel文件格式，另一个请求是要求导出其他模式时，两者是否存在冲突呢？
	 * 实践：实际上不存在上面两者的冲突，似乎是web服务器会根据线程来控制该单例的，有时间需要研究
	 * 一下。
	 * 单例模式
	 * @author chenf
	 * @param type 导出类型 xls为excel文件，xml为Xml格式文件，pdf....
	 * @return ExportIface 具体的实现类。
	 * */
	public static ExportIface getInstance(String type){
		ExportIface export=null;
		if(type==null){
			return null;
		}else if(type.equals("xls")){
			if(export==null || !(export instanceof ExportToPageExcel)){
				export = new ExportToPageExcel();
			}
		}else if(type.equals("xml")){
			if(export==null || !(export instanceof ExportToXml)){
				export = new ExportToXml();
			}
		}else if(type.equals("pdf")){
			
		}
		return export;
	}
	
}
