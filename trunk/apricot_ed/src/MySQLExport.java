import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import com.apricot.eating.util.DataUtil;
/**
 * 
 */
/**
 * @author Administrator
 */
public class MySQLExport {
	private class Column {
		private String name;
		private String type;

		public String getName() {
			return name;
		}

		public String getType() {
			return type;
		}

		public Column(String name, String type) {
			super();
			this.name = name;
			this.type = type;
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException, Exception {
		// TODO Auto-generated method stub
		new MySQLExport().export("E:/program/apricot_ed/src/tabs.txt");
	}
	private Connection conn = null;

	/**
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public MySQLExport() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated constructor stub
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/eating_db", "root", "root");
	}

	public void export(String fileName) throws IOException, Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
		OutputStream out = new FileOutputStream("e:/data.sql");
		String line = null;
		while ((line = reader.readLine()) != null) {
			List l = export1(line);
			out.write(("/* 开始表" + line + "*/\n").getBytes());
			for (Iterator sqls = l.iterator(); sqls.hasNext();) {
				String s = (String) sqls.next();
				if (s.length() > 0) {
					out.write(s.getBytes());
				}
			}
			out.write("/*结束*/\n".getBytes());
		}
		conn.close();
	}

	/**
	 * 等到表列
	 * 
	 * @param tabname
	 * @return
	 * @throws Exception
	 */
	private List getColumns(String tabname) throws Exception {
		PreparedStatement stmt = conn.prepareStatement("desc " + tabname);
		ResultSet res = stmt.executeQuery();
		List a = new ArrayList();
		while (res.next()) {
			a.add(new Column(res.getString(1), res.getString(2)));
		}
		res.close();
		stmt.close();
		return a;
	}

	private List export1(String tabname) throws Exception {
		List cols = getColumns(tabname);
		List sqls = new ArrayList();
		PreparedStatement stmt = conn.prepareStatement("select * from " + tabname);
		ResultSet res = stmt.executeQuery();
		while (res.next()) {
			StringBuffer sql = new StringBuffer();
			StringBuffer data = new StringBuffer();
			sql.append("insert into ").append(tabname);
			sql.append("(");
			for (Iterator cls = cols.iterator(); cls.hasNext();) {
				Column c = (Column) cls.next();
				Object v = res.getObject(c.name);
				if (v == null)
					continue;
				if (v instanceof Date) {
					data.append(DataUtil.format((Date) v, "yyyy-MM-dd HH:mm:ss"));
				} else if (v instanceof String) {
					data.append("'" + DataUtil.toGBK(v.toString()) + "'");
				} else {
					data.append(v.toString());
				}
				data.append(",");
				sql.append(c.name).append(",");
			}
			if (sql.toString().endsWith(",")) {
				sql = new StringBuffer(sql.toString().substring(0, sql.length() - 1));
				data = new StringBuffer(data.toString().substring(0, data.length() - 1));
			}
			sqls.add(sql.append(") values(").append(data.toString()).append(");\n").toString());
		}
		res.close();
		stmt.close();
		return sqls;
	}
}
