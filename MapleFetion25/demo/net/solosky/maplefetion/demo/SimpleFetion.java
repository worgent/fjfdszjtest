package net.solosky.maplefetion.demo;

import net.solosky.maplefetion.FetionClient;
import net.solosky.maplefetion.LoginState;
import net.solosky.maplefetion.NotifyEventListener;
import net.solosky.maplefetion.bean.Message;
import net.solosky.maplefetion.demo.util.Util;
import net.solosky.maplefetion.event.ActionEvent;
import net.solosky.maplefetion.event.NotifyEvent;
import net.solosky.maplefetion.event.NotifyEventType;
import net.solosky.maplefetion.event.action.ActionEventFuture;
import net.solosky.maplefetion.event.action.FailureEvent;
import net.solosky.maplefetion.event.action.failure.RequestFailureEvent;
import net.solosky.maplefetion.event.action.success.SendChatMessageSuccessEvent;
import net.solosky.maplefetion.event.notify.ImageVerifyEvent;


/**
 * 这是个简单的飞信演示程序
 * 可以给指定的手机号码发送消息然后退出，前提是这个手机号码的飞信用户是你的好友，否则会发送失败。
 * 参数说明： SimpleFetion 手机号 密码 发送消息的手机号 消息内容
 * @author solosky
 *
 */
public class SimpleFetion {
	//final FetionClient client = new FetionClient("501558386", "szj19840212");
	final FetionClient client = new FetionClient("13599204724", "my123@123");
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1.新建对象
		SimpleFetion tt=new SimpleFetion();
		/*
		args=new String[4];
		args[0]="501558386";
		args[1]="szj19840212";
		args[2]="13599204724";//13459327646,13599204724
		args[3]="测试1";
		tt.sendmsg(args[2],args[3]);
		
		args[0]="501558386";
		args[1]="szj19840212";
		args[2]="13599204724";//13459327646,13599204724
		args[3]="测试2";
		tt.sendmsg(args[2],args[3]);
		*/
	
		String msg = "";
		//新闻
		msg = Util.dispatch("wapsohu ");
		tt.SendMsgbyLong(tt,msg,"13599204724","新闻");
		//tt.SendMsgbyLong(tt,msg,"13599204724","新闻");
		
		//天气
		msg = Util.dispatch("weather1 ");
		
