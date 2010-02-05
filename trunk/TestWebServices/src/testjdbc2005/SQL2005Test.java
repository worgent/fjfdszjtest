package testjdbc2005;

import java.sql.*;
import javax.swing.JOptionPane;

public class SQL2005Test {
	public static void main(String[] args) {

		ResultSet result;
		String dbURL;

//		String name = JOptionPane.showInputDialog("输入SQL Server 2005登录名：");
//		String pass = JOptionPane.showInputDialog("输入SQL Server 2005密码");
//		String server = JOptionPane.showInputDialog("输入要登录的数据库服务器的地址");
		String name = "sa";
		String pass ="123@123";
		String server ="127.0.0.1:1434";//"FJFDSZJ-PC\\SQLEXPRESS:1434";
		String instance = "";//tempdb;

		if (instance.trim().equals("")) {
			dbURL = "jdbc:sqlserver://" + server+";DatabaseName=tempdb;user=sa;password=123@123;";
		}

		else {
			dbURL = "jdbc:sqlserver://" + server + "\\" + instance;
		}

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			Connection con = DriverManager.getConnection(dbURL, name, pass);

			System.out.println("建立连接成功!");

			Statement stat = con.createStatement();

			result = stat
					.executeQuery("select * from myuser");

			System.out
					.println("----------------------------------------------------------");

			int i = 0;
			while (result.next()) {
				i++;
				System.out.println(String.valueOf(i) + "  " + result.getString(1)
						+ "  \t" + result.getString(2));

			}
			System.out
					.println("----------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}