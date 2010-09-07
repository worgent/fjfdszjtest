package getudpport;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GetUdpPort extends JFrame {
  JPanel contentPane;
  JScrollPane jScrollPane1 = new JScrollPane();
  JTextArea jTextArea1 = new JTextArea();
  JButton jButton1 = new JButton();

  //Construct the frame
  public GetUdpPort() {
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
    this.setTitle("獲得UDP端口號");
    jScrollPane1.setBounds(new Rectangle(14, 8, 375, 136));
    jTextArea1.setText("");
    jButton1.setBounds(new Rectangle(56, 161, 274, 30));
    jButton1.setFont(new java.awt.Font("Dialog", 0, 12));
    jButton1.setForeground(Color.magenta);
    jButton1.setText("獲得UDP端口號");
    jButton1.addActionListener(new GetUdpPort_jButton1_actionAdapter(this));
    contentPane.setBackground(new Color(138, 244, 244));
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
for(int port=1024;port<=65535;port++)
    {
    try{
     DatagramSocket datagramsocket=new DatagramSocket(port);
     datagramsocket.close();
    }catch(SocketException err){jTextArea1.append("端口號為"+port+"已經服務"+"\n");}
    }
  }
}

class GetUdpPort_jButton1_actionAdapter implements java.awt.event.ActionListener {
  GetUdpPort adaptee;

  GetUdpPort_jButton1_actionAdapter(GetUdpPort adaptee) {
    this.adaptee = adaptee;
  }
  public void actionPerformed(ActionEvent e) {
    adaptee.jButton1_actionPerformed(e);
  }
}