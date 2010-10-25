package cn.org.rapid_framework.generator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

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
		
		//多表,配置basepackage,namespace
		//cn.org.rapid_framework.generator.GeneratorControl gg;
/*		GeneratorProperties.setProperty("basepackage", "com.womobile.workduty");
		GeneratorProperties.setProperty("namespace", "workduty");
		
		GeneratorFacade g = new GeneratorFacade();
//		g.printAllTableNames();				//打印数据库中的表名称
		
 //   	g.deleteOutRootDir();							//删除生成器的输出目录
		//workduty,collection,feedback
    	g.generateByTable("workduty","template");	//通过数据库表生成文件user_info需要改成你自己的表名,template为模板的根目录
	*/	
		
		
		//生成文档

		String fileName="G:/Java/womobileex/generator/src/cn/org/rapid_framework/generator/tab.txt";
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		String line = null;
		//必要参数设置
		
		GeneratorFacade g = new GeneratorFacade();
		while ((line = reader.readLine()) != null) {
			//读取参数
			String[] lines=line.split(";");// tablename lines[1], tablecode lines[0]
			//
			GeneratorProperties.setProperty("mytablecode", lines[0]);
			GeneratorProperties.setProperty("mytablename", lines[1]);
			g.generateByTable(lines[0],"template/doc");	//通过数据库表生成文件user_info需要改成你自己的表名,template为模板的根目录
			break;
		}
		//g.generateByAllTable("template");	//自动搜索数据库中的所有表并生成文件,template为模板的根目录
//		g.generateByClass(Blog.class,"template_clazz");
		
//		g.deleteByTable("table_name", "template"); //删除生成的文件
		//打开文件夹
		Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}
}
