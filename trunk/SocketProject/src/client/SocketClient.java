package client;
import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SocketClient extends JFrame implements Runnable {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JTextField jTextField1 = new JTextField();
  JButton jButton1 = new JButton();
  JButton jButton2 = new JButton();
  JButton jButton3 = new JButton();

  Socket socket;
 BufferedReader bufferedReader;
 PrintWriter printWriter;
 private class GetInfo implements Runnable{
   public void run() {
     String str;
     try {
       str = bufferedReader.readLine(); //從bufferedReader讀入一行
       while (str != "客戶端退出！") {
         jTextArea1.append("客戶端信息：" + str + "\n");
         str = bufferedReader.readLine(); //繼續讀入客戶端信息
       }
       bufferedReader.close(); //關閉緩沖區
       printWriter.close();
       socket.close(); //關閉socket

     }
     catch (Exception e) {
       jButton2.setEnabled(false);
       jTextArea1.append(e.toString() + "\n");
     }
   }
 }


  //Construct the frame
  public SocketClient() {
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
    this.setTitle("客戶端");
    jScrollPane1.setBounds(new Rectangle(10, 5, 381, 147));
    jTextArea1.setText("");
    jTextField1.setText("");
    jTextField1.setBounds(new Rectangle(12, 162, 375, 32));
    jTextField1.addKeyListener(new SocketClient_jTextField1_keyAdapter(this));
    jButton1.setBounds(new Rectangle(7, 205, 84, 25));
    jButton1.setText("連接");
    jButton1.addActionListener(new SocketClient_jButton1_actionAdapter(this));
    jButton2.setBounds(new Rectangle(113, 206, 96, 24));
    jButton2.setEnabled(false);
    jButton2.setFocusPainted(true);
    jButton2.setText("發送");
    jButton2.addActionListener(new SocketClient_jButton2_actionAdapter(this));
    jButton3.setBounds(new Rectangle(226, 208, 103, 22));
    jButton3.setText("退出");
    jButton3.addActionListener(new SocketClient_jButton3_actionAdapter(this));
    contentPane.add(jScrollPane1, null);
    contentPane.add(jTextField1, null);
    contentPane.add(jButton1, null);
    contentPane.add(jButton2, null);
    contentPane.add(jButton3, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
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
    //throw new java.lang.UnsupportedOperationException("Method run() not yet implemented.");
    try{
       socket=new Socket("127.0.0.1",2001);
       //將客戶機名為client，如不是清更改
       bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
       printWriter=new PrintWriter(socket.getOutputStream());
       jButton2.setEnabled(true);
       GetInfo getinfo=new GetInfo();
       Thread t=new Thread(getinfo);
       t.start();
       jTextArea1.append("客戶端以加入！\n");
       jButton1.setEnabled(false);
      }catch(Exception e){jTextArea1.append(e.toString()+"\n");}

  }

  void jButton1_actionPerformed(ActionEvent e) {
//開始線程
    Thread t=new Thread(this);
    t.start();
  }

  void jButton2_actionPerformed(ActionEvent e) {
    printWriter.println(jTextField1.getText());
        printWriter.flush();
        jTextArea1.append("客戶端信息："+jTextField1.getText()+"\n");
        jTextField1.setText("");
  }

  void jButton3_actionPerformed(ActionEvent e) {
    try{
        printWriter.println("客戶端退出！");
        printWriter.flush();
        }catch(Exception err){jTextArea1.append(err.toString()+"\n");}
    finally{System.exit(0);}
  }

  void jTextField1_keyPressed(KeyEvent e) {
    if(e.getKeyChar()==KeyEvent.VK_ENTER)
        {
        printWriter.println(jTextField1.getText());
        printWriter.flush();
        jTextArea1.append("客戶端信息："+jTextField1.getText()+"\n");
        jTextField1.setText("");
        }
  }
}

class SocketClient_jButton1_actionAdapter implements java.awt.event.ActionListener {
  SocketClient adaptee;

  SocketClient_jButton1_actionAdapter(SocketClient adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}

class SocketClient_jButton2_actionAdapter implements java.awt.event.ActionListener {
  SocketClient adaptee;

  SocketClient_jButton2_actionAdapter(SocketClient adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton2_actionPerformed(e);
  }
}

class SocketClient_jButton3_actionAdapter implements java.awt.event.ActionListener {
  SocketClient adaptee;

  SocketClient_jButton3_actionAdapter(SocketClient adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton3_actionPerformed(e);
  }
}

class SocketClient_jTextField1_keyAdapter extends java.awt.event.KeyAdapter {
  SocketClient adaptee;

  SocketClient_jTextField1_keyAdapter(SocketClient adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.jTextField1_keyPressed(e);
  }
}