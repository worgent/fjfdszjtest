/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.womobile.mp.R;

public class WorkdutyActivity extends Activity {
	private Button btnWorkdutyadd;
	private Button btnWorkdutyquery;
	private Button btnWorkdutyback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workdutylist);
		
		btnWorkdutyadd = (Button) this.findViewById(R.id.btnworkdutyadd);
		btnWorkdutyadd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.AddWorkduty");
				startActivityForResult(intent, RESULT_OK);
			}
		});
		
		btnWorkdutyquery = (Button) this.findViewById(R.id.btnworkdutyquery);
		btnWorkdutyquery.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.QueryWorkduty");
				startActivity(intent);
			}
		});

		btnWorkdutyback = (Button) this.findViewById(R.id.btnworkdutyback);
		btnWorkdutyback.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
	
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		
		if(resultCode == RESULT_OK)
			System.out.println("back success");
	}

}
