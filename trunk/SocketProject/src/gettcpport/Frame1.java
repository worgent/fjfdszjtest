package gettcpport;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
public class Frame1 extends JFrame {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton1 = new JButton();

  //Construct the frame
  public Frame1() {
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
    this.setTitle("獲得TCP端口");
    jScrollPane1.setBounds(new Rectangle(25, 23, 189, 160));
    jButton1.setBounds(new Rectangle(34, 208, 159, 32));
    jButton1.setText("獲得端口");
    jButton1.addActionListener(new Frame1_jButton1_actionAdapter(this));
    jTextArea1.setText("");
    contentPane.add(jScrollPane1, null);
    contentPane.add(jButton1, null);
    jScrollPane1.getViewport().add(jTextArea1, null);
  }
  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }

  void jButton1_actionPerformed(ActionEvent e) {
String host="localhost";//定義本地主機
    for(int i=1024;i<65535;i++)
    {
    try{
     Socket socket=new Socket(host,i);
     jTextArea1.append(i+"\n");
    }catch(UnknownHostException e1){break;}
  catch(IOException error){}
    }
  }
}

class Frame1_jButton1_actionAdapter implements java.awt.event.ActionListener {
  Frame1 adaptee;

  Frame1_jButton1_actionAdapter(Frame1 adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}