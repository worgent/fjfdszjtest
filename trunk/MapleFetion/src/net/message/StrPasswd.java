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
package net.message;

/**
 * Purpose      : 说明
 *
 * @author fjfdszj
 * @see     StrPasswd.java
 *
 */
public class StrPasswd {
	/**
	 * Purpose      : 说明
	 */
    public static String UnchainPassword(String FSource)
    {
    	String Key="";
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
        int num4 = FSource.length() - 2;
        int index = 0;
        byte[] bytes = new byte[num4];
        for (int i = 2; i <= num4; i += 2)
        {
            int num7 = HexToInt(FSource.substring(i, i+2));
            if (num2 < (length - 1))
            {
                num2++;
            }
            else
            {
                num2 = 0;
            }
            int num8 = num7 ^ Key.charAt(num2);
            if (num8 <= num3)
            {
                num8 = (0xff + num8) - num3;
            }
            else
            {
                num8 -= num3;
            }
            bytes[index] = (byte) (0xff & num8);
            index++;
            num3 = num7;
        }
        return bytes.toString();
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
        }*/
        sResult=Integer.toString(num, 10).toUpperCase();
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
    
    public static String EncryptPassword(String FSource)
    {
    	String Key="";
        int length = Key.length();
        if (length == 0)
        {
            Key = "XiaoLing";
        }
        int num2 =(int)(Math.random()*0x100);
        String str = IntToHex(num2);
        int num4 = 0;
        byte[] bytes = FSource.getBytes();
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
            num3 ^= Key.charAt(num4);
            str = str + IntToHex(num3);
            num2 = num3;
        }
        return str;
    }
	
	public static void main(String[] args) {
		String test="admin";
		try {
			System.out.println(HexToInt("0A"));
			System.out.println(IntToHex(16));
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
