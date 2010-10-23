/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.activity;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.womobile.mp.entities.Workduty;
import com.womobile.mp.R;
import com.womobile.mp.FieldSupport;
/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


	
	
public class AddWorkdutyActivity extends Activity {
	private EditText IDEdt;
	private EditText TITLEEdt;
	private EditText DETAILEdt;
	private EditText ADJUNCTEdt;
	private EditText TARGETEdt;
	private EditText FORMATEdt;
	private EditText PROIDEdt;
	private EditText ATTITUDEEdt;
	private EditText GRADEEdt;
	private EditText ISENDEdt;
	private EditText FLOWER_STATEEdt;
	private EditText PROCESS_CODEEdt;
	private EditText AUDITING_CODEEdt;
	private EditText BILL_STATEEdt;
	private EditText CREATE_CODEEdt;
	private EditText CREATE_DATEEdt;
	private EditText EDITOR_CODEEdt;
	private EditText EDITOR_DATEEdt;
	private EditText FEEDBKIDEdt;
		
		
	private Button okBtn;
	private Button cancelBtn;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workduty);
		initView();
		fillBrandSpinner();
		fillAgencyText();
		setListeners();
	}
	
	private void initView() {
		IDEdt=(EditText)this.findViewById(R.id.workduty_ID);
		TITLEEdt=(EditText)this.findViewById(R.id.workduty_TITLE);
		DETAILEdt=(EditText)this.findViewById(R.id.workduty_DETAIL);
		ADJUNCTEdt=(EditText)this.findViewById(R.id.workduty_ADJUNCT);
		TARGETEdt=(EditText)this.findViewById(R.id.workduty_TARGET);
		FORMATEdt=(EditText)this.findViewById(R.id.workduty_FORMAT);
		PROIDEdt=(EditText)this.findViewById(R.id.workduty_PROID);
		ATTITUDEEdt=(EditText)this.findViewById(R.id.workduty_ATTITUDE);
		GRADEEdt=(EditText)this.findViewById(R.id.workduty_GRADE);
		ISENDEdt=(EditText)this.findViewById(R.id.workduty_ISEND);
		FLOWER_STATEEdt=(EditText)this.findViewById(R.id.workduty_FLOWER_STATE);
		PROCESS_CODEEdt=(EditText)this.findViewById(R.id.workduty_PROCESS_CODE);
		AUDITING_CODEEdt=(EditText)this.findViewById(R.id.workduty_AUDITING_CODE);
		BILL_STATEEdt=(EditText)this.findViewById(R.id.workduty_BILL_STATE);
		CREATE_CODEEdt=(EditText)this.findViewById(R.id.workduty_CREATE_CODE);
		CREATE_DATEEdt=(EditText)this.findViewById(R.id.workduty_CREATE_DATE);
		EDITOR_CODEEdt=(EditText)this.findViewById(R.id.workduty_EDITOR_CODE);
		EDITOR_DATEEdt=(EditText)this.findViewById(R.id.workduty_EDITOR_DATE);
		FEEDBKIDEdt=(EditText)this.findViewById(R.id.workduty_FEEDBKID);
		okBtn = (Button) this.findViewById(R.id.btnworkdutysumbit);
		cancelBtn = (Button) this.findViewById(R.id.btnworkdutycancel);
	}
	
	private void fillBrandSpinner() {
	}
	
	private void fillAgencyText() {
	}
	
	private void setListeners() {
	    okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (validation()) {
					Uri uri = null;

					uri = AddWorkduty(Workduty.CONTENT_URI
							,Workduty.KEY_ID
							,Workduty.KEY_TITLE
							,Workduty.KEY_DETAIL
							,Workduty.KEY_ADJUNCT
							,Workduty.KEY_TARGET
							,Workduty.KEY_FORMAT
							,Workduty.KEY_PROID
							,Workduty.KEY_ATTITUDE
							,Workduty.KEY_GRADE
							,Workduty.KEY_ISEND
							,Workduty.KEY_FLOWER_STATE
							,Workduty.KEY_PROCESS_CODE
							,Workduty.KEY_AUDITING_CODE
							,Workduty.KEY_BILL_STATE
							,Workduty.KEY_CREATE_CODE
							,Workduty.KEY_CREATE_DATE
							,Workduty.KEY_EDITOR_CODE
							,Workduty.KEY_EDITOR_DATE
							,Workduty.KEY_FEEDBKID
					);
					if (uri != null) {
						openSuccessDialog();
					}else
						Toast.makeText(AddWorkdutyActivity.this, "添加失败，请检查数据",
								Toast.LENGTH_SHORT).show();
				}
			}
		});
		cancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	
	private Uri AddWorkduty(Uri uriType
				,String ID
				,String TITLE
				,String DETAIL
				,String ADJUNCT
				,String TARGET
				,String FORMAT
				,String PROID
				,String ATTITUDE
				,String GRADE
				,String ISEND
				,String FLOWER_STATE
				,String PROCESS_CODE
				,String AUDITING_CODE
				,String BILL_STATE
				,String CREATE_CODE
				,String CREATE_DATE
				,String EDITOR_CODE
				,String EDITOR_DATE
				,String FEEDBKID
			) {
		ContentValues values = new ContentValues();
		
			values.put(ID, IDEdt.getText().toString());	
			values.put(TITLE, TITLEEdt.getText().toString());	
			values.put(DETAIL, DETAILEdt.getText().toString());	
			values.put(ADJUNCT, ADJUNCTEdt.getText().toString());	
			values.put(TARGET, TARGETEdt.getText().toString());	
			values.put(FORMAT, FORMATEdt.getText().toString());	
			values.put(PROID, PROIDEdt.getText().toString());	
			values.put(ATTITUDE, ATTITUDEEdt.getText().toString());	
			values.put(GRADE, GRADEEdt.getText().toString());	
			values.put(ISEND, ISENDEdt.getText().toString());	
			values.put(FLOWER_STATE, FLOWER_STATEEdt.getText().toString());	
			values.put(PROCESS_CODE, PROCESS_CODEEdt.getText().toString());	
			values.put(AUDITING_CODE, AUDITING_CODEEdt.getText().toString());	
			values.put(BILL_STATE, BILL_STATEEdt.getText().toString());	
			values.put(CREATE_CODE, CREATE_CODEEdt.getText().toString());	
			values.put(CREATE_DATE, CREATE_DATEEdt.getText().toString());	
			values.put(EDITOR_CODE, EDITOR_CODEEdt.getText().toString());	
			values.put(EDITOR_DATE, EDITOR_DATEEdt.getText().toString());	
			values.put(FEEDBKID, FEEDBKIDEdt.getText().toString());	
		Uri uri = getContentResolver().insert(uriType, values);

		return uri;
	}

			
	private boolean validation() {
		String tag = "";
		/*
		 * if (!isInteger(countEdt.getText().toString())) tag +=
		 * "请在商品数量中输入数字\n"; if (!isValidDate()) tag += "日期不能小于今天";
		 */
		if (tag.equals(""))
			return true;
		else {
			Toast.makeText(AddWorkdutyActivity.this, tag, Toast.LENGTH_LONG)
					.show();
			return false;
		}
	}

		
	private void openSuccessDialog() {
		new AlertDialog.Builder(AddWorkdutyActivity.this).setTitle("").setMessage(
				"添加成功").setPositiveButton("确定",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
					}
				}).show();
	}
	
	
	private boolean isInteger(String number) {
		Pattern pattern = Pattern.compile("^-?\\d+$");
		Matcher match = pattern.matcher(number);
		return match.matches();
	}
}
