import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class ClientFrameApp extends JFrame {
	//相关变量定义
	JPanel contentPane; //定义面板
	JScrollPane scrollText = new JScrollPane(); //定义滚动条
	JTextArea txtArea = new JTextArea(); //文本区域
	
	
	JLabel lbMobile = new JLabel(); // 文本提示框       手机号
	JTextField txtMobile = new JTextField(); //文本框
	
	
	JLabel lbSend = new JLabel(); // 文本提示框			内容
	JTextField txtConet = new JTextField(); //文本框
	
	
	JButton btnLink = new JButton(); //链接按钮
	JButton btnSend = new JButton(); //发送按钮
	JButton btnAbbreviate = new JButton(); //发送按钮
	JButton btnExit = new JButton(); //退出按钮
	
	JButton btnStartSMproxy = new JButton(); //发送按钮
	JButton btnEndSMproxy = new JButton(); //退出按钮
	
	JLabel lbHitMsg= new JLabel(); //提示信息
	
	//短信收发类
	SMProxySend myproxy=null;

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
		this.setTitle("SMproxy短信发送");
		
		// 发送信息，文本框和标签.
		lbMobile.setText("手机号");
		lbMobile.setBounds(new Rectangle(8, 5, 50, 20));
		//内容文件框
		txtMobile.setText("13599204724");
		txtMobile.setBounds(new Rectangle(65, 5, 200, 20));
		
		//发送信息到服务处理
		btnSend.setBounds(new Rectangle(280,5,80, 20));
		btnSend.setText("发送");
		btnSend.addActionListener(new Frame1_btnSend_actionAdapter(this));
		//退出事件处理
		btnExit.setBounds(new Rectangle(380, 5, 80, 20));
		btnExit.setText("退出");
		btnExit.addActionListener(new Frame1_btnExit_actionAdapter(this));
		
		
		//拼音处理按键
		btnAbbreviate.setBounds(new Rectangle(280,350,80, 20));
		btnAbbreviate.setText("拼音");
		btnAbbreviate.addActionListener(new Frame1_btnSend_actionAdapter(this));
		
		// 发送信息，文本框和标签.
		lbSend.setText("发送内容");
		lbSend.setBounds(new Rectangle(8, 30, 50,20));
		//内容文件框
		txtConet.setText("Linux下短信测试！");
		txtConet.setBounds(new Rectangle(65, 30, 200,20));
		//回车事件处理
		txtConet.addKeyListener(new Frame1_txtConet_keyAdapter(this));
		//滚动条,文本区域组件的绑定
		scrollText.setBounds(new Rectangle(8, 55, 450,275));
		txtArea.setText("");
		scrollText.getViewport().add(txtArea, null);
		txtArea.addKeyListener(new Frame1_txtArea_keyAdapter(this));
		//发送信息到服务处理
		btnStartSMproxy.setBounds(new Rectangle(280,30,80, 20));
		btnStartSMproxy.setText("启动短信服务");
		btnStartSMproxy.addActionListener(new Frame1_btnStartSMproxy_actionAdapter(this));
		//退出事件处理
		btnEndSMproxy.setBounds(new Rectangle(380, 30, 80, 20));
		btnEndSMproxy.setText("关闭短信服务");
		btnEndSMproxy.addActionListener(new Frame1_btnEndSMproxy_actionAdapter(this));
		// 提示信息
		lbHitMsg.setText("手机号，内容必填！");
		lbHitMsg.setForeground(Color.red);
		lbHitMsg.setBounds(new Rectangle(8, 335, 450, 40));
		contentPane.add(lbMobile, null);
		contentPane.add(txtMobile, null);
		contentPane.add(btnSend, null);
		contentPane.add(btnAbbreviate, null);
		contentPane.add(btnExit, null);
		contentPane.add(lbSend, null);
		contentPane.add(txtConet, null);
		contentPane.add(btnStartSMproxy, null);
		contentPane.add(btnEndSMproxy, null);
		contentPane.add(scrollText, null);
		contentPane.add(lbHitMsg, null);
	}
	
	public void SendSMproxy(String mobiles,String content)
	{
		myproxy.SendMessage(mobiles, content);
		
	}
	/**
	 * 拼音处理
	 */
	void btnAbbreviate_actionPerformed(ActionEvent e) {
		String mobiles=txtMobile.getText().toString();
		String content=txtConet.getText().toString();
		AddMsgTextArea("发送短信，手机号:"+mobiles+"\r\n 内容:"+content);
		SendSMproxy(mobiles,content);
	}
	
	/**
	 * 发送缓冲区数据
	 */
	void btnSend_actionPerformed(ActionEvent e) {
		String mobiles=txtMobile.getText().toString();
		String content=txtConet.getText().toString();
		AddMsgTextArea("发送短信，手机号:"+mobiles+"\r\n 内容:"+content);
		SendSMproxy(mobiles,content);
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
		//txtConet.setText("");		
	}

	/**
	 * 退出信息发送
	 */
	void btnExit_actionPerformed(ActionEvent e) {
		try {
			myproxy.Close();
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
	
	/**
	 * 短信连接事件
	 */
	void btnStartSMproxy_actionPerformed(ActionEvent e) {
		if (myproxy.myProxy == null) {
			myproxy = new SMProxySend(txtArea);
			myproxy.ProBaseConf();
			AddMsgTextArea("启动短信服务");
		}
	}
	
	/**
	 * 短信断开事件
	 */
	void btnEndSMproxy_actionPerformed(ActionEvent e) {
		myproxy.Close();
		AddMsgTextArea("关闭短信服务");
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


//拼音事件
class Frame1_btnAbbreviate_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnAbbreviate_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnAbbreviate_actionPerformed(e);
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

//发送事件
class Frame1_btnStartSMproxy_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnStartSMproxy_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnStartSMproxy_actionPerformed(e);
	}
}

//退出事件
class Frame1_btnEndSMproxy_actionAdapter implements java.awt.event.ActionListener {
	ClientFrameApp adaptee;

	Frame1_btnEndSMproxy_actionAdapter(ClientFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnEndSMproxy_actionPerformed(e);
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


