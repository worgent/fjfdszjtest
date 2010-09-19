package com.qzgf.utils.export;
import java.sql.ResultSet;
import java.util.HashMap;

import java.io.*;

public interface ImportIface {
	public HashMap readLine() throws IOException;
	public void close();
}
