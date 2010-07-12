import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 
 */

/**
 * @author xudahu
 * 
 */
public class UpdateZIP {
    private String projectDir = "F:/program/apricot_ed";
    private String version = "";
    private String build = projectDir + "/build";

    /**
     * 
     */
    public UpdateZIP() {
	// TODO Auto-generated constructor stub
    }

    private void delete(File f) {
	if (!f.exists())
	    return;
	if (f.isDirectory()) {
	    File[] list = f.listFiles();
	    for (int i = 0; i < list.length; i++) {
		delete(list[i]);
	    }
	}

	f.delete();
    }

    private void zip(String versionNo) throws IOException {
	this.version = new StringBuffer(this.projectDir).append("/version/").append(versionNo).toString();
	File b = new File(this.build);
	delete(b);
	b.mkdirs();
	//
	BufferedReader modifyList = new BufferedReader(new InputStreamReader(new FileInputStream(un(this.version,
		"modify_list.txt"))));
	String line = null;
	while ((line = modifyList.readLine()) != null) {
	    if (line.trim().length() == 0)
		continue;
	    line=line.trim();
	    System.out.println(line);
	    copy(un(this.projectDir, line), un(this.build, line));

	}
	copy(un(this.version, "readme.txt"), un(this.build, "readme.txt"));

	modifyList.close();

    }

    private void copy(String s, String d) {
	FileInputStream source = null;
	FileOutputStream target = null;
	try {
	    new File(d).getParentFile().mkdirs();
	    source = new FileInputStream(s);
	    target = new FileOutputStream(d);
	    byte[] b = new byte[1024];
	    int len = 0;
	    while ((len = source.read(b)) > 0) {
		target.write(b, 0, len);
	    }
	} catch (Exception e) {
           System.out.println("文件拷贝错误:"+s);
	}

	try {
	    if (source != null)
		source.close();
	} catch (Exception ex) {
	}
	;
	try {
	    if (target != null)
		target.close();
	} catch (Exception ex) {
	}
	;
    }

    private String un(String p, String a) {
	if (!a.startsWith("/"))
	    a = "/" + a;
	return p + a;
    }

    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
	// TODO Auto-generated method stub
	new UpdateZIP().zip("100426");
	System.out.println("考贝完成");
    }

}
