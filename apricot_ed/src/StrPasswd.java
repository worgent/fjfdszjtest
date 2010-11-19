/**
* Copyright (C) qzgf, 2010
*
* License        :Apache License 2.0
* Project        :MapleFetion
* Package        :net.message
* File	         :StrPasswd.java
* Written by     :fjfdszj
* Created Date   :Sep 3, 2010
* Purpose        :功能描述

======================================

* Modifyer by    :fjfdszj
* Update Date    :Sep 3, 2010
* Purpose        :描述

*/

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     StrPasswd.java
 *
 */
public class StrPasswd {
	static String comkey="ABC";
	/**
	 * 
	 * Purpose      : 解密
	 */
    public static String UnchainPassword(String FSource)
    {
    	String Key=comkey;
        String str = "";
        int length = Key.length();
        if (FSource.length() == 2)
        {
            return str;
        }
        if (length == 0)
        {
            Key = "XiaoLing";
        }
        int num2 = 0;
        int num3 =HexToInt(FSource.substring(0, 2));
       // System.out.println("还原随机变量字符串:"+FSource.substring(0, 2)+",整形时:"+num3);
        int num4 = FSource.length() - 2;
        int index = 0;
        byte[] bytes = new byte[num4/2];
        for (int i = 2; i <= num4; i += 2)
        {
            int num7 = HexToInt(FSource.substring(i, i+2));
            if (num2 < (length - 1)){
                num2++;
            }else{
                num2 = 0;
            }
            int num8 = num7 ^Key.getBytes()[num2];
            if (num8 <= num3){
                num8 = (0xff + num8) - num3;
            }else{
                num8 -= num3;
            }
            bytes[index] = (byte) (0xff & num8);
            index++;
            num3 = num7;
        }
        return new String(bytes).trim();
    }
    
    /**
     * 
     * Purpose      : 加密
     * @param FSource
     * @return
     */
    // B0678F42F4      
    // D072FE31A_Kq  137DF12C4  4AFC21D58 
    public static String EncryptPassword(String FSource)
    {
    	String Key=comkey;
        int length = Key.length();
        if (length == 0)
        {
            Key = "XiaoLing";
        }
        int num2 =(int)(Math.random()*0x100);
        String str = IntToHex(num2);
        //System.out.println("随机变量字符串:"+str+",整形时:"+num2);
        int num4 = 0;
        byte[] bytes = FSource.getBytes();
        //System.out.println("预编码字符串:"+FSource+",二进制数组:"+bytes);
        for(byte num5 : bytes)
        {
            int num3 = (num5 + num2) % 0xff;
            if (num4 < (length - 1))
            {
                num4++;
            }
            else
            {
                num4 = 0;
            }
            //key是改变随机变量.
            num3 ^= Key.getBytes()[num4];
            str = str + IntToHex(num3);
            num2 = num3;
        }
        return str;
    }

    //Integer.toString(i, radix)
    private static String IntToHex(int num){
        String sResult = "";
        /*
        while( num > 0 ){
                int m = num % 16;
                if ( m < 10 )
                    sResult = ( char )( '0' + m ) + sResult;
                else
                    sResult = ( char )( 'A' + m - 10 ) + sResult;
                num = num / 16;
        };
        */
        sResult=Integer.toString(num, 16).toUpperCase();
        //强制转化为两位数
        if(sResult.length()==1){
        	sResult="0"+sResult;
        }
        return sResult;
    }
    
    //Integer.parseInt(s, radix)
    private static int HexToInt(String num){
        int sResult = 0;
        /*
        int length=num.length();
        for(int i=0;i<length;i++){
        	//sResult=sResult*16+Integer.parseInt(num.charAt(i));
        	sResult=sResult*16+Integer.parseInt(num,"0x");
        }
        */
        sResult=Integer.parseInt(num, 16);
        return sResult;
    }
    
	//不支持中文加密
	public static void main(String[] args) {
		String test="admin";
		//admin 加密码应该为 "36FE020025FA"
		try {
			//System.out.println(HexToInt("ff"));
			System.out.println(IntToHex(6));
			test=StrPasswd.EncryptPassword(test);
			System.out.println(test);
			test=StrPasswd.UnchainPassword(test);
			System.out.println(test);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
