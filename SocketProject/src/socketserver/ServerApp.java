package socketserver;

 
import javax.swing.UIManager;
import java.awt.*;

public class ServerApp {
  boolean packFrame = false;

  //构造方法
  public ServerApp() {
    ServerFrameApp frame = new ServerFrameApp();
    if (packFrame) {
      frame.pack();
    }
    else {
      frame.validate();
    }
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = frame.getSize();
    if (frameSize.height > screenSize.height) {
      frameSize.height = screenSize.height;
    }
    if (frameSize.width > screenSize.width) {
      frameSize.width = screenSize.width;
    }
    frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) /1);
    frame.setVisible(true);
  }
  //主方法
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
    catch(Exception e) {
      e.printStackTrace();
    }
    new ServerApp();
  }
}