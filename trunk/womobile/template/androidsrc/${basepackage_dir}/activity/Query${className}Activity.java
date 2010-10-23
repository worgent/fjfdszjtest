<#include "/custom.include">
<#include "/my_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.activity;

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

<#include "/andriod_imports.include">
public class Query${className}Activity extends Activity {
	
	private String from[] = new String[] { "count"
			<#list table.columns as column>
			,${className}.KEY_${column.constantName}
			</#list>	
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
	
	private Uri current${className};
	
	private HashMap<Integer, String> query${className}Map = new HashMap<Integer, String>();
	private HashMap<Integer, Uri> uriMap = new HashMap<Integer, Uri>();
	
	private List<HashMap<String, String>> dataMaps = null;
	private List<HashMap<String, String>> dataDetailMaps = null;	

	private int pageNum = 1;
	private int pageCount = 15;
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
		setContentView(R.layout.${classNameLower}query);
		initMaps();
		initView();
		setListView();
		setListeners();
		fillDataMaps(getCurrent${className}, null);
		setListAdapter(getFillMaps());
	}

	private void initMaps() {
		query${className}Map.put(0,${className}.KEY_ID);
		uriMap.put(0, ${className}.CONTENT_URI);
		setCurrent${className}(uriMap.get(0));
	}
	private void initView() {
		queryEdt = (EditText) this.findViewById(R.id.txt${table.sqlName}query);
		startDateEdt = (EditText) this.findViewById(R.id.txt${table.sqlName}startDate);
		endDateEdt = (EditText) this.findViewById(R.id.txt${table.sqlName}endDate);
		okBtn = (Button) this.findViewById(R.id.btn${table.sqlName}search);
		backBtn = (Button) this.findViewById(R.id.btn${table.sqlName}return);
		homeBtn = (Button) this.findViewById(R.id.btn${table.sqlName}homePage);
		lastBtn = (Button) this.findViewById(R.id.btn${table.sqlName}lastPage);
		nextBtn = (Button) this.findViewById(R.id.btn${table.sqlName}nextPage);
		bottomBtn = (Button) this.findViewById(R.id.btn${table.sqlName}bottomPage);
		currentPageTxt = (TextView) this.findViewById(R.id.txt${table.sqlName}currentPage);
		listView = (ListView) this.findViewById(R.id.lv${table.sqlName}Main);
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
				selection = query${className}Map.get(0) + "=\""
						+ queryEdt.getText().toString() + "\"";
				if (!startDateEdt.getText().toString().equals("开始时间")
						&& !endDateEdt.getText().toString().equals("结束时间"))
					selection += " and date between " + "\""
							+ startDateEdt.getText().toString() + "\""
							+ " and " + "\"" + endDateEdt.getText().toString()
							+ "\"";
				fillDataMaps(getCurrent${className}(), selection);
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
		intent.setAction("android.intent.action.Edit${className}");
		intent.putExtra("dataMap", map);
		startActivity(intent);
	}
	
	private void actionForDelMenuItem(int location) {
		HashMap<String, String> map = dataMaps.get(location);
		int num = getContentResolver().delete(getCurrent${className}(),
				"id = " + map.get(FieldSupport.KEY_ID), null);
		if (num != 0) {
			Toast.makeText(Query${className}Activity.this, "删除成功", Toast.LENGTH_SHORT).show();
			fillDataMaps(getCurrent${className}(), null);
			setListAdapter(getFillMaps());
		} else
			Toast.makeText(Query${className}Activity.this, "删除失败", Toast.LENGTH_SHORT).show();
	}

	/*
	private void actionForRecieveMenuItem(String recieveStr, int location,String statusStr) {
		HashMap<String, String> map = dataMaps.get(location);
		if (getCurrent${className}() == ${className}.CONTENT_URI) {
			if (statusStr.equals("已提交")) {
				if (recieveStr.equals("否")) {
					ContentValues values = new ContentValues();
					values.put(${className}.KEY_RECIEVE, "1");
					int num = getContentResolver().update(${className}.CONTENT_URI,
							values, "id = " + map.get(FieldSupport.KEY_ID),
							null);
					if (num != 0) {
						Toast.makeText(Query${className}Activity.this, "该订单确认收货",
								Toast.LENGTH_SHORT).show();
						fillDataMaps(getCurrent${className}(), null);
						setListAdapter(getFillMaps());
					}
				} else
					Toast.makeText(Query${className}Activity.this, "该订单已经收货",
							Toast.LENGTH_SHORT).show();
			} else
				Toast.makeText(Query${className}Activity.this, "该订单还未提交",
						Toast.LENGTH_SHORT).show();
		} else
			Toast.makeText(Query${className}Activity.this, "此功能不支持预订单",
					Toast.LENGTH_SHORT).show();
	}
	*/
		
		
	//分页相关
	private void setListAdapter(List<HashMap<String, String>> fillMaps) {
		int[] to = new int[] { R.id.cnt_count
			<#list table.columns as column>
			,R.id.cnt${table.sqlName}_${column.constantName}
			</#list>
		};

		SimpleAdapter adapter = new SimpleAdapter(this, fillMaps,
				R.layout.${classNameLower}item, from, to);
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
			Toast.makeText(Query${className}Activity.this, "对不起，没有相关数据",
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
				put${className}Map(cursor, firstMap);
				dataMaps.add(firstMap);
				listCount++;
			} while (cursor.moveToNext());
		}
		cursor.close();
	}

	private void put${className}Map(Cursor cursor, HashMap<String, String> map) {
		<#list table.columns as column>
			int INT_${column.constantName} = cursor.getColumnIndex(${className}.KEY_${column.constantName});
		</#list>		
		map.put("count", listCount + "");
		<#list table.columns as column>
			map.put(${className}.KEY_${column.constantName}, cursor.getString(INT_${column.constantName}));
		</#list>
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
	
	public Uri getCurrent${className}() {
		return current${className};
	}
	public void setCurrent${className}(Uri current) {
		this.current${className} = current;
	}
}
