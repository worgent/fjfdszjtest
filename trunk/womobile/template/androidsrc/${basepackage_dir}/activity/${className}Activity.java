<#include "/custom.include">
<#include "//my_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import ${basepackage}.R;

public class ${className}Activity extends Activity {
	private Button btn${className}add;
	private Button btn${className}query;
	private Button btn${className}back;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.${classNameLower}list);
		
		btn${className}add = (Button) this.findViewById(R.id.btn${table.sqlName}add);
		btn${className}add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.Add${className}");
				startActivityForResult(intent, RESULT_OK);
			}
		});
		
		btn${className}query = (Button) this.findViewById(R.id.btn${table.sqlName}query);
		btn${className}query.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setAction("android.intent.action.Query${className}");
				startActivity(intent);
			}
		});

		btn${className}back = (Button) this.findViewById(R.id.btn${table.sqlName}back);
		btn${className}back.setOnClickListener(new OnClickListener() {
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
