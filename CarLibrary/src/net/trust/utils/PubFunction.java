package net.trust.utils;

public class PubFunction {
	/**
	 *  null转化为string
	 *  摘自厦门忠诚度系统的公共类
	 * @param Object
	 * @return
	 */
	public static String getNulltoStr(Object o) {
		String str = "";
		try{
			if (o != null)
				str = o.toString();
		}catch(Exception e){
			return "";
		}
		return str;
	}
	/**
	 *  判断是否不为空
	 * @param boolean
	 * @return
	 */
	public static boolean bIsEmpty(String str) {
		boolean bisNull = false;
		if (str == null || str == "" || str.trim().equals(""))
			bisNull = true;
		return bisNull;
	}
}
