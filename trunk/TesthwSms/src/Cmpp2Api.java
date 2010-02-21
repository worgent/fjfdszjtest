
//引入相关有用的包文件
import com.commerceware.cmpp.*;//东软提供的软件包

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

//时间：20081205，修改短信测试用例
public class Cmpp2Api
{
    //发送短信相关参数说明
    String strIcpId;		//用户名
    String strSvcType;   
    //服务类型  0x00000022l 不合法的服务类型
    byte fee_type;
    //01计费用户号免费。02按条计费信息费。03对“计费用户号码”按包月收取信息费
    //04：对“计费用户号码”的信息费封顶 05：对“计费用户号码”的收费是由SP实现
    byte info_fee;
    //info fee 4 integer 资费代码
    //以分为单位，值范围0-999999
    byte proto_id;//协议号
    byte msg_mode; //设置提交请求包中消息模式域的内容
   // msg_mode：消息模式:0-不需要状态报告， 1-需要状态报告，2-控制类型消息
    byte priority;
    //priority 1 integer 优先级别
    //值范围0-9，优先级9最高
    byte validate[];
    //validity period 17 or 1 c-octet string 消息存活有效期
    byte schedule[];
    //schedule 17 or 1 c-octet string 消息定时发送时间
    byte fee_utype;
    //计费用户类型：0对目的终端计费，1对源终端计费，2对sp计费。3：对指定的计费用户收费
    String strFeeUser;
    //fee user var.
    //max 21 c-dec string 计费用户，如果计费用户类型为3，则本参数必须被设置；如果计费用户类型为其它值，此参数无效
    String strSrcAddr;
    //接收短信用户手机显示来电号码
    byte du_count;
    byte data_coding;   //编码
    String strServerIP;  //服务器ip
    int iServerPort;    //服务器端口。
    String strIcpAuth;  //密码
    byte iLoginType;
    //用户登录类型  smias 系统支持三种登录类型：   0--发送型   1--接收型  2--收发型
    byte iVersion;
    //双方协商的版本号(高位4bit表示主版本号,低位4bit表示次版本号) i/f version 1 integer 接口版本
    //其值的计算方法为： 主版本号*16+副版本号  例如：版本1.2的值为0x12
    int iTimestamp;
    //其值为从1970-01-01 gmt时间到sp此次login的时间所流逝的秒数
    Returndata rData;
    static String propFileName = "MsgProp.txt";//配置文件路径

