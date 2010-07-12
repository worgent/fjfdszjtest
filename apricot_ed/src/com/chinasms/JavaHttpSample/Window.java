package com.chinasms.JavaHttpSample;

//登入的用户名/密码:admin/123.
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;

public class Window {
	Display display = new Display();

	Shell shell = new Shell(display);
	
	Text userName, password, destination, message, pubisher;
	
	CLabel report;
	
	public Window() {
		init();	
	}
	
	private void init() {
		shell.setText("JavaHttp应用实例");
		shell.setImage(new Image(display, "../image/china_sms_logo.ico"));

		GridLayout gridLayout = new GridLayout(4, false);
		gridLayout.verticalSpacing = 10;

		shell.setLayout(gridLayout);

		// User Name
		Label label = new Label(shell, SWT.LEFT);
		label.setText("企业用户名：");

		userName = new Text(shell, SWT.SINGLE | SWT.BORDER);
		GridData gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		userName.setLayoutData(gridData);

		// Password
		label = new Label(shell, SWT.LEFT);
		label.setText("企业用户密码：");

		password = new Text(shell, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		gridData.horizontalSpan = 3;
		password.setLayoutData(gridData);
		password.setEchoChar('*');
		
		// Details.
		label = new Label(shell, SWT.LEFT);
		label.setText("目的手机号：");

		destination = new Text(shell, SWT.SINGLE | SWT.BORDER);
		destination.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		destination.setTextLimit(11);
		
		/*
		label = new Label(shell, SWT.NULL);
		label.setText("Publisher");

		pubisher = new Text(shell, SWT.SINGLE | SWT.BORDER);
		pubisher.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));

		label = new Label(shell, SWT.NULL);
		label.setText("Rating");

		Combo rating = new Combo(shell, SWT.READ_ONLY);
		rating.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		rating.add("5");
		rating.add("4");
		rating.add("3");
		rating.add("2");
		rating.add("1");
		*/
		
		// Abstract.

		label = new Label(shell, SWT.LEFT);
		label.setText("短信内容：");
		
		message = new Text(shell, SWT.WRAP | SWT.MULTI | SWT.BORDER
				| SWT.H_SCROLL | SWT.V_SCROLL);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL
				| GridData.VERTICAL_ALIGN_FILL);
		gridData.horizontalSpan = 4;
		gridData.grabExcessVerticalSpace = true;

		message.setLayoutData(gridData);

		// Button.
		Button enter = new Button(shell, SWT.PUSH);
		enter.setText("发送");

		gridData = new GridData();
		gridData.horizontalSpan = 8;
		gridData.horizontalAlignment = GridData.END;
		enter.setLayoutData(gridData);

		// report
		label = new Label(shell, SWT.NULL);
		label.setText("信息报告：");

		gridData = new GridData();
		gridData.verticalSpan = 3;
		label.setLayoutData(gridData);

		report = new CLabel(shell, SWT.NULL);
	

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 1;
		gridData.verticalSpan = 1;
		gridData.heightHint = 100;
		gridData.widthHint = 100;

		//report.setLayoutData(gridData);
		//report.setFont(new Font(display, "Courier", 24, SWT.BOLD));
		report.setForeground(new Color(display,0,0,255));
		
		enter.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				send();
			}
		});

		
		// Fill information.

		userName.setText("huage8888");
		password.setText("85272100");
		destination.setText("13807084531,13657912707");
		//pubisher.setText("John Wiley & Sons");
		//report.setBackground(new Image(display, "../image/China_logo.gif"));
		message.setText("欢迎使用中国短信发送");

		shell.pack();
		shell.open();

		// Set up the event loop.
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				// If no more entries in event queue
				display.sleep();
			}
		}

		display.dispose();

	}

	public void send() {
		
		// 存放调用返回的结果,判断成功与否应该分析这个字符串
		String returnCode; 
		
		// 这里修改成你自己的用户名和密码
		Sender sms = new Sender(userName.getText(), password.getText());
		
		//
		// 这里修改成要发送的手机号码和发送内容
		returnCode = sms.massSend(destination.getText(), message.getText(),"时间","特服代码");
		
		String encode = null;
		try {
			
			encode = new String(returnCode.getBytes("GBK"), "GB2312");
			report.setText(encode);
			pubisher.setText(encode);
			System.out.println(encode);
			
		} catch (Exception e) {
			System.out.println("异常");
		}
	}
	
	public static void main(String[] args) {
		new Window();
	}
}