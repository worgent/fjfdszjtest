package cn.org.rapid_framework.generator;


/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */

public class GeneratorMain { 
	/**
	 * 请直接修改以下代码调用不同的方法以执行相关生成任务.
	 */
	public static void main(String[] args) throws Exception {
		GeneratorProperties.setProperty("basepackage", "com.qzgf.application.report.reportpattern");
		GeneratorProperties.setProperty("namespace", "report");
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
		
// 		T_DailyWork  T_ToMonitor  T_Declare  
// 		t_dailywork  t_tomonitor  t_declare  
		
//  	g.deleteOutRootDir();							//删除生成器的输出目录
//		g.deleteByTable("t_dailywork", "temwo"); //删除生成的文件
    	g.generateByTable("t_reportpattern","temwo");	//通过数据库表生成文件user_info需要改成你自己的表名,template为模板的根目录
		//g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");
		

		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
