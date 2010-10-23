package com.womobile.mp;

import org.apache.http.client.methods.HttpPost;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.apis.MapsDemo;
import com.womobile.util.AccountsDbAdapter;
import com.womobile.util.HttpUtil;

public class LoginActivity extends Activity {
	
	private Button cancelBtn,loginBtn;
	private EditText userEditText,pwdEditText;
	
	AccountsDbAdapter mDbHelper;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("jͨ�ƶ�����");
		setContentView(R.layout.login_system); 
		
        mDbHelper = new AccountsDbAdapter(this);
        mDbHelper.open();
        
		//ȡ��Ĭ�����
		setDefaultValues();
		
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		loginBtn = (Button)findViewById(R.id.loginButton);
		
		userEditText = (EditText)findViewById(R.id.userEditText);
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		//����������Ϣ
		userEditText.setText(settings.getString(PREF_USERNAME, DEFAULT_USERNAME));
		pwdEditText.setText(settings.getString(PREF_PASSWORD, DEFAULT_PASSWORD));
		
		

		cancelBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		loginBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(validate()){
					if(login()){
						Intent intent = new Intent(LoginActivity.this,MainMenuActivity.class);
						startActivity(intent);
					}else{
						showDialog("�û���ƻ�������������������룡");
					}
				}
			}
		});

	}
	
	private boolean login(){
		String username = userEditText.getText().toString();
		String pwd = pwdEditText.getText().toString();
		
		mDbHelper.createAccount(username, pwd, "http://identi.ca/api");
		String result=query(username,pwd);
		
		return true;
		/*
		if(result!=null&&result.equals("1")){
			return true;
		}else{
			return false;
		}
		*/
	}
	
	private boolean validate(){
		String username = userEditText.getText().toString();
		if(username.equals("")){
			showDialog("�û�����Ǳ����");
			return false;
		}
		String pwd = pwdEditText.getText().toString();
		if(pwd.equals("")){
			showDialog("�û������Ǳ����");
			return false;
		}
		return true;
	}
	private void showDialog(String msg){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(msg)
		       .setCancelable(false)
		       .setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		           }
		       });
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	private String query(String username,String password){
		String queryString = "username="+username+"&password="+password;
		String url = HttpUtil.BASE_URL+"servlet/LoginServlet?"+queryString;
		return HttpUtil.queryStringForPost(url);
    }
	
	
	//========================================================
	/* Following the menu item constants which will be used for menu creation */
	public static final int FIRST_MENU_ID = Menu.FIRST;
	public static final int CONFIGURE_MENU_ITEM = FIRST_MENU_ID + 1;
	public static final int CALL_MENU_ITEM = FIRST_MENU_ID + 2;
	public static final int ABOUT_MENU_ITEM = FIRST_MENU_ID + 3;
	public static final int EXIT_MENU_ITEM = FIRST_MENU_ID + 4;
	
	private static AlertDialog m_AlertDlg;
	//�˵���
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		boolean result = super.onCreateOptionsMenu(menu);

		MenuItem m = menu.add(0, ABOUT_MENU_ITEM, 0, R.string.btnabout);
		m.setIcon(android.R.drawable.ic_menu_info_details);
		m = menu.add(0, EXIT_MENU_ITEM, 0, R.string.btnexit);
		m.setIcon(android.R.drawable.ic_menu_close_clear_cancel);
		m = menu.add(0, CONFIGURE_MENU_ITEM, 0, R.string.btnconfig);
		m.setIcon(android.R.drawable.ic_menu_preferences);
						
		return result;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		boolean result = super.onOptionsItemSelected(item);
		Intent intent = null;
		//ȡ��url
		//String url=settings.getString(PREF_USERNAME, DEFAULT_USERNAME);
		String url="http://127.0.0.1:8080/workduty/Workduty/mpsumbit.do";//http://127.0.0.1:8080/servlet/LoginServlet?username=123&password=123
		
		switch (item.getItemId()) {
		case ABOUT_MENU_ITEM:
			if (m_AlertDlg != null) 
			{
				m_AlertDlg.cancel();
			}
			m_AlertDlg = new AlertDialog.Builder(this)
			.setMessage(getString(R.string.version).replace("\\n","\n").replace("${VERSION}", "2"))
			.setTitle(getString(R.string.btnabout))
			.setIcon(android.R.drawable.ic_menu_info_details)
			.setCancelable(true)
			.show();
			break;
			
			
		case EXIT_MENU_ITEM: 
			/*�˳�ǰ��Щʲô��*/
			
			url=posturl(url);
			if (m_AlertDlg != null) 
			{
				m_AlertDlg.cancel();
			}
			m_AlertDlg = new AlertDialog.Builder(this)
			.setMessage(url)
			.setTitle(getString(R.string.btnabout))
			.setIcon(android.R.drawable.ic_menu_info_details)
			.setCancelable(true)
			.show();
			
			
			//finish();
			break;
			
		case CONFIGURE_MENU_ITEM: {
			try {
				//测试PreferenceActivity
//				intent = new Intent(this, Settings.class);
//				startActivity(intent);
				//测试map
				intent = new Intent(this, MapsDemo.class);
				startActivity(intent);
			} catch (ActivityNotFoundException e) {
			}
		}
			break;
		}

		return result;
	}

	private String posturl(String url){ 
		String result="";
		try{
			HttpPost request = HttpUtil.getHttpPost(url);
			result= HttpUtil.queryStringForPost(request);
		}catch(Exception e){
			result=e.toString();
		}
		return result;
	}
	// Current settings handler
	private static SharedPreferences settings;
	// Context definition
	private Settings context = null;

	// Path where to store all profiles - !!!should be replaced by some system variable!!!
	private final static String profilePath = "/sdcard/wombile/";
	// Path where is stored the shared preference file - !!!should be replaced by some system variable!!!
	private final String sharedPrefsPath = "/data/data/com.womobile/shared_prefs/";
	// Shared preference file name - !!!should be replaced by some system variable!!!
	private final String sharedPrefsFile = "com.womobile.conf_preferences";
	
	public static final String PREF_USERNAME = "username";
	public static final String PREF_PASSWORD = "password";
	
	public static final String	DEFAULT_USERNAME = "";
	public static final String	DEFAULT_PASSWORD = "";
	
	private void setDefaultValues() {
		settings = getSharedPreferences(sharedPrefsFile, MODE_PRIVATE);
		//settings.registerOnSharedPreferenceChangeListener(this);
	}
}