package udpserver;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UdpServer extends JFrame {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  boolean wait=true;
  String hostname;//定义客户机名
  //设置接收数据报Socket和发送数据报Socket
  DatagramSocket receiveSocket,sendSocket;
  DatagramPacket receivePacket,sendPacket;//设置接收和发送数据报

  public void receiveMassage(){
   //接收数据包并显示
  try{
   //建立端口号为5000的DatagramSocket对象
   receiveSocket=new DatagramSocket(5000);
  byte[] buf=new byte[500];
  //建立DatagramPacket对象
  receivePacket=new DatagramPacket(buf,buf.length);
  while(wait=true)
  {
   receiveSocket.receive(receivePacket);//接收数据包
   hostname=receivePacket.getAddress().toString();// 获得主机名
   int port=receivePacket.getPort();//获得端口号
   jTextArea1.append("来自主机名为"+hostname+"端口号为"+port+"的信息");
   String str=new String(receivePacket.getData(),0,receivePacket.getLength());
   jTextArea1.append(str+"\n");
 }
  }catch(SocketException err){jTextArea1.append(err+"\n");}
 catch(IOException e){jTextArea1.append(e+"\n");}
  }

  public void sendMessage(){
   try{
     String str=jTextField1.getText();
     jTextArea1.append("服务器的消息为："+str+"\n");
     byte buf[]=new byte[500];
     str.getBytes(0,str.length(),buf,0);
     sendPacket=new DatagramPacket(buf,str.length(),InetAddress.getByName(hostname),5001);
     sendSocket.send(sendPacket);//发送数据报
     jTextField1.setText("");
   }catch(IOException e){jTextArea1.append(e+"\n");}

  }

  //Construct the frame
  public UdpServer() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(null);
    this.setSize(new Dimension(400, 300));
    this.setTitle("UDP服務器");
    jScrollPane1.setBounds(new Rectangle(6, 7, 388, 141));
    jTextArea1.setText("");
    jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
    jLabel1.setText("发送消息");
    jLabel1.setBounds(new Rectangle(8, 162, 55, 22));
    jTextField1.setFont(new java.awt.Font("Dialog", 0, 12));
    jTextField1.setText("");
    jTextField1.setBounds(new Rectangle(65, 153, 325, 37));
    jTextField1.addKeyListener(new UdpServer_jTextField1_keyAdapter(this));
    jButton1.setBounds(new Rectangle(21, 208, 136, 33));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
    jButton1.setToolTipText("");
    jButton1.setText("发送");
    jButton1.addActionListener(new UdpServer_jButton1_actionAdapter(this));
    jButton2.setBounds(new Rectangle(196, 210, 120, 30));
    jButton2.setFont(new java.awt.Font("Dialog", 0, 12));
    jButton2.setText("退出");
    jButton2.addActionListener(new UdpServer_jButton2_actionAdapter(this));
    contentPane.setBackground(new Color(244, 235, 194));
    contentPane.setFont(new java.awt.Font("Dialog", 0, 12));
    contentPane.add(jScrollPane1, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jLabel1, null);
    contentPane.add(jButton1, null);
    contentPane.add(jButton2, null);
    jScrollPane1.getViewport().add(jTextArea1, null);

   receiveMassage();

  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
  sendMessage();//发送数据报
  }

  void jButton2_actionPerformed(ActionEvent e) {
 wait=false;
 System.exit(0);
  }

  void jTextField1_keyPressed(KeyEvent e) {
if(e.getKeyChar()==KeyEvent.VK_ENTER)
    {
     sendMessage();//发送数据包
    }
  }
}

class UdpServer_jButton1_actionAdapter implements java.awt.event.ActionListener {
  UdpServer adaptee;

  UdpServer_jButton1_actionAdapter(UdpServer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class UdpServer_jButton2_actionAdapter implements java.awt.event.ActionListener {
  UdpServer adaptee;

  UdpServer_jButton2_actionAdapter(UdpServer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class UdpServer_jTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  UdpServer adaptee;

  UdpServer_jTextField1_keyAdapter(UdpServer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTextField1_keyPressed(e);
  }
}