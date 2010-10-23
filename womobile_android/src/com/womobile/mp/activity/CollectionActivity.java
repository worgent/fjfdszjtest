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

public class CollectionActivity extends Activity {
	private Button btnCollectionadd;
	private Button btnCollectionquery;
	private Button btnCollectionback;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.collectionlist);
		
		btnCollectionadd = (Button) this.findViewById(R.id.btncollectionadd);
		btnCollectionadd.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.AddCollection");
				startActivityForResult(intent, RESULT_OK);
			}
		});
		
		btnCollectionquery = (Button) this.findViewById(R.id.btncollectionquery);
		btnCollectionquery.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.QueryCollection");
				startActivity(intent);
			}
		});

		btnCollectionback = (Button) this.findViewById(R.id.btncollectionback);
		btnCollectionback.setOnClickListener(new OnClickListener() {
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
