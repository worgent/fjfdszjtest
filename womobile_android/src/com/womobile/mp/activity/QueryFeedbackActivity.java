/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.activity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.womobile.mp.entities.Feedback;
import com.womobile.mp.R;
import com.womobile.mp.FieldSupport;
/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */

public class QueryFeedbackActivity extends Activity {
	
	private String from[] = new String[] { "count"
			,Feedback.KEY_ID
			,Feedback.KEY_TITLE
			,Feedback.KEY_DETAIL
			,Feedback.KEY_ADJUNCT
			,Feedback.KEY_ATTITUDE
			,Feedback.KEY_DEFINE
			,Feedback.KEY_FLOWER_STATE
			,Feedback.KEY_PROCESS_CODE
			,Feedback.KEY_AUDITING_CODE
			,Feedback.KEY_BILL_STATE
			,Feedback.KEY_CREATE_CODE
			,Feedback.KEY_CREATE_DATE
			,Feedback.KEY_EDITOR_CODE
			,Feedback.KEY_EDITOR_DATE
			,Feedback.KEY_BILLID
			};

	private ListView listView;	
	private EditText queryEdt;
	private EditText startDateEdt;
	private EditText endDateEdt;
	private Button okBtn;
	private Button backBtn;
	private Button homeBtn;
	private Button lastBtn;
	private Button nextBtn;
	private Button bottomBtn;
	private TextView currentPageTxt;
	
	private Uri currentFeedback;
	
	private HashMap<Integer, String> queryFeedbackMap = new HashMap<Integer, String>();
	private HashMap<Integer, Uri> uriMap = new HashMap<Integer, Uri>();
	
	private List<HashMap<String, String>> dataMaps = null;
	private List<HashMap<String, String>> dataDetailMaps = null;	

	private int pageNum = 1;
	private int pageCount = 20;
	private Boolean isEmpty = false;
	private int mYear;
	private int mMonth;
	private int mDay;
	private Boolean isStartEmpty = false;
	private Boolean isEndEmpty = false;
	private int listCount=0;
	private int listPosition = 0;	
	
