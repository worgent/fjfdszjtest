/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.entities;

import com.womobile.mp.provider.FeedbackProvider;
import android.net.Uri;

public class Feedback {
	public static final Uri CONTENT_URI = Uri.parse("content://"
			+ FeedbackProvider.CONTENT_URI + "/feedback");
		
	//alias
	public static final String KEY_ID = "id";
	public static final String KEY_TITLE = "title";
	public static final String KEY_DETAIL = "detail";
	public static final String KEY_ADJUNCT = "adjunct";
	public static final String KEY_ATTITUDE = "attitude";
	public static final String KEY_DEFINE = "define";
	public static final String KEY_FLOWER_STATE = "flower_state";
	public static final String KEY_PROCESS_CODE = "process_code";
	public static final String KEY_AUDITING_CODE = "auditing_code";
	public static final String KEY_BILL_STATE = "bill_state";
	public static final String KEY_CREATE_CODE = "create_code";
	public static final String KEY_CREATE_DATE = "create_date";
	public static final String KEY_EDITOR_CODE = "editor_code";
	public static final String KEY_EDITOR_DATE = "editor_date";
	public static final String KEY_BILLID = "billid";
}
