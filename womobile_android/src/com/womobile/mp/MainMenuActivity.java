package com.womobile.mp;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.womobile.mp.channelcollect.InfoCollActivity;
import com.womobile.mp.location.MyPositionActivity;
import com.womobile.util.AccountsDbAdapter;
import com.womobile.util.Statusnet;

public class MainMenuActivity extends Activity {

	AccountsDbAdapter mDbHelper;
	private Statusnet account;
	private String username="";
	private String password="";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置标题
		setTitle("联通移动助理-主菜单");

		// String[] menus = { "日常办公", "位置考勤", "渠道巡访","促销取证","渠道采集","订单配送","公告信息"
		// };
		/*
		 * setListAdapter(new ArrayAdapter<String>(this,
		 * android.R.layout.simple_list_item_1, menus));
		 * getListView().setTextFilterEnabled(true);
		 */

		// 设置界面布局
		setContentView(R.layout.main_menu);
		// 获得GridView实例
		GridView gridview = (GridView) findViewById(R.id.gridview);
		// 为GridView设置Adapter
		gridview.setAdapter(new ImageAdapter(this));
		
		//帐号
		mDbHelper = new AccountsDbAdapter(this);
        mDbHelper.open();
		Cursor accountsCursor = mDbHelper.fetchAccount(1);
		startManagingCursor(accountsCursor);
		try{
			if(account==null){
				username = accountsCursor.getString(accountsCursor.getColumnIndexOrThrow(AccountsDbAdapter.KEY_USERNAME));
				password = accountsCursor.getString(accountsCursor.getColumnIndexOrThrow(AccountsDbAdapter.KEY_PASSWORD));
				account = new Statusnet(username,password);
			}
		}catch(Exception e){
			startActivityForResult(new Intent(MainMenuActivity.this, LoginActivity.class), 1);
		}
	    
	}

	// 继承BaseAdapter
	public class ImageAdapter extends BaseAdapter {

		// 上下文
		private Context mContext;

		// 构造方法
		public ImageAdapter(Context c) {
			mContext = c;
		}

		// 图片资源数组
		private Integer[] mThumbIds = { R.drawable.clock_48,
				R.drawable.globe_48, R.drawable.app_48,
				R.drawable.camera_48, R.drawable.home_48,
				R.drawable.comment_warning_48 };

		// 组件个数
		@Override
		public int getCount() {
			return mThumbIds.length;
		}

		// 当前组件
		@Override
		public Object getItem(int position) {
			return null;
		}

		// 当前组件Id
		@Override
		public long getItemId(int position) {
			return 0;
		}

		// 获得当前视图
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// 声明图片视图
			ImageView imageView;
			if (convertView == null) {
				// 实例化图片视图
				imageView = new ImageView(mContext);
				// 设置图片视图属性
				imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
				imageView.setPadding(8, 8, 8, 8);
			} else {
				imageView = (ImageView) convertView;
			}
			// 设置图片视图图片资源
			imageView.setImageResource(mThumbIds[position]);

			// 为当前视图添加监听器
			switch (position) {
			//采集
			case 0:
				imageView.setOnClickListener(collectListener);
				break;
			//位置	
			case 1:
				imageView.setOnClickListener(gpsListener);
				break;
			//日常工作	
			case 2:
				imageView.setOnClickListener(workdutyListener);
				break;	
			//测试反馈	
			case 3:
				imageView.setOnClickListener(feedbackListener);
				break;	
			//数据采集
			case 4:
				imageView.setOnClickListener(collectionListener);
				break;	
			default:
				break;
			}

			return imageView;
		}
		
		
		//数据采集
		OnClickListener collectListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				//启动定位Activity
				intent.setClass(MainMenuActivity.this,InfoCollActivity.class);
				startActivity(intent);
			}
			
		};
		
		
		//定位监听器
		OnClickListener gpsListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent=new Intent();
				//启动定位Activity
				intent.setClass(MainMenuActivity.this,MyPositionActivity.class);
				startActivity(intent);
			}
			
		};
		
		//日常工作
		OnClickListener collectionListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
				Intent intent=new Intent();
				//启动定位Activity
				intent.setAction("android.intent.action.Collection");
				startActivity(intent);
				}catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		};	
		
		
		//日常工作
		OnClickListener workdutyListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
				Intent intent=new Intent();
				//启动定位Activity
				intent.setAction("android.intent.action.Workduty");
				startActivity(intent);
				}catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		};
		
		//测试
		OnClickListener feedbackListener=new OnClickListener() {

			@Override
			public void onClick(View v) {
				try{
					Intent intent = new Intent();
					intent.setAction("android.intent.action.Feedback");
					startActivity(intent);
				}catch(Exception e)
				{
					System.out.println(e.toString());
				}
			}
			
		};


	}

}