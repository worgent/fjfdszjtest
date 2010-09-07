package likeqq;
/*小凌 QQ:3312883*/
import java.awt.event.*;
import java.io.*;
import java.net.*;

//客户信息
class Customer implements Serializable
{
	//声名 用户名 密码
	String custName;
	String custPassword;
	
}
public class AppServer extends Thread
{
	//声名服务器套接字
	ServerSocket serverSocket;
	public AppServer()
	{
		try//捕获异常
		{
		serverSocket = new ServerSocket(1001);//初始化 服务器套接字 设置服务器端口为 1001
	    }
	    catch(IOException e)
	    {
	    	fail(e,"不能启动服务器");//如果初始化失败 报错 显示异常
	    }
	    System.out.println("服务器启动 . . .");//服务器初始化成功
	    start();//启动线程
	}
	public static void fail(Exception e,String str)//自定义异常
	{
		System.out.println(str + "." + e);
	}
	public void run()//启动线程开始
	{
		try//捕获异常
		{
			while(true)//while里就开始监听客户端数据
			{
				Socket client = serverSocket.accept();//获取客户端Socket  当有数据到达时 把数据装到client里
				/*Connection con =*/ new Connection(client);//然后 初始化一个Connection (PS:因为AppServer类到这里就是循环了 所以我把前面那段注释掉了没影响:)
			}
		}
		catch(IOException e)//如果监听有问题就 包报错
		{
			fail(e,"Not listening");
		}
	}

	
    public static void main(String args[])//程序入口
	{
		new AppServer();//初始化 AppServer
	}
}

class Connection extends Thread
{
	//声明一个客户端的套接字(PS:上面那个是服务器套接字 这个是客户端的)和 一个输入流和输出流
	protected Socket netClient;//客户端的Socket
	protected ObjectInputStream fromClient;//从客户端获得数据
	protected PrintStream toClient;//发送数据到客户端
	public Connection(Socket client)//这里就是41行 那里初始化Connection 
	{
		netClient = client;//把client赋值到netClient里
		try//捕获异常
		{
		fromClient = new ObjectInputStream(netClient.getInputStream());//初始化一个输入流 把netClient里的数据赋值到fromClient上
		toClient = new PrintStream(netClient.getOutputStream());//初始化一个输出流 把netClient里的连接数据赋值到toClient上
	    }
	    catch(IOException e)//如果上面的初始化有问题 就关闭套接字
	    {
	    	try
	    	{
	    	netClient.close();//关闭套接字
	        }
	        catch(IOException el)
	        {
	        	System.err.println("Unable to set up streams" + el);
	        	return;
	        }
	    }
	    this.start();//启动第二个线程
	}
	public void run()//启动第二个线程开始
	{
		//声明一个Customer类 (PS:Customer就是最上面那里 Customer其实就是一个数据结构)
		Customer clientMessage;
		try//捕获异常
		{
			for(;;)//进入判断循环
			{
			clientMessage = (Customer)fromClient.readObject();//这里是将68行初始化的fromClient的数据读出来 转换成Customer类型 然后在赋值到clientMessage上
			if(clientMessage == null)//如果数据为空 就break
			break;
			toClient.println("收到服务器返回数据:" +"用户名"+ clientMessage.custName+"密码"+clientMessage.custPassword);//用toClient发送数据回客户端
			System.out.println("登陆:" + "用户名" +clientMessage.custName+"密码"+clientMessage.custPassword);//这句在服务器控制台上显示 连接上服务器的客户端 用户和密码
		    }
		}
		catch(IOException e)//定义IO异常
		{
			System.out.println("Error in reading object" + e);
		}
		catch(ClassNotFoundException el)
		{
			System.out.println("Error in reading object" + el);
		}
		finally//finally是撒子搞忘了...
		{
//			try
//			{
//				netClient.close();
//			}
//			catch(IOException e)
//			{
//				
//			}
		}
	}
}