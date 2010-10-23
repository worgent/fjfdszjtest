/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.activity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.womobile.mp.entities.Collection;
import com.womobile.mp.R;
import com.womobile.mp.FieldSupport;
/**
 * @author badqiu email:badqiu(a)gmail.com
 * @version 1.0
 * @since 1.0
 */


	
	
public class EditCollectionActivity extends Activity {
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
	private int Collection_id;

	private Button okBtn;
	private Button cancelBtn;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collection);
		initView();
		fillBrandSpinner();
		fillAgencyText();
		setTextFileds(getIntent());
		setListeners();
	}
	
	private void initView() {
		IDEdt=(EditText)this.findViewById(R.id.collection_ID);
		TITLEEdt=(EditText)this.findViewById(R.id.collection_TITLE);
		DETAILEdt=(EditText)this.findViewById(R.id.collection_DETAIL);
		ADJUNCTEdt=(EditText)this.findViewById(R.id.collection_ADJUNCT);
		TARGETEdt=(EditText)this.findViewById(R.id.collection_TARGET);
		FORMATEdt=(EditText)this.findViewById(R.id.collection_FORMAT);
		PROIDEdt=(EditText)this.findViewById(R.id.collection_PROID);
		ATTITUDEEdt=(EditText)this.findViewById(R.id.collection_ATTITUDE);
		GRADEEdt=(EditText)this.findViewById(R.id.collection_GRADE);
		ISENDEdt=(EditText)this.findViewById(R.id.collection_ISEND);
		FLOWER_STATEEdt=(EditText)this.findViewById(R.id.collection_FLOWER_STATE);
		PROCESS_CODEEdt=(EditText)this.findViewById(R.id.collection_PROCESS_CODE);
		AUDITING_CODEEdt=(EditText)this.findViewById(R.id.collection_AUDITING_CODE);
		BILL_STATEEdt=(EditText)this.findViewById(R.id.collection_BILL_STATE);
		CREATE_CODEEdt=(EditText)this.findViewById(R.id.collection_CREATE_CODE);
		CREATE_DATEEdt=(EditText)this.findViewById(R.id.collection_CREATE_DATE);
		EDITOR_CODEEdt=(EditText)this.findViewById(R.id.collection_EDITOR_CODE);
		EDITOR_DATEEdt=(EditText)this.findViewById(R.id.collection_EDITOR_DATE);
		FEEDBKIDEdt=(EditText)this.findViewById(R.id.collection_FEEDBKID);
		okBtn = (Button) this.findViewById(R.id.btncollectionsumbit);
		cancelBtn = (Button) this.findViewById(R.id.btncollectioncancel);
	}
	
	private void fillBrandSpinner() {
	}
	
	private void fillAgencyText() {
	}
	
	private void setListeners() {
	    okBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (validation()) {
					int number = 0;
					number = updateCollection(Collection.CONTENT_URI
							,Collection.KEY_ID
							,Collection.KEY_TITLE
							,Collection.KEY_DETAIL
							,Collection.KEY_ADJUNCT
							,Collection.KEY_TARGET
							,Collection.KEY_FORMAT
							,Collection.KEY_PROID
							,Collection.KEY_ATTITUDE
							,Collection.KEY_GRADE
							,Collection.KEY_ISEND
							,Collection.KEY_FLOWER_STATE
							,Collection.KEY_PROCESS_CODE
							,Collection.KEY_AUDITING_CODE
							,Collection.KEY_BILL_STATE
							,Collection.KEY_CREATE_CODE
							,Collection.KEY_CREATE_DATE
							,Collection.KEY_EDITOR_CODE
							,Collection.KEY_EDITOR_DATE
							,Collection.KEY_FEEDBKID
					);
					if (number != 0) {
						openSuccessDialog();
					}else
						Toast.makeText(EditCollectionActivity.this, "修改失败，请检查数据",
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
	
	
	private int updateCollection(Uri uriType
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
		
		
		int number = getContentResolver().update(uriType, values
				,FieldSupport.KEY_ID +"="+Collection_id, null);

		return number;
	}

	private void setTextFileds(Intent intent) {
		HashMap<String, String> map = (HashMap<String, String>) intent
				.getExtras().get("dataMap");
		HashMap<Integer, HashMap<String, String>> detailMap = (HashMap<Integer, HashMap<String, String>>) intent
				.getExtras().get("detailMap");
		Collection_id = Integer.parseInt(map.get(FieldSupport.KEY_ID));
		
		 IDEdt.setText(map.get(Collection.KEY_ID));
		 TITLEEdt.setText(map.get(Collection.KEY_TITLE));
		 DETAILEdt.setText(map.get(Collection.KEY_DETAIL));
		 ADJUNCTEdt.setText(map.get(Collection.KEY_ADJUNCT));
		 TARGETEdt.setText(map.get(Collection.KEY_TARGET));
		 FORMATEdt.setText(map.get(Collection.KEY_FORMAT));
		 PROIDEdt.setText(map.get(Collection.KEY_PROID));
		 ATTITUDEEdt.setText(map.get(Collection.KEY_ATTITUDE));
		 GRADEEdt.setText(map.get(Collection.KEY_GRADE));
		 ISENDEdt.setText(map.get(Collection.KEY_ISEND));
		 FLOWER_STATEEdt.setText(map.get(Collection.KEY_FLOWER_STATE));
		 PROCESS_CODEEdt.setText(map.get(Collection.KEY_PROCESS_CODE));
		 AUDITING_CODEEdt.setText(map.get(Collection.KEY_AUDITING_CODE));
		 BILL_STATEEdt.setText(map.get(Collection.KEY_BILL_STATE));
		 CREATE_CODEEdt.setText(map.get(Collection.KEY_CREATE_CODE));
		 CREATE_DATEEdt.setText(map.get(Collection.KEY_CREATE_DATE));
		 EDITOR_CODEEdt.setText(map.get(Collection.KEY_EDITOR_CODE));
		 EDITOR_DATEEdt.setText(map.get(Collection.KEY_EDITOR_DATE));
		 FEEDBKIDEdt.setText(map.get(Collection.KEY_FEEDBKID));
		
	}
	
	
	private boolean validation() {
		String tag = "";
		/*
		if (!isInteger(countEdt.getText().toString()))
			tag += "请在商品数量中输入数字\n";
		if (!isValidDate())
			tag += "日期不能小于今天";
		*/
		if (tag.equals(""))
			return true;
		else {
			Toast.makeText(EditCollectionActivity.this, tag, Toast.LENGTH_LONG)
					.show();
			return false;
		}
	}

		
	private void openSuccessDialog() {
		new AlertDialog.Builder(EditCollectionActivity.this).setTitle("").setMessage(
				"修改成功").setPositiveButton("确定",
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
