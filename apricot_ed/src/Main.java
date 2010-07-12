import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mchange.util.PasswordUtils;

/**
 * 
 */
/**
 * @author Administrator
 */
public class Main {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream("table.txt")));
		String l = reader.readLine();
		l = reader.readLine();
		System.out.println("[");
		StringBuffer cols = new StringBuffer();
		while (l != null) {
			System.out.print("      {");
			String[] as = l.split("	");
			System.out.print("label:\"");
			System.out.print(as[0]);
			System.out.print("\",");
			System.out.print("name:\"");
			System.out.print(as[1].toLowerCase());
			System.out.print("\"");

			if ("true".equalsIgnoreCase(as[7])) {
				System.out.print(",allowBlank:false");
			}
			if (as[0].indexOf("ID") >= 0 || as[0].indexOf("ฑ๊สถ") >= 0) {
				System.out.print(",hide:\"");
				System.out.print("all");
				System.out.print("\"");
			}
			System.out.print("}");
			cols.append(as[1].toLowerCase()).append(",");
			l = reader.readLine();
			if (l != null)
				System.out.println(",");
		}
		System.out.println("\n   ];");
		System.out.println();
		System.out.println(cols.toString());
		
		System.out.println(PasswordUtils.deCodeValue("V3PbI+qMbdfVQmGEiUpTwQ=="));

	}
}