	private String selection = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedbackquery);
		initMaps();
		initView();
		setListView();
		setListeners();
		
		fillDataMaps(getCurrentFeedback(), null);
		setListAdapter(getFillMaps());
	}

	private void initMaps() {
		queryFeedbackMap.put(0,Feedback.KEY_ID);
		uriMap.put(0, Feedback.CONTENT_URI);
		setCurrentFeedback(uriMap.get(0));
	}
	private void initView() {
		queryEdt = (EditText) this.findViewById(R.id.txtfeedbackquery);
		startDateEdt = (EditText) this.findViewById(R.id.txtfeedbackstartDate);
		endDateEdt = (EditText) this.findViewById(R.id.txtfeedbackendDate);
		okBtn = (Button) this.findViewById(R.id.btnfeedbacksearch);
		backBtn = (Button) this.findViewById(R.id.btnfeedbackreturn);
		homeBtn = (Button) this.findViewById(R.id.btnfeedbackhomePage);
		lastBtn = (Button) this.findViewById(R.id.btnfeedbacklastPage);
		nextBtn = (Button) this.findViewById(R.id.btnfeedbacknextPage);
		bottomBtn = (Button) this.findViewById(R.id.btnfeedbackbottomPage);
		currentPageTxt = (TextView) this.findViewById(R.id.txtfeedbackcurrentPage);
		listView = (ListView) this.findViewById(R.id.lvfeedbackMain);
	}

	private void setListView() {
		listView.setTextFilterEnabled(true);
	}
	
	
	private void setListeners() {
		startDateEdt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isStartEmpty = true;
				showDialog(0);
			}
		});
		endDateEdt.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isEndEmpty = true;
				showDialog(0);
			}
		});		
		okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				selection = queryFeedbackMap.get(0) + "=\""
						+ queryEdt.getText().toString() + "\"";
				if (!startDateEdt.getText().toString().equals("开始时间")
						&& !endDateEdt.getText().toString().equals("结束时间"))
					selection += " and date between " + "\""
							+ startDateEdt.getText().toString() + "\""
							+ " and " + "\"" + endDateEdt.getText().toString()
							+ "\"";
				fillDataMaps(getCurrentFeedback(), selection);
				setListAdapter(getFillMaps());
			}
		});
		backBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		homeBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				pageNum = 1;
				lastBtn.setEnabled(false);
				homeBtn.setEnabled(false);
				nextBtn.setEnabled(true);
				bottomBtn.setEnabled(true);
				setListAdapter(getFillMaps());
			}
		});
		lastBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				pageNum--;
				if (pageNum != 1) {
				} else {
					lastBtn.setEnabled(false);
					homeBtn.setEnabled(false);
				}
				nextBtn.setEnabled(true);
				bottomBtn.setEnabled(true);
				setListAdapter(getFillMaps());
			}
		});
		nextBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				pageNum++;
				if (pageNum != getAllPages()) {
				} else {
					nextBtn.setEnabled(false);
					bottomBtn.setEnabled(false);
				}
				homeBtn.setEnabled(true);
				lastBtn.setEnabled(true);
				setListAdapter(getFillMaps());
			}
		});
		bottomBtn.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				pageNum = getAllPages();
				nextBtn.setEnabled(false);
				bottomBtn.setEnabled(false);
				lastBtn.setEnabled(true);
				homeBtn.setEnabled(true);
				setListAdapter(getFillMaps());
			}
		});
		

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				listPosition = arg2;
				//setDetailListAdapter();
			}
		});
		listView.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				listPosition = arg2;
				//setDetailListAdapter();
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = this.getMenuInflater();
		inflater.inflate(R.menu.option_menu, menu);
		menu.findItem(R.id.menuEdit).setIcon(android.R.drawable.ic_menu_edit);
		menu.findItem(R.id.menuDel).setIcon(android.R.drawable.ic_menu_delete);
		menu.findItem(R.id.menuRecieve).setIcon(android.R.drawable.ic_menu_agenda);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {
		int tempPosition = listView.getSelectedItemPosition();
		if (tempPosition >= 0)
			listPosition = tempPosition;
		int location = (pageNum - 1) * pageCount + listPosition;
		switch (item.getItemId()) {
		case R.id.menuEdit:
			actionForEditMenuItem(location);
			break;
		case R.id.menuRecieve:
			//actionForRecieveMenuItem("",location, "");
			break;
		case R.id.menuDel:
			actionForDelMenuItem(location);
			break;
		}
		return true;
	}	
	
	
	private void actionForEditMenuItem(int location) {
		HashMap<String, String> map = dataMaps.get(location);
		Intent intent = new Intent();
		intent.setAction("android.intent.action.EditFeedback");
		intent.putExtra("dataMap", map);
		startActivity(intent);
	}
	
	private void actionForDelMenuItem(int location) {
		HashMap<String, String> map = dataMaps.get(location);
		int num = getContentResolver().delete(getCurrentFeedback(),
				"id = " + map.get(FieldSupport.KEY_ID), null);
		if (num != 0) {
			Toast.makeText(QueryFeedbackActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
			fillDataMaps(getCurrentFeedback(), null);
			setListAdapter(getFillMaps());
		} else
			Toast.makeText(QueryFeedbackActivity.this, "删除失败", Toast.LENGTH_SHORT).show();
	}

	/*
	private void actionForRecieveMenuItem(String recieveStr, int location,String statusStr) {
		HashMap<String, String> map = dataMaps.get(location);
		if (getCurrentFeedback() == Feedback.CONTENT_URI) {
			if (statusStr.equals("已提交")) {
				if (recieveStr.equals("否")) {
					ContentValues values = new ContentValues();
					values.put(Feedback.KEY_RECIEVE, "1");
					int num = getContentResolver().update(Feedback.CONTENT_URI,
							values, "id = " + map.get(FieldSupport.KEY_ID),
							null);
					if (num != 0) {
						Toast.makeText(QueryFeedbackActivity.this, "该订单确认收货",
								Toast.LENGTH_SHORT).show();
						fillDataMaps(getCurrentFeedback(), null);
						setListAdapter(getFillMaps());
					}
				} else
					Toast.makeText(QueryFeedbackActivity.this, "该订单已经收货",
							Toast.LENGTH_SHORT).show();
			} else
				Toast.makeText(QueryFeedbackActivity.this, "该订单还未提交",
						Toast.LENGTH_SHORT).show();
		} else
			Toast.makeText(QueryFeedbackActivity.this, "此功能不支持预订单",
					Toast.LENGTH_SHORT).show();
	}
	*/
		
		
	//分页相关
	private void setListAdapter(List<HashMap<String, String>> fillMaps) {
		int[] to = new int[] { R.id.cnt_count
			,R.id.cntfeedback_ID
			,R.id.cntfeedback_TITLE
			,R.id.cntfeedback_DETAIL
			,R.id.cntfeedback_ADJUNCT
			,R.id.cntfeedback_ATTITUDE
			,R.id.cntfeedback_DEFINE
			,R.id.cntfeedback_FLOWER_STATE
			,R.id.cntfeedback_PROCESS_CODE
			,R.id.cntfeedback_AUDITING_CODE
			,R.id.cntfeedback_BILL_STATE
			,R.id.cntfeedback_CREATE_CODE
			,R.id.cntfeedback_CREATE_DATE
			,R.id.cntfeedback_EDITOR_CODE
			,R.id.cntfeedback_EDITOR_DATE
			,R.id.cntfeedback_BILLID
		};

		SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
				R.layout.feedbackitem, from, to);
		listView.setAdapter(adapter);

		if (!isEmpty) {
			int allPages = getAllPages();
			if (allPages == 1) {
				homeBtn.setEnabled(false);
				lastBtn.setEnabled(false);
				nextBtn.setEnabled(false);
				bottomBtn.setEnabled(false);
			}
			currentPageTxt.setText(pageNum + "/" + allPages);
		} else {
			currentPageTxt.setText(0 + "/" + getAllPages());
			nextBtn.setEnabled(false);
			bottomBtn.setEnabled(false);
		}
	}

	
	private int getAllPages() {
		if (dataMaps != null) {
			int dataLength = dataMaps.size();
			if (dataLength % pageCount == 0)
				return dataLength / pageCount;
			else
				return dataLength / pageCount + 1;
		}
		return 0;
	}
	
	private List<HashMap<String, String>> getFillMaps() {
		List<HashMap<String, String>> fillMaps = new ArrayList<HashMap<String, String>>();
		if (dataMaps != null) {
			for (int i = pageNum * pageCount - pageCount; i < pageCount
					* pageNum; i++) {
				if (i < dataMaps.size()) {
					HashMap<String, String> map = dataMaps.get(i);
					if (map != null)
						fillMaps.add(map);
				}
			}
			return fillMaps;
		}
		return null;
	}
	//数据填充
	private void fillDataMaps(Uri uri, String selection) {
		listCount = 1;
		dataMaps = new ArrayList<HashMap<String, String>>();
		Cursor cursor = this.managedQuery(uri, null, selection, null, null);
		if (cursor.getCount() == 0) {
			isEmpty = true;
			Toast.makeText(QueryFeedbackActivity.this, "对不起，没有相关数据",
					Toast.LENGTH_SHORT).show();
		} else {
			isEmpty = false;
			nextBtn.setEnabled(true);
			bottomBtn.setEnabled(true);
			lastBtn.setEnabled(true);
			homeBtn.setEnabled(true);
			Log.i("The size of cursor", cursor.getCount() + "");
			cursor.moveToFirst();
			do {
				HashMap<String, String> firstMap = new HashMap<String, String>();
				putOrderMap(cursor, firstMap);
				dataMaps.add(firstMap);
				listCount++;
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void putOrderMap(Cursor cursor, HashMap<String, String> map) {
			int INT_ID = cursor.getColumnIndex(Feedback.KEY_ID);
			int INT_TITLE = cursor.getColumnIndex(Feedback.KEY_TITLE);
			int INT_DETAIL = cursor.getColumnIndex(Feedback.KEY_DETAIL);
			int INT_ADJUNCT = cursor.getColumnIndex(Feedback.KEY_ADJUNCT);
			int INT_ATTITUDE = cursor.getColumnIndex(Feedback.KEY_ATTITUDE);
			int INT_DEFINE = cursor.getColumnIndex(Feedback.KEY_DEFINE);
			int INT_FLOWER_STATE = cursor.getColumnIndex(Feedback.KEY_FLOWER_STATE);
			int INT_PROCESS_CODE = cursor.getColumnIndex(Feedback.KEY_PROCESS_CODE);
			int INT_AUDITING_CODE = cursor.getColumnIndex(Feedback.KEY_AUDITING_CODE);
			int INT_BILL_STATE = cursor.getColumnIndex(Feedback.KEY_BILL_STATE);
			int INT_CREATE_CODE = cursor.getColumnIndex(Feedback.KEY_CREATE_CODE);
			int INT_CREATE_DATE = cursor.getColumnIndex(Feedback.KEY_CREATE_DATE);
			int INT_EDITOR_CODE = cursor.getColumnIndex(Feedback.KEY_EDITOR_CODE);
			int INT_EDITOR_DATE = cursor.getColumnIndex(Feedback.KEY_EDITOR_DATE);
			int INT_BILLID = cursor.getColumnIndex(Feedback.KEY_BILLID);
			
		    map.put("count", listCount + "");
			map.put(Feedback.KEY_ID, cursor.getString(INT_ID));
			map.put(Feedback.KEY_TITLE, cursor.getString(INT_TITLE));
			map.put(Feedback.KEY_DETAIL, cursor.getString(INT_DETAIL));
			map.put(Feedback.KEY_ADJUNCT, cursor.getString(INT_ADJUNCT));
			map.put(Feedback.KEY_ATTITUDE, cursor.getString(INT_ATTITUDE));
			map.put(Feedback.KEY_DEFINE, cursor.getString(INT_DEFINE));
			map.put(Feedback.KEY_FLOWER_STATE, cursor.getString(INT_FLOWER_STATE));
			map.put(Feedback.KEY_PROCESS_CODE, cursor.getString(INT_PROCESS_CODE));
			map.put(Feedback.KEY_AUDITING_CODE, cursor.getString(INT_AUDITING_CODE));
			map.put(Feedback.KEY_BILL_STATE, cursor.getString(INT_BILL_STATE));
			map.put(Feedback.KEY_CREATE_CODE, cursor.getString(INT_CREATE_CODE));
			map.put(Feedback.KEY_CREATE_DATE, cursor.getString(INT_CREATE_DATE));
			map.put(Feedback.KEY_EDITOR_CODE, cursor.getString(INT_EDITOR_CODE));
			map.put(Feedback.KEY_EDITOR_DATE, cursor.getString(INT_EDITOR_DATE));
			map.put(Feedback.KEY_BILLID, cursor.getString(INT_BILLID));
	}

	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			mYear = Calendar.getInstance().get(Calendar.YEAR);
			mMonth = Calendar.getInstance().get(Calendar.MONTH);
			mDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}

		return null;
	}

	
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			setDateFiled();
		}

		private void setDateFiled() {
			if (isStartEmpty) {
				startDateEdt.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
				isStartEmpty = false;
			}
			if (isEndEmpty) {
				endDateEdt.setText(mYear + "-" + (mMonth + 1) + "-" + mDay);
				isEndEmpty = false;
			}
		}
	};
	
	public Uri getCurrentFeedback() {
		return currentFeedback;
	}
	public void setCurrentFeedback(Uri current) {
		this.currentFeedback = current;
	}
}
