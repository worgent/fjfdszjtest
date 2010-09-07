package socketclient;

import java.io.*;
import java.net.*;
import java.util.Properties;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClientFrameApp extends JFrame implements Runnable {
	//相关变量定义
	JPanel contentPane; //定义面板
	JScrollPane scrollText = new JScrollPane(); //定义滚动条
	JTextArea txtArea = new JTextArea(); //文本区域
	JTextField txtConet = new JTextField(); //文本框
	JButton btnLink = new JButton(); //链接按钮
	JButton btnSend = new JButton(); //发送按钮
	JButton btnExit = new JButton(); //退出按钮

	Socket clientSocket; //客户端发送信息的clientSocket
	BufferedReader clientBR; //读入流文件
	PrintWriter clientPW; //打印流文件

	private static String ip = ""; //远程服务器的ip
	private static int port = 0; //远程服务器的端口

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
		//滚动条,文本区域组件的绑定
		scrollText.setBounds(new Rectangle(8, 5, 385, 186));
		txtArea.setText("");
		scrollText.getViewport().add(txtArea, null);
		txtArea.addKeyListener(new Frame1_txtArea_keyAdapter(this));
		//内容文件框
		txtConet.setText("");
		txtConet.setBounds(new Rectangle(9, 197, 381, 32));
		//回车事件处理
		txtConet.addKeyListener(new Frame1_txtConet_keyAdapter(this));
		//连接服务器处理
		btnLink.setBounds(new Rectangle(7, 243, 86, 27));
		btnLink.setText("链接");
		btnLink.addActionListener(new Frame1_btnLink_actionAdapter(this));
		//发送信息到服务处理
		btnSend.setBounds(new Rectangle(149, 244, 91, 27));
		btnSend.setEnabled(false);
		btnSend.setText("发送");
		btnSend.addActionListener(new Frame1_btnSend_actionAdapter(this));
		//退出事件处理
		btnExit.setBounds(new Rectangle(294, 242, 94, 28));
		btnExit.setText("退出");
		btnExit.addActionListener(new Frame1_btnExit_actionAdapter(this));
		contentPane.add(scrollText, null);
		contentPane.add(txtConet, null);
		contentPane.add(btnLink, null);
		contentPane.add(btnSend, null);
		contentPane.add(btnExit, null);
	}

	/**
	 * 创建线程（读取数据）
	 * 
	 */
	private class GetInfo implements Runnable {
		public void run() {
			String str; //相关提示信息
			try {
				do{
					str = clientBR.readLine(); //继续从s缓冲区读入一行
					txtArea.append("服务端信息：" + str + "\n");
				}while(!str.equalsIgnoreCase("server_exit"));
				btnSend.setEnabled(false); //异常时发送按钮关闭
				btnLink.setEnabled(true);
				clientBR.close(); //关闭缓冲区
				clientPW.close(); //关闭输出流
				clientSocket.close(); //关闭Socket
			} catch (Exception e) {
				txtArea.append(e.toString() + "\n");
			}
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
				clientPW.println("client_exit");
				clientPW.flush();
			} catch (Exception err) {
				txtArea.append(err.toString() + "\n");
			} finally {
				System.exit(0);
			}
		}
	}

	/**
	 * 通过读取配置文件信息得到远程的IP地址及相关信息
	 */
	private static void GetRemoteAdd() {
		//加载Properties文件流
		if ("".equals(ip)) {
			Properties properties = new Properties();
			ip="127.0.0.1";//local test
			port=5024;
//			try {
//				properties
//						.load(ClientFrameApp.class
//								.getResourceAsStream("/RemoteConfig.properties"));
//				ip = properties.getProperty("SMS.IP");
//				port = Integer.parseInt(properties.getProperty("SMS.PORT"));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	/**
	 * 发送信息时启动的线程，本类线程
	 */
	public void run() {
		try {
			GetRemoteAdd();//取得远程的ip和端口号
			clientSocket = new Socket(ip, port);//创建要访问的远程Socket

			clientBR = new BufferedReader(new InputStreamReader(clientSocket
					.getInputStream()));//接收缓冲区的数据
			clientPW = new PrintWriter(clientSocket.getOutputStream());//发送缓冲区的数据

			btnSend.setEnabled(true); //发送按钮可用
			GetInfo getinfo = new GetInfo();
			Thread t = new Thread(getinfo);
			t.start();
			txtArea.append("客戶端以加入！\n");
			btnLink.setEnabled(false); //连接按钮不可用
		} catch (Exception e) {
			txtArea.append(e.toString() + "\n");
		}

	}

	/**
	 * 点击连接时，启动本线程，
	 * @param e
	 */
	void btnLink_actionPerformed(ActionEvent e) {
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * 发送缓冲区数据
	 */
	void btnSend_actionPerformed(ActionEvent e) {
		clientPW.println(txtConet.getText());
		clientPW.flush();
		txtArea.append("客戶端信息：" + txtConet.getText() + "\n");
		txtConet.setText("");
	}

	/**
	 * 退出信息发送
	 */
	void btnExit_actionPerformed(ActionEvent e) {
		try {
			txtArea.append("客戶端退出！\n");
			clientPW.println("client_exit");
			clientPW.flush();
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
			clientPW.println(txtConet.getText());
			clientPW.flush();
			txtArea.append("客戶端信息：" + txtConet.getText() + "\n");
			txtConet.setText("");
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