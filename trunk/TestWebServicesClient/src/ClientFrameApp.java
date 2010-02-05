
/*
        service0.setProperty("mtom-enabled", "true");
        org.codehaus.xfire.client.Client.getInstance(var).setProperty("mtom-enabled","true");
        org.codehaus.xfire.client.Client.getInstance(var).setProperty(HttpTransport.CHUNKING_ENABLED,"true");
 */

import java.io.*;
import java.net.*;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.codehaus.xfire.transport.http.HttpTransport;

import com.qzgf.GetListDatebyBytesClient;
import com.qzgf.GetListDatebyBytesPortType;
import com.qzgf.utils.FileZip;
import com.qzgf.utils.StringParseToXML;
import com.qzgf.utils.WriteFile;

public class ClientFrameApp extends JFrame {
	//相关变量定义
	JPanel contentPane; //定义面板
	JScrollPane scrollText = new JScrollPane(); //定义滚动条
	JTextArea txtArea = new JTextArea(); //文本区域
	JLabel lbSend = new JLabel(); // 文本提示框
	JTextField txtConet = new JTextField(); //文本框
	JButton btnLink = new JButton(); //链接按钮
	JButton btnSend = new JButton(); //发送按钮
	JButton btnExit = new JButton(); //退出按钮
	
	JLabel lbHitMsg= new JLabel(); //提示信息

	/**
	 * 构造方法
	 * 
	 */
	public ClientFrameApp() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 控件初始化
	 * @throws Exception
	 */
	private void jbInit() throws Exception {
		//新建面板
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		this.setSize(new Dimension(500, 400));
		this.setTitle("星网GPS客戶端");
		// 发送信息，文本框和标签.
		lbSend.setText("发送报文");
		lbSend.setBounds(new Rectangle(8, 5, 50, 20));
		//内容文件框
		txtConet.setText("R,闽K00212,2008-11-01,2008-11-05");
		txtConet.setBounds(new Rectangle(65, 5, 200, 20));
		//回车事件处理
		txtConet.addKeyListener(new Frame1_txtConet_keyAdapter(this));
		//滚动条,文本区域组件的绑定
		scrollText.setBounds(new Rectangle(8, 30, 450, 300));
		txtArea.setText("");
		scrollText.getViewport().add(txtArea, null);
		txtArea.addKeyListener(new Frame1_txtArea_keyAdapter(this));
		//发送信息到服务处理
		btnSend.setBounds(new Rectangle(280,5,80, 20));
		btnSend.setText("发送");
		btnSend.addActionListener(new Frame1_btnSend_actionAdapter(this));
		//退出事件处理
		btnExit.setBounds(new Rectangle(380, 5, 80, 20));
		btnExit.setText("退出");
		btnExit.addActionListener(new Frame1_btnExit_actionAdapter(this));
		
		// 提示信息
		lbHitMsg.setText("报文格式:指令,车辆编号,中心接收开始时间,结束时间 空参数必须经以'*'号代替!");
		lbHitMsg.setForeground(Color.red);
		lbHitMsg.setBounds(new Rectangle(8, 335, 450, 40));

		contentPane.add(lbSend, null);
		contentPane.add(txtConet, null);
		contentPane.add(btnSend, null);
		contentPane.add(btnExit, null);
//		contentPane.add(btnLink, null);
		contentPane.add(scrollText, null);
		contentPane.add(lbHitMsg, null);
		
        Timer timer = new Timer(true);
        //激活短信猫数据采集周期为每小时调用
        TimerTask getGpsRecorderTimerTask = new GetGpsRecordTimer();
        timer.schedule(getGpsRecorderTimerTask, 0, 60 * 60 * 1000);
        Calendar cal = Calendar.getInstance();
        String timertaskhit="定时器已经启动，将于每晚23点"+ cal.get(Calendar.MINUTE)+"分时，取得当天所有车辆的行驶记录！";
        txtArea.append(timertaskhit + "\n");
	}
	/**
	 * 处理报文件命令
	 * 指令格式:str＝"区域" ,＂报文指令＂，＂车辆编号＂,'开始日期','结束日期'
	 */
	private void ProRemoteDate(String str)
	{
		try {

	        GetListDatebyBytesClient client = new GetListDatebyBytesClient();
	        
	        GetListDatebyBytesPortType service = client.getGetListDatebyBytesHttpPort();
	        
			List ls=(List)FileZip.uncompressList(service.getCompress(str));
			
//			测试delphi的使用
//			byte[] bytestr=service.getListToXmlToByte(str);
//			
//			String xmlstr=(String)FileZip.ByteToXml(bytestr);
//			System.out.println(xmlstr);
			
			// 2.远程服务处理完成
			AddMsgTextArea("服务端取回数据！");
			if (ls.size() == 0) {
				AddMsgTextArea("服务器数据不存在或者没有权限！");
			} else {
				// 3.客户端开始处理数据
				AddMsgTextArea("客户端开始写数据!");
				WriteFile wf = new WriteFile();
				//String filename=wf.ProWriteFilebyList("./",str,ls);
				//绝对路径
				String filename=wf.ProWriteFilebyList("",System.getProperty("user.dir")+"\\",str,ls);
				//System.out.println(System.getProperty("user.dir"));//user.dir指定了当前的路径
				AddMsgTextArea("客户端完成写数据！文件名称为:"+filename);
			}
		} catch (Exception e) {
			AddMsgTextArea("无法取得远程服务，请检查网络!\r\n"+e.toString());
		}
	}
	
