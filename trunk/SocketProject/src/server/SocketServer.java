package server;
import java.net.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SocketServer extends JFrame implements Runnable {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JLabel jLabel1 = new JLabel();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();

  Socket socket;
  ServerSocket serversocket;
  BufferedReader bufferedReader;//客戶端消息緩沖區
  PrintWriter printWriter;//服務器端消息緩沖區

  private class GetInfo implements Runnable{
     //定義一個內部類，接受消息
     public void run()
     {
     String str;
     try{
      str=bufferedReader.readLine();//從bufferedreader讀入一行
      while(str!="exit!")
  {
     jTextArea1.append("客戶端消息："+str+"\n");
     str=bufferedReader.readLine();//繼續讀入客戶端消息
  }
   bufferedReader.close();//關閉緩沖區
   printWriter.close();//
   socket.close();//關閉socket
   serversocket.close();//關閉serversocket
    }catch(Exception e){jTextArea1.append(e.toString()+"\n");}
     }
    }


  //Construct the frame
  public SocketServer() {
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
    this.setTitle("服務器");
    jScrollPane1.setBounds(new Rectangle(10, 3, 379, 118));
    jLabel1.setText("發送信息");
    jLabel1.setBounds(new Rectangle(9, 143, 51, 21));
    jTextArea1.setText("");
    jTextField1.setText("");
    jTextField1.setBounds(new Rectangle(65, 139, 320, 34));
    jTextField1.addKeyListener(new SocketServer_jTextField1_keyAdapter(this));
    jButton1.setBounds(new Rectangle(8, 185, 118, 33));
    jButton1.setEnabled(false);
    jButton1.setText("發送");
    jButton1.addActionListener(new SocketServer_jButton1_actionAdapter(this));
    jButton2.setBounds(new Rectangle(151, 187, 118, 32));
    jButton2.setText("退出");
    jButton2.addActionListener(new SocketServer_jButton2_actionAdapter(this));
    contentPane.add(jScrollPane1, null);
    contentPane.add(jLabel1, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jButton1, null);
    contentPane.add(jButton2, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
 //Thread t=new Thread(frame);

  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
  public void run() {
    /**@todo Implement this java.lang.Runnable method*/
   // throw new java.lang.UnsupportedOperationException("Method run() not yet implemented.");
   try
    {

     //建立端口號為1000的服務器的端口
     serversocket=new ServerSocket(4331);
     socket=serversocket.accept();//接收客戶端
     bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));// 建立緩沖區
     printWriter=new PrintWriter(socket.getOutputStream());
     if(socket!=null)
     {
      jTextArea1.append("客戶端連接！\n");
      jButton1.setEnabled(true);
     }
     GetInfo getinfo=new GetInfo();
     Thread t=new Thread(getinfo);//創建接收客戶端消息的線程
     t.start();
    }catch(Exception e){jTextArea1.append(e.toString()+"\n");}
 }

  void jButton1_actionPerformed(ActionEvent e) {
    printWriter.println(jTextField1.getText());
       //讀入jtextfield1中的文本
       printWriter.flush();
       //將jTextField1中的文本信息顯示到jTextArea1中
       jTextArea1.append("服務器端信息："+jTextField1.getText()+"\n");
       jTextField1.setText("");

  }

  void jButton2_actionPerformed(ActionEvent e) {
//退出
    try{
     jTextArea1.append("服務器退出！\n");
     printWriter.println("exit!");
     printWriter.flush();
    }catch(Exception err){jTextArea1.append(err.toString()+"\n");}
finally{System.exit(0);}

  }

  void jTextField1_keyPressed(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER)
        {
        try{
        jTextArea1.append("服務器退出！\n");
        printWriter.println("exit!");
        printWriter.flush();
        }catch(Exception err){jTextArea1.append(e.toString()+"\n");}

    finally{System.exit(0);}
        }

  }
}

class SocketServer_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SocketServer adaptee;

  SocketServer_jButton1_actionAdapter(SocketServer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SocketServer_jButton2_actionAdapter implements java.awt.event.ActionListener {
  SocketServer adaptee;

  SocketServer_jButton2_actionAdapter(SocketServer adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class SocketServer_jTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  SocketServer adaptee;

  SocketServer_jTextField1_keyAdapter(SocketServer adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTextField1_keyPressed(e);
  }
}