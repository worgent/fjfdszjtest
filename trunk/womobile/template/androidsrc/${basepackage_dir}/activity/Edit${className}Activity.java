<#include "/custom.include">
<#include "/my_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign actionExtension = "do">
package ${basepackage}.activity;

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

<#include "/andriod_imports.include">

	
	
public class Edit${className}Activity extends Activity {
	<#list table.columns as column>
	private EditText ${column.constantName}Edt;
	</#list>
	private int ${className}_id;

	private Button okBtn;
	private Button cancelBtn;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.${classNameLower});
		initView();
		fillBrandSpinner();
		fillAgencyText();
		setTextFileds(getIntent());
		setListeners();
	}
	
	private void initView() {
		<#list table.columns as column>
		${column.constantName}Edt=(EditText)this.findViewById(R.id.${table.sqlName}_${column.constantName});
		</#list>
		okBtn = (Button) this.findViewById(R.id.btn${table.sqlName}sumbit);
		cancelBtn = (Button) this.findViewById(R.id.btn${table.sqlName}cancel);
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
					number = update${className}(${className}.CONTENT_URI
						<#list table.columns as column>
							,${className}.KEY_${column.constantName}
						</#list>	
					);
					if (number != 0) {
						openSuccessDialog();
					}else
						Toast.makeText(Edit${className}Activity.this, "修改失败，请检查数据",
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
	
	
	private int update${className}(Uri uriType
			<#list table.columns as column>
				,String ${column.constantName}
			</#list>
			) {
		ContentValues values = new ContentValues();
		
		<#list table.columns as column>
		values.put(${column.constantName}, ${column.constantName}Edt.getText().toString());	
		</#list>
		
		
		int number = getContentResolver().update(uriType, values
				,FieldSupport.KEY_ID +"="+${className}_id, null);

		return number;
	}

	private void setTextFileds(Intent intent) {
		HashMap<String, String> map = (HashMap<String, String>) intent
				.getExtras().get("dataMap");
		HashMap<Integer, HashMap<String, String>> detailMap = (HashMap<Integer, HashMap<String, String>>) intent
				.getExtras().get("detailMap");
		${className}_id = Integer.parseInt(map.get(FieldSupport.KEY_ID));
		
		<#list table.columns as column>
		 ${column.constantName}Edt.setText(map.get(${className}.KEY_${column.constantName}));
		</#list>
		
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
			Toast.makeText(Edit${className}Activity.this, tag, Toast.LENGTH_LONG)
					.show();
			return false;
		}
	}

		
	private void openSuccessDialog() {
		new AlertDialog.Builder(Edit${className}Activity.this).setTitle("").setMessage(
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
