package socketserver;

import java.net.*;
import java.util.Properties;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import socketclient.ClientFrameApp;

public class ServerFrameApp extends JFrame implements Runnable {

	// 相关变量定义
	JPanel contentPane; // 定义面板
	JScrollPane scrollText = new JScrollPane(); // 定义滚动条
	JTextArea txtArea = new JTextArea(); // 文本区域
	JLabel lbSend = new JLabel(); // 文本提示框
	JTextField txtConet = new JTextField(); // 文本框

	JButton btnSend = new JButton(); // 发送按钮
	JButton btnExit = new JButton(); // 退出按钮

	Socket socket; // 客户端Socket
	ServerSocket serversocket; // 服务端Socket
	BufferedReader clientBR; // 读入流文件
	PrintWriter clientPW; // 打印流文件

	private static String ip = ""; // 远程服务器的ip
	private static int port = 0; // 远程服务器的端口
	
	private static boolean flag = true; // 远程服务器的端口

	/**
	 * 构造函数
	 */
	public ServerFrameApp() {
		enableEvents(AWTEvent.WINDOW_EVENT_MASK);
		try {
			jbInit();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化控件
	 * 
	 */
	private void jbInit() throws Exception {
		// 面板
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(null);
		this.setSize(new Dimension(500, 400));
		this.setTitle("星网GPS服务端");
		// 设置滚动条，与文本区域的关连
		scrollText.setBounds(new Rectangle(15, 5, 371, 182));
		txtArea.setText("");
		scrollText.getViewport().add(txtArea, null);
		txtArea.addKeyListener(new Frame1_txtArea_keyAdapter(this));
		// 发送信息，文本框和标签.
		lbSend.setText("发送消息");
		lbSend.setBounds(new Rectangle(17, 198, 52, 20));
		txtConet.setText("");
		txtConet.setBounds(new Rectangle(76, 197, 309, 28));
		txtConet.addKeyListener(new Frame1_txtConet_keyAdapter(this));
		// 发送按钮
		btnSend.setBounds(new Rectangle(20, 255, 105, 28));
		btnSend.setEnabled(false);
		btnSend.setText("发送");
		btnSend.addActionListener(new Frame1_btnSend_actionAdapter(this));
		// 退出按钮
		btnExit.setBounds(new Rectangle(189, 257, 112, 27));
		btnExit.setText("退出");
		btnExit.addActionListener(new Frame1_btnExit_actionAdapter(this));

		contentPane.add(scrollText, null);
		contentPane.add(lbSend, null);
		contentPane.add(txtConet, null);
		contentPane.add(btnSend, null);
		contentPane.add(btnExit, null);
		// 启动本类线程
		Thread t = new Thread(this);
		t.start();
	}

	/**
	 * 定义一个内部类，接收信息
	 * 
	 * @author fjfdszj
	 * 
	 */
	private class GetInfo implements Runnable {
		public void run() {
			String str;
			try {
				do{
					str = clientBR.readLine();// 继续从接收缓冲区读取一行数据
					txtArea.append("客戶端消息：" + str + "\n");
				}while(!str.equalsIgnoreCase("client_exit"));
				clientBR.close(); // 关闭接收缓冲区数据
				clientPW.close(); // 关闭发送缓冲区数据
				socket.close(); // 关闭socket
				//serversocket.close(); // 关闭serversocket
				btnSend.setEnabled(false);
				//flag=false;
				// 启动本类线程
				//Thread t = new Thread(this);
				//t.start();				
			} catch (Exception e) {
				txtArea.append(e.toString() + "\n");
			}
		}
	}

	/**
	 * 通过读取配置文件信息得到远程的IP地址及相关信息
	 */
	private static void GetRemoteAdd() {
		// 加载Properties文件流
		if ("".equals(ip)) {
			Properties properties = new Properties();
			ip="127.0.0.1";
			port=5024;
//			try {
//				properties.load(ClientFrameApp.class.getResourceAsStream("/LocalConf.properties"));
//				ip = properties.getProperty("SMS.IP");
//				port = Integer.parseInt(properties.getProperty("SMS.PORT"));
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
	}

	/**
	 * 本类线程方法
	 */
	public void run() {
		try {
			GetRemoteAdd();// 取得本地的ip和端口号
			if(flag)serversocket = new ServerSocket(port);
			while (true) {
				socket = serversocket.accept();// 接收客戶端的socket信息
				clientBR = new BufferedReader(new InputStreamReader(socket
						.getInputStream()));// 接收缓冲区
				clientPW = new PrintWriter(socket.getOutputStream());// 发送缓冲区
				if (socket != null) {
					txtArea.append("客戶端连接成功！\n");
					txtArea.append("新的连接: " + socket.getInetAddress() + ":"
							+ socket.getPort() + "\n");
					btnSend.setEnabled(true);
				}
				GetInfo getinfo = new GetInfo();
				Thread t = new Thread(getinfo);// 創建接收客戶端消息的線程
				t.start();
			}
		} catch (Exception e) {
			txtArea.append(e.toString() + "\n");
		}

	}

	/***************************************************************************
	 * windows关闭事件
	 */
	protected void processWindowEvent(WindowEvent e) {
		super.processWindowEvent(e);
		if (e.getID() == WindowEvent.WINDOW_CLOSING) {
			try {
				txtArea.append("服务端退出！\n");
				clientPW.println("server_exit");
				clientPW.flush();
			} catch (Exception err) {
				txtArea.append(err.toString() + "\n");
			} finally {
				System.exit(0);
			}
		}
	}

	/**
	 * 发送信息
	 * 
	 * @param e
	 */
	void btnSend_actionPerformed(ActionEvent e) {
		clientPW.println(txtConet.getText());
		clientPW.flush();
		txtArea.append("服务端信息：" + txtConet.getText() + "\n");
		txtConet.setText("");
	}

	/**
	 * 服务端退出
	 * 
	 * @param e
	 */
	void btnExit_actionPerformed(ActionEvent e) {
		try {
			txtArea.append("服务端退出！\n");
			clientPW.println("server_exit");
			clientPW.flush();
		} catch (Exception err) {
			txtArea.append(err.toString() + "\n");
		} finally {
			System.exit(0);
		}
	}

	void txtConet_keyPressed(KeyEvent e) {
		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
			clientPW.println(txtConet.getText());
			clientPW.flush();
			txtArea.append("服务端信息：" + txtConet.getText() + "\n");
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

class Frame1_btnSend_actionAdapter implements java.awt.event.ActionListener {
	ServerFrameApp adaptee;

	Frame1_btnSend_actionAdapter(ServerFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnSend_actionPerformed(e);
	}
}

class Frame1_btnExit_actionAdapter implements java.awt.event.ActionListener {
	ServerFrameApp adaptee;

	Frame1_btnExit_actionAdapter(ServerFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e) {
		adaptee.btnExit_actionPerformed(e);
	}
}

class Frame1_txtConet_keyAdapter extends java.awt.event.KeyAdapter {
	ServerFrameApp adaptee;

	Frame1_txtConet_keyAdapter(ServerFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.txtConet_keyPressed(e);
	}
}

class Frame1_txtArea_keyAdapter extends java.awt.event.KeyAdapter {
	ServerFrameApp adaptee;

	Frame1_txtArea_keyAdapter(ServerFrameApp adaptee) {
		this.adaptee = adaptee;
	}

	public void keyPressed(KeyEvent e) {
		adaptee.txtArea_keyF1(e);
	}
}