    /**
     * 从propFileName文件读取配置信息
     */
    public void ProConfig()
    {
        strIcpId = "";
        strSvcType = "";
        fee_type = 2;
        info_fee = 8;
        proto_id = 1;
        msg_mode = 0;
        priority = 9;
        validate = new byte[10];
        schedule = new byte[2];
        fee_utype = 2;
        strFeeUser = "";
        strSrcAddr = "";
        du_count = 1;
        data_coding = 15;
        strServerIP = "211.138.155.249";
        iServerPort = 7890;
        strIcpAuth = "fjy4z";
        iLoginType = 2;
        iVersion = 18;
        iTimestamp = 0x3b47fcbd;
        rData = null;
        rData = new Returndata();
        Properties properties = new Properties();//从文档属性。
        try
        {
        	properties.load(getClass().getResourceAsStream(propFileName));
            strIcpId= properties.getProperty("ICPID", "913461");
            strSvcType = properties.getProperty("SVCTYPE", "04545");
            fee_type = Byte.parseByte(properties.getProperty("FEETYPE", "2"));
            info_fee = Byte.parseByte(properties.getProperty("INFOFEE", "8"));
            proto_id = Byte.parseByte(properties.getProperty("PROTOID", "1"));
            msg_mode = Byte.parseByte(properties.getProperty("MSGMODE", "0"));
            priority = Byte.parseByte(properties.getProperty("PRIORITY", "9"));
            validate[0] = Byte.parseByte(properties.getProperty("VALIDATE", "0"));
            schedule[0] = Byte.parseByte(properties.getProperty("SCHEDULE", "0"));
            fee_utype = Byte.parseByte(properties.getProperty("FEEUTYPE", "2"));
            strFeeUser = properties.getProperty("FEEUSER", "0591185");
            strSrcAddr = properties.getProperty("SRCADDR", "0591185");
            du_count = Byte.parseByte(properties.getProperty("DUCOUNT", "1"));
            data_coding = Byte.parseByte(properties.getProperty("DATACODING", "15"));
            strServerIP = properties.getProperty("SERVERIP", "211.138.155.249");
            iServerPort = Integer.parseInt(properties.getProperty("SERVERPORT", "7890"));
            strIcpAuth = properties.getProperty("ICPAUTH", "fjy4z");
            iLoginType = Byte.parseByte(properties.getProperty("LOGINTYPE", "2"));
            iVersion = 18;
            iTimestamp = Integer.parseInt(properties.getProperty("TIMESTAMP", "994573501"));
        }
        catch(IOException ioexception)
        {
            System.out.println("\u5F02\u5E38\uFF1A\u8BFB\u53D6\u5177\u4F53\u914D\u7F6E\u6587\u4EF6\u5C5E\u6027\u5931\u8D25\uFF01");
        } 	
    }
    /**
     * 构造方法
     */  
    public Cmpp2Api()
    {
    	ProConfig();
    }
    /**
     * 发送短信
     * 参数说明:
     * @param content:短信发送内容
     * @param arraylist手机号码数组(可群发)
     * @return 返回值类型，封装了返回值（值与相关信息）
     */
    public Returndata SendMessage(String content, ArrayList arraylist)
    {
        String s1 = content;
        int i = 0;
        rData.initData();//初始化返回数据信息
        CMPP cmpp = new CMPP();
//      byte abyte0[] = new byte[150];
//      byte abyte2[] = new byte[40];
//      byte abyte3[] = new byte[10];
        int k = 0;
        cmppe_submit cmppe_submit1 = new cmppe_submit();                        //提交
//      cmppe_submit_result cmppe_submit_result1 = new cmppe_submit_result();   //查询
//      cmppe_cancel cmppe_cancel1 = new cmppe_cancel();     					//取消
        
        //在存储用户名
        byte abyte4[] = new byte[10];
        for(int l = 0; l < strIcpId.length(); l++)
            abyte4[l] = toHex(strIcpId.charAt(l));                              //将用户名转化为hex存储。
        abyte4[strIcpId.length()] = 0;
        
        //服务类别
        byte abyte5[] = new byte[6];											//svcType编码保存
        for(int i1 = 0; i1 < strSvcType.length(); i1++)
            abyte5[i1] = toHex(strSvcType.charAt(i1));
        abyte5[strSvcType.length()] = 0;
        
        //短信接入号
        byte abyte6[] = new byte[20];											
        for(int j1 = 0; j1 < strFeeUser.length(); j1++)
            abyte6[j1] = toHex(strFeeUser.charAt(j1));
        abyte6[strFeeUser.length()] = 0;
        //源地址
        byte abyte7[] = new byte[12];
        for(int k1 = 0; k1 < strSrcAddr.length(); k1++)
            abyte7[k1] = toHex(strSrcAddr.charAt(k1));							//srcaddr源址。
        abyte7[strSrcAddr.length()] = 0;
        
        //手机号码，存储于二维数组
        int l1 = arraylist.size();
        //String s2 = "";
        byte abyte8[][] = new byte[l1 + 1][20];
        for(int i2 = 0; i2 < l1; i2++)
        {
            String s3 = (String)arraylist.get(i2);
            int j2 = Integer.parseInt(s3.substring(0, 3));
            //截取手机号的前三位,首先满足11位，同时前3位在135~139之间
            if(s3.length() == 11 && j2 >= 135 && j2 <= 139)
            {
                for(int k2 = 0; k2 < s3.length(); k2++)
                    abyte8[i2][k2] = toHex(s3.charAt(k2));//将手机号每一位转化为16制。

                abyte8[i2][s3.length()] = 0;//同时以0为字符数组的结尾。
                k++;
            }
        }

        du_count = (byte)k;//手机号数目
        byte byte0 = 70;//最大内容字符数
        //对手机发送内容进行处理
        while(s1.length() > 0) 
        {
            i++;
            byte abyte1[];
            try
            {
            	//两个变量说明content存储当前好发送的信息，而s1是大于70位的字符。
            	content = s1;
                if(content.length() > byte0)
                {
                	content = content.substring(0, byte0);
                    s1 = s1.substring(byte0);
                } else
                {
                    s1 = "";
                }
                abyte1 = content.getBytes("GB2312");
                content = new String(abyte1);
            }
            catch(Exception exception)
            {
                System.out.println("strMsg\u8F6C\u6362\u683C\u5F0F\u5931\u8D25\uFF01" + content);
                rData.setReturnData(-1, "strMsg\u8F6C\u6362\u683C\u5F0F\u5931\u8D25\uFF01" + content);
                return rData;
            }
            int l2 = abyte1.length;
//            cmppe_result cmppe_result1 = new cmppe_result();
//            cmppe_deliver_result cmppe_deliver_result1 = new cmppe_deliver_result();
            try
            {
            	//建立连接，发送短信
                conn_desc conn_desc1 = new conn_desc();
                cmpp.cmpp_connect_to_ismg(strServerIP, iServerPort, conn_desc1);
                System.out.println("Connected ISMG");
                
                //测试处理
                //cmpp.cmpp_login(conn_desc1, "444504", "888888", 2, 18, 20081203);
                //重新计算邮戳(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
//              Calendar time=Calendar.getInstance();
//              int second=time.get(Calendar.SECOND);
//              int minute=time.get(Calendar.MINUTE);
//              int hour=time.get(Calendar.HOUR_OF_DAY);
//              int day =time.get(Calendar.DAY_OF_MONTH);
//              int month=time.get(Calendar.MONTH)+1;
                
                //System.out.println((new SimpleDateFormat("MMddHHmmss")).format(new Date()));
                //System.out.println(Integer.getInteger((new SimpleDateFormat("MMddHHmmss")).format(new Date())));
                //iTimestamp=Integer.getInteger((new SimpleDateFormat("MMddHHmmss")).format(new Date()));
                String temp=(new SimpleDateFormat("MMddHHmmss")).format(new Date()).toString();
                iTimestamp=Integer.valueOf(temp);
                //iTimestamp=((new Date()).getTimezoneOffset());
                //iTimestamp=((new Date()).getDate());
                
                
                cmpp.cmpp_login(conn_desc1, strIcpId, strIcpAuth, iLoginType, iVersion, iTimestamp);//建立连接
                readPa(conn_desc1);																	//应答信息
                //设置发送基本信息
                cmppe_submit1.set_icpid(abyte4);
                cmppe_submit1.set_svctype(abyte5);
                cmppe_submit1.set_feetype(fee_type);
                cmppe_submit1.set_infofee(info_fee);
                cmppe_submit1.set_protoid(proto_id);
                cmppe_submit1.set_msgmode(msg_mode);
                cmppe_submit1.set_priority(priority);
                cmppe_submit1.set_validate(validate);
                cmppe_submit1.set_schedule(schedule);
                cmppe_submit1.set_feeutype(fee_utype);
                cmppe_submit1.set_feeuser(abyte6);
                cmppe_submit1.set_srcaddr(abyte7);
                cmppe_submit1.set_dstaddr(abyte8);
                cmppe_submit1.set_ducount(du_count);
                cmppe_submit1.set_msg(data_coding, l2, abyte1);
                
                
                //提交发送信息
                int j = cmpp.cmpp_submit(conn_desc1, cmppe_submit1);
                System.out.println("\u53D1\u9001\u77ED\u4FE1 SM Seq = " + j);
                readPa(conn_desc1);									//读取信息
                // msg_mode：消息模式:0-不需要状态报告， 1-需要状态报告，2-控制类型消息
                if(msg_mode == 1)
                    readPa(conn_desc1);
                
                
                
                //断开发送信息
                cmpp.cmpp_logout(conn_desc1);
                readPa(conn_desc1);
                
                //cmpp.cmpp_disconnect_from_ismg(conn_desc1);
               
            }
            catch(Exception exception1)
            {
                System.out.println(exception1.getMessage());
                rData.setReturnData(-2, exception1.getMessage());
                exception1.printStackTrace();
                System.out.println("have a exception");
                try
                {
                    System.in.read();
                }
                catch(Exception exception2) { }
                return rData;
            }
        }
        if(rData.iReturnCode == 0)
            rData.iReturnCode = i;
        return rData;
    }
   
    //操作数据库
    public Returndata SendMsg(String content,ArrayList mobileList)
    {
    	rData = SendMessage(content, mobileList); //发送短信。
    	
        return rData;
    }
    //操作数据库
    public Returndata RecMsg(String content,ArrayList mobileList)
    {
    	rData = SendMessage(content, mobileList); //发送短信。
    	
        return rData;
    }
    /**
     * 国通短信发送处理
     * @param s
     * @param s1
     * @param i
     * @param s2
     * @param j
     * @param k
     * @param s3
     * @param l
     * @return
     */
    public Returndata SendYwMsg(String content, String mobilearrary, int i, String s2, int j, int k, String s3, 
            int l)
    {
    	
        ArrayList arraylist = new ArrayList();			//存储手机数组
        CutStrings cutstrings = new CutStrings();       //分隔操作类
        cutstrings.initData(mobilearrary, ",");					 //参数说明以分隔符","对字符串s1进行分隔。
        String as[] = cutstrings.getMembers();           //得到分隔后的数组
        int i1 = cutstrings.getCountToken();
        int k1 = 0;
        for(int l1 = 0; l1 < i1; l1++)
        {
            String s4 = as[l1];
            int i2 = Integer.parseInt(s4.substring(0, 3));
            if(s4.length() == 11 && i2 >= 135 && i2 <= 139)
            {
                arraylist.add(as[l1]);
                k1++;
            }
        }

        rData = SendMessage(content, arraylist);
        //对数据库进行操作
//        if(rData.iReturnCode > 0)
//        {
//            int j1 = k1 * rData.iReturnCode;
//            Dboperation dboperation = new Dboperation();
//            String s5 = "insert into dxfsb(DXXLH,DXNR,JSDXHM,FSMK,FSR,FSSJ,SFYQHF,ZDZFHF,ZDZFJSHM,DXFSSL,FSFS) values(YWPHONEMSGBH.nextval,'" + s + "','" + s1 + "'," + Integer.toString(i) + ",'" + s2 + "',sysdate," + Integer.toString(j) + "," + Integer.toString(k) + ",'" + s3 + "'," + j1 + "," + l + ")";
//            rData = dboperation.executeUpdate(s5);
//        }
        return rData;
    }