		tt.SendMsgbyLong(tt,msg,"13599204724","天气");
		//tt.SendMsgbyLong(tt,msg,"13599204724","天气");
	}
	
    /**
     * 
     * Purpose      : 针对长短信分开发送方案2010-09-10
     * @param msg  : 短信内容(允许超过180)测试中是165,现在设定150
     * @param per  : 分隔短信的前缀
     * @param cmd  : 命令字
     */
    public void SendMsgbyLong(SimpleFetion sim,String msg,String phone,String per){
    	try {
    		int lenmax=150;//每条短信允许的最大长度
    		int length=msg.length();//短信消息的长度
    		int count=length/lenmax;//分为的段数 (89/24+"天"+89%24+"小时"))
    		int lencur=length%lenmax;//分为的段数 (89/24+"天"+89%24+"小时"))
    		//仅为单条就可发送的短信
    		if(count==0){
    			sim.sendmsg(phone,msg);
    		}else{
    			//需要拆分同时要循环发送格式如 ([新闻1/2])
    			if(lencur!=0){
    				count=count+1;
    			}
    			for(int i=0;i<count;i++){
    				String msgcur="";
    				
    				if(i==count-1){
    					//最后一次
    					msgcur=msg.substring(i*lenmax);
    				}else{
    					//其他的最150个字符
    					msgcur=msg.substring(i*lenmax, (i+1)*lenmax);
    				}
    				//增加前缀信息
    				msgcur="["+per+String.valueOf(i+1)+"/"+String.valueOf(count)+"]"+msgcur;
    				sim.sendmsg(phone,msgcur);
    				
    				//分开新闻与天气,相隔30秒
 		           try{ 
 		                Thread.sleep(30*1000); 
 		            } 
 		            catch(InterruptedException   e){}
    			}
    		}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	
	/**
	 * 
	 * Purpose      : 内核发送机制
	 * @param args
	 * @param client
	 */
	public void sendmsg(String phone,String cnt){
			System.out.println("正在登录中，可能需要1分钟左右，请稍候...");
			
			//这里设置一个登录状态监听器，可以显示当前登录步骤，避免用户感到焦虑
			client.setNotifyEventListener(new NotifyEventListener() {
				public void fireEvent(NotifyEvent event)
				{
					System.err.println(event.toString());
					//如果出现验证码，取消登录
					if(event.getEventType()==NotifyEventType.IMAGE_VERIFY) {
						System.out.println("当前登录过程或者操作需要验证，操作失败。");
						client.cancelVerify((ImageVerifyEvent) event);
					}
				}
			});
			//禁用掉群，登录可以变得快一点
			client.enableGroup(false);		
			
			//这里为了编程方便使用了同步登录，当然推荐异步登录，使用登录状态回调函数来完成登录成功后的操作
			LoginState state = client.syncLogin();
			if(state==LoginState.LOGIN_SUCCESS){	//登录成功
				System.out.println("登录成功，正在发送消息至 "+phone+",请稍候...");											
					
				ActionEventFuture future = new ActionEventFuture();	//建立一个Future来等待操作事件			
				client.sendChatMessage(Long.parseLong(phone), new Message(cnt), future);
				ActionEvent event = future.waitActionEventWithoutException();	//等待操作完成事件
				switch(event.getEventType()){
					
					case SUCCESS:
						SendChatMessageSuccessEvent evt = (SendChatMessageSuccessEvent) event;
						if(evt.isSendToMobile()){
							System.out.println("发送成功，消息已通过短信发送到对方手机！");
						}else if(evt.isSendToClient()){
							System.out.println("发送成功，消息已通过服务直接发送到对方客户端！");
						}
						break;
						
					case FAILURE:
						FailureEvent evt2 = (FailureEvent) event;
						switch(evt2.getFailureType()){
							case BUDDY_NOT_FOUND:
								System.out.println("发送失败, 该用户可能不是你好友，请尝试添加该用户为好友后再发送消息。");
								break;
							case USER_NOT_FOUND:
								System.out.println("发送失败, 该用户不是移动用户。");
								break;
							case SIPC_FAIL:
								System.out.println("发送失败, 服务器返回了错误的信息。");
								break;
							case UNKNOWN_FAIL:
								System.out.println("发送失败, 不知道错在哪里。");
								
							case REQEUST_FAIL:
								RequestFailureEvent evt3 = (RequestFailureEvent) event; 
								System.out.println("提示:"+evt3.getReason()+", 更多信息请访问:"+evt3.getReason());
								
							default:
								System.out.println("发送消息失败！"+event.toString());
						}
						break;
					
					/* 以下三个错误状态是在异步发送消息的情况才会发生，
					 * 为了方便处理，使用waitActionEventWithException()同步的情况下，这三个错误是通过异常来处理的
					 * 也就是在waitActionEvent的时候就会判断是否出现了这三个错误，如果出现了就会抛出相应的异常
					 * 而waitActionEventWithoutException()不会抛出异常，会把这些错误作为操作事件返回
					 */
					case SYSTEM_ERROR:
						System.out.println("发送失败, 客户端内部错误。");
						break;
					case TIMEOUT:
						System.out.println("发送失败, 超时");
						break;
					case TRANSFER_ERROR:
						System.out.println("发送失败, 超时");
				}	
				
				
				//2.退出
		        //无论发送成功还是失败，因为登录了，就必须退出，释放线程资源，否则客户端不会主动退出
		        //如果登录失败了，客户端会主动释放线程资源，不需要退出
		        System.out.print("正在退出客户端...");
		        client.logout();
		        System.out.println("已经退出。");
	            
			}else if(state==LoginState.SSI_AUTH_FAIL){
				System.out.println("你输入的手机号或者密码不对，请检查后重试!");
			}else if(state==LoginState.SSI_CONNECT_FAIL){
				System.out.println("SSI连接失败！！");
			}else if(state==LoginState.SIPC_CONNECT_FAIL){
				System.out.println("SIPC服务器连接失败！！");
			}else if(state==LoginState.LOGIN_CANCELED){
				//取消了登录
			}else {
				System.out.println("登录失败，原因："+state.name());
			}
	}

}
