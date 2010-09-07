package likeqq;
/*小凌 QQ:3312883*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

//class Customer extends Object implements java.io.Serializable
//{
//     String custName;
//     String custPassword;
//}
public class CustomerApplet extends JApplet 
{
	//声明 面板 标签 按钮....
	JPanel panelObject;
	JLabel labelCustName; 
	JLabel labelCustPassword;
	JTextField textCustName;  
	JPasswordField textCustPassword;
    JButton buttonLogin;
	GridBagLayout gl;
	GridBagConstraints gbc;

	public void init()//程序入口
	{
		//初始化 布局 变量
		gl = new GridBagLayout();//初始化格子布局 gl
		gbc = new GridBagConstraints();//初始化格子约束
		panelObject = (JPanel)getContentPane();//设置主面板
		panelObject.setLayout(gl);//在主面板里设置一个格子布局 gl
            
            //初始化 控件
		labelCustName = new JLabel("Customer Login Name");
		labelCustPassword = new JLabel("Password");
		textCustName = new JTextField(15);
            textCustPassword = new JPasswordField(15);
            buttonLogin=new JButton("Login");
		
		//添加控件到面板上
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 1;
		gbc.gridy = 5;
		gl.setConstraints(labelCustName,gbc);
		panelObject.add(labelCustName);
	
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 4;
		gbc.gridy = 5;
		gl.setConstraints(textCustName,gbc);
		panelObject.add(textCustName);

		gbc.anchor = GridBagConstraints.NORTHWEST;	
		gbc.gridx = 1;
		gbc.gridy = 9;
		gl.setConstraints(labelCustPassword,gbc);
		panelObject.add(labelCustPassword);

		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.gridx = 4;
		gbc.gridy = 9;
		gl.setConstraints(textCustPassword,gbc);
		panelObject.add(textCustPassword);

            gbc.anchor=GridBagConstraints.NORTHWEST;
            gbc.gridx=2;
            gbc.gridy=13;
            gl.setConstraints(buttonLogin,gbc);//格子布局gl设置约束 把buttonLogin加入约束
            panelObject.add(buttonLogin);
            
            LoginAction loginrequest=new LoginAction();//初始化一个登陆按钮监听类 为loginrequest
            buttonLogin.addActionListener(loginrequest);//给登陆按钮(buttonLogin)加上"登陆按钮监听类" 让他有事件功能
	}
    class LoginAction implements ActionListener//登陆按钮监听类
    {
       public void actionPerformed(ActionEvent evt)//当发生事件从这里开始
       {
            Object obj=evt.getSource();//取出事件内容
            if(obj==buttonLogin)//判断事件内容
            {
            	
               Customer data=new Customer();//初始化一个Customer类
	   	       data.custName = textCustName.getText();//取JTextField的内容也就是用户名 赋值到data.custName上
		       data.custPassword =new String(textCustPassword.getPassword());//基本同上 这次是取的密码 赋值到data.custPassword
		   try//捕获异常
		   {
			Socket toServer;//声明一个套接字用来连接服务器
  			toServer = new Socket("127.0.0.1",1001);//初始化toServer 开始连接服务器
			ObjectOutputStream streamToServer=new ObjectOutputStream (toServer.getOutputStream());//初始化一个输出流用来取服务器的IP 然后把	IP放入一个 才初始化的输出流streamToServer 这个输出流用来 向服务器发送数据				
			streamToServer.writeObject((Customer)data);//发送数据到服务器(SP  "(Customer)data"这也是个数据转换 到data转换到Customer类型)
			
                  //下面这段就是监听了 
                  BufferedReader fromServer=new BufferedReader(new InputStreamReader(toServer.getInputStream()));//这句看着这么长其实很简单 就是把服务器返回的数据 放到fromServer里
                  String status=fromServer.readLine();//然后读出fromServer里的数据 到一个字符类型status
                  getAppletContext().showStatus(status);//在把status显示到 Applet里
	            	streamToServer.close();//关闭输出流
                  fromServer.close();//关闭BufferedReader //正常情况下 程序到这里就终止了 
                  
		 
               }
		   catch(InvalidClassException e)
		   {
			showStatus("The Customer class is invalid" + e);
		   }
		   catch(NotSerializableException e)
		   {
			showStatus("The object is not serializable" + e);
		   }
		   catch(IOException e)
		   {
			//showStatus("Cannot write to the server" + e);
			showStatus("连接服务器失败！");
		   }	
	      }
       }
    }
}