     /**
      * 修改密码
      * 
      */
    public int ModifyPass(String s)
        throws IOException
    {
        int i = 0;
        Properties properties = new Properties();
        try
        {
        	properties.load(getClass().getResourceAsStream(propFileName));
            properties.setProperty("ICPAUTH", s);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            i = -1;
            System.out.println("\u5F02\u5E38\uFF1AModifyPass\u5931\u8D25\uFF01");
        }
        return i;
    }
    
    /**
     * 接收短信信息
     */
    public void ReceMsg() {
		// 建立连接，发送短信
		CMPP cmpp = new CMPP();
		conn_desc conn_desc1 = new conn_desc();
		try {
			cmpp.cmpp_connect_to_ismg(strServerIP, iServerPort, conn_desc1);
			
            //重新计算邮戳
            String temp=(new SimpleDateFormat("MMddHHmmss")).format(new Date()).toString();
            iTimestamp=Integer.valueOf(temp);
            cmpp.cmpp_login(conn_desc1, strIcpId, strIcpAuth, iLoginType, iVersion, iTimestamp);//建立连接
            readPa(conn_desc1);	

			while (true) {
				Object obj = null;
				try {
					cmppe_result cmppe_result1 = cmpp.readResPack(conn_desc1);// 读取包信息。
					switch (cmppe_result1.pack_id) {
					case 5: // '\005'
						System.out.println("------------deliver---------: STAT = 0");
						cmppe_deliver_result cmppe_deliver_result1 = (cmppe_deliver_result) cmppe_result1;
						if (cmppe_deliver_result1.status_rpt == 1)
							System.out.println("Rpt status = "
									+ cmppe_deliver_result1.status_from_rpt);
						cmpp.cmpp_send_deliver_resp(conn_desc1,
								cmppe_deliver_result1.seq,
								((cmppe_result) (cmppe_deliver_result1)).stat);
						break;
					default:
						System.out.println("---------Error packet-----------");
						break;
					}
				} catch (UnknownPackException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (DeliverFailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Connected ISMG");
	}
 
    /**
	 * 读取信息,应答信息
	 * 
	 * @param conn_desc1
	 *            服务得到的连接
	 */
    public void readPa(conn_desc conn_desc1)
    {
        CMPP cmpp = new CMPP();//cmpp2api接口。
        Object obj = null;
        try
        {
            cmppe_result cmppe_result1 = cmpp.readResPack(conn_desc1);//读取包信息。
            switch(cmppe_result1.pack_id)
            {
            case -2147483648: 
                System.out.println("get nack pack");
                break;
            //登录应答
            case -2147483647: 
                cmppe_login_result cmppe_login_result1 = (cmppe_login_result)cmppe_result1;
                System.out.println("------------login resp----------: STAT = " + ((cmppe_result) (cmppe_login_result1)).stat);
                break;

            case -2147483646: 
                System.out.println("------------logout resp----------: STAT = " + cmppe_result1.stat);
                break;
                //提交应答
            case -2147483644: 
                cmppe_submit_result cmppe_submit_result1 = (cmppe_submit_result)cmppe_result1;
                System.out.println("------------submit resp----------: STAT = " + ((cmppe_result) (cmppe_submit_result1)).stat + " SEQ = " + cmppe_submit_result1.seq);
                break;
                //分发应答
            case 5: // '\005'
                System.out.println("------------deliver---------: STAT = 0");
                cmppe_deliver_result cmppe_deliver_result1 = (cmppe_deliver_result)cmppe_result1;
                if(cmppe_deliver_result1.status_rpt == 1)
                    System.out.println("Rpt status = " + cmppe_deliver_result1.status_from_rpt);
                cmpp.cmpp_send_deliver_resp(conn_desc1, cmppe_deliver_result1.seq, ((cmppe_result) (cmppe_deliver_result1)).stat);
                break;
                //取消应答
            case -2147483641: 
                System.out.println("---------cancel-----------: STAT = " + cmppe_result1.stat);
                break;
                //激活应答
            case -2147483640: 
                System.out.println("---------active resp-----------: STAT " + cmppe_result1.stat);
                break;

            default:
                System.out.println("---------Error packet-----------");
                break;
            }
        }
        catch(Exception exception)
        {
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            System.out.println("have a exception");
        }
    }

    /**
     * 字符转化为的ASCII码：数字字符对应的ASCII码
     * @param c
     * @return
     */
    public static byte toHex(char c)
    {
        switch(c)
        {
        case 49: // '1'
            return 49;

        case 50: // '2'
            return 50;

        case 51: // '3'
            return 51;

        case 52: // '4'
            return 52;

        case 53: // '5'
            return 53;

        case 54: // '6'
            return 54;

        case 55: // '7'
            return 55;

        case 56: // '8'
            return 56;

        case 57: // '9'
            return 57;

        case 48: // '0'
            return 48;
        }
        return 0;
    }
    
    //测试方法
    public static void main(String[] args){
    	Cmpp2Api api=new Cmpp2Api();
    	ArrayList arraylist = new ArrayList();
    	arraylist.add("13599204724");
    	api.SendMessage("HelloWorld!", arraylist);
    	
//    	while(true)
//    	{
//    		api.ReceMsg();
//    		String sm="";
//    	}
    }
}