	/**
	 * 发送缓冲区数据
	 */
	void btnSend_actionPerformed(ActionEvent e) {
		
		// 1.报文件信息验证
		String strCmd = txtConet.getText();
		// 2.发送报文信息
		String str = "报文件请求信息：" + txtConet.getText();
		AddMsgTextArea(str);
		if (str.split(",").length == 4) {
			// 3.处理报文请求
			String AreaStr = "103,";// 区域编号与权限相关连.
			strCmd = AreaStr + strCmd;
			ProRemoteDate(strCmd);
		} else {
			str="报文格式有误,请看相关说明";
			AddMsgTextArea(str);	
		}
	}
	/**
	 * windows的关闭按钮事件处理
	 */
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			try {
				txtArea.append("客戶端退出！\n");
			} catch (Exception err) {
				txtArea.append(err.toString() + "\n");
			} finally {
				System.exit(0);
			}
		}
	}

	/**
	 * 点击连接时，启动本线程，
	 * @param e
	 */
	void btnLink_actionPerformed(ActionEvent e) {
	}

	/**
	 * 消息面板中增加信息
	 */
	public void  AddMsgTextArea(String MsgStr)
	{
		txtArea.append(MsgStr + "\n");
		txtConet.setText("");		
	}

	/**
	 * 退出信息发送
	 */
	void btnExit_actionPerformed(ActionEvent e) {
		try {
			txtArea.append("客戶端退出！\n");
		} catch (Exception err) {
			txtArea.append(err.toString() + "\n");
		} finally {
			System.exit(0);
		}
	}

	/**
	 * 文本框回车事件
	 */
	void txtConet_keyPressed(KeyEvent e) {
		char a=e.getKeyChar();
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			// 1.报文件信息验证
			String strCmd = txtConet.getText();
			// 2.发送报文信息
			String str = "报文件请求信息：" + txtConet.getText();
			AddMsgTextArea(str);
			if (str.split(",").length == 4) {
				// 3.处理报文请求
				String AreaStr = "103,";// 区域编号与权限相关连.
				strCmd = AreaStr + strCmd;
				ProRemoteDate(strCmd);
			} else {
				str="报文格式有误,请看相关说明";
				AddMsgTextArea(str);	
			}
		}
	}

	/**
	 * 文本区域清空事件
	 */
	void txtArea_keyF1(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_F1) {
			txtArea.setText("");
		}
	}
}

//连接事件
class Frame1_btnLink_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnLink_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnLink_actionPerformed(e);
	}
}

//发送事件
class Frame1_btnSend_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnSend_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnSend_actionPerformed(e);
	}
}

//退出事件
class Frame1_btnExit_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnExit_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnExit_actionPerformed(e);
	}
}

//回车事件
class Frame1_txtConet_keyAdapter extends java.awt.event.KeyAdapter {
	ClientFrameApp adaptee;

	Frame1_txtConet_keyAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.txtConet_keyPressed(e);
	}
}

//清空事件
class Frame1_txtArea_keyAdapter extends java.awt.event.KeyAdapter {
	ClientFrameApp adaptee;

	Frame1_txtArea_keyAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.txtArea_keyF1(e);
	}
}


