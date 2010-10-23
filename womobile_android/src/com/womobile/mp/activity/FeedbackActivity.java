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

public class FeedbackActivity extends Activity {
	private Button btnFeedbackadd;
	private Button btnFeedbackquery;
	private Button btnFeedbackback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.feedbacklist);
		
		btnFeedbackadd = (Button) this.findViewById(R.id.btnfeedbackadd);
		btnFeedbackadd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.AddFeedback");
				startActivityForResult(intent, RESULT_OK);
			}
		});
		
		btnFeedbackquery = (Button) this.findViewById(R.id.btnfeedbackquery);
		btnFeedbackquery.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.QueryFeedback");
				startActivity(intent);
			}
		});

		btnFeedbackback = (Button) this.findViewById(R.id.btnfeedbackback);
		btnFeedbackback.setOnClickListener(new OnClickListener() {
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
