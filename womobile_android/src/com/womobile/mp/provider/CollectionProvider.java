/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2001 The Apache Software Foundation.  All rights
 * reserved.
 */

package com.womobile.mp.provider;

import com.womobile.mp.entities.*;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.util.Log;

public class CollectionProvider  extends ContentProvider{

	
	public static final String CONTENT_URI = "com.womobile.mp.provider.CollectionProvider";

	private static final String TAG = "CollectionProvider";
	private static final String DATABASE_NAME = "AllTables.db";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_TABLE_NAME = "collection";

	private static final String DATABASE_CREATE = "create table if not exists "
			+ DATABASE_TABLE_NAME + "("
			+ Collection.KEY_ID + " varchar(20) , "
			+ Collection.KEY_TITLE + " varchar(20) , "
			+ Collection.KEY_DETAIL + " varchar(20) , "
			+ Collection.KEY_ADJUNCT + " varchar(20) , "
			+ Collection.KEY_TARGET + " varchar(20) , "
			+ Collection.KEY_FORMAT + " varchar(20) , "
			+ Collection.KEY_PROID + " varchar(20) , "
			+ Collection.KEY_ATTITUDE + " varchar(20) , "
			+ Collection.KEY_GRADE + " varchar(20) , "
			+ Collection.KEY_ISEND + " varchar(20) , "
			+ Collection.KEY_FLOWER_STATE + " varchar(20) , "
			+ Collection.KEY_PROCESS_CODE + " varchar(20) , "
			+ Collection.KEY_AUDITING_CODE + " varchar(20) , "
			+ Collection.KEY_BILL_STATE + " varchar(20) , "
			+ Collection.KEY_CREATE_CODE + " varchar(20) , "
			+ Collection.KEY_CREATE_DATE + " varchar(20) , "
			+ Collection.KEY_EDITOR_CODE + " varchar(20) , "
			+ Collection.KEY_EDITOR_DATE + " varchar(20) , "
			+ Collection.KEY_FEEDBKID + " varchar(20) "
			+")";
		
	private DatabaseHelper collectionHelper = null;

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		if (collectionHelper == null)
			collectionHelper = new DatabaseHelper(getContext());
		SQLiteDatabase db = collectionHelper.getWritableDatabase();
		return db.delete(DATABASE_TABLE_NAME, selection, selectionArgs);
	}

	@Override
	public String getType(Uri uri) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		// TODO Auto-generated method stub
		Log.i(TAG, "step into insert");
		SQLiteDatabase sqlDB = collectionHelper.getWritableDatabase();
		long rowId = sqlDB.insert(DATABASE_TABLE_NAME, "", values);
		Log.i("rowId is", rowId + "");
		if (rowId > 0) {
			Uri rowUri = ContentUris.appendId(Collection.CONTENT_URI.buildUpon(),
					rowId).build();
			getContext().getContentResolver().notifyChange(rowUri, null);
			return rowUri;
		}
		throw new SQLException("Failed to insert row into " + uri);
	}

	@Override
	public boolean onCreate() {
		Log.i(TAG, "step into onCreate");
		if (collectionHelper == null)
			collectionHelper = new DatabaseHelper(getContext());
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		if (collectionHelper == null)
			collectionHelper = new DatabaseHelper(getContext());
		SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
		SQLiteDatabase db = collectionHelper.getWritableDatabase();
		qb.setTables(DATABASE_TABLE_NAME);
		Cursor c = qb.query(db, projection, selection, selectionArgs, null,
				null, sortOrder);
		c.setNotificationUri(getContext().getContentResolver(), uri);
		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO Auto-generated method stub
		if (collectionHelper == null)
			collectionHelper = new DatabaseHelper(getContext());
		SQLiteDatabase db = collectionHelper.getWritableDatabase();
		return db.update(DATABASE_TABLE_NAME, values, selection, selectionArgs);
	}

			
	
	
	
	
		private static class DatabaseHelper extends SQLiteOpenHelper {

		private SQLiteDatabase db = null;
		private Context ctx = null;

		public DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			ctx = context;
			exist(DATABASE_NAME);
			db = openDatabase(DATABASE_NAME);
			createtable(db);
			// TODO Auto-generated constructor stub
		}

		private void createtable(SQLiteDatabase db) {
//			 db.execSQL("drop table orderinfo");
			db.execSQL(DATABASE_CREATE);
			// if (getAllCount(db) == 0)
			// initData(db);
			Log.i(TAG, "Table created...");

		}

		private void initData(SQLiteDatabase db) {
			Log.i(TAG, "initData...");
			/*
			db
					.execSQL("INSERT INTO "
							+ DATABASE_TABLE_NAME
							+ " (orderid,brandcode,brandcount,date,format,amount,agencyid,description,status,vipid,recieve)"
							+ " VALUES ('O-10','七匹狼',10,'2010-6-11','条','450','1','好烟','0','1','0')");
		     */
		}

		private int getAllCount(SQLiteDatabase db) {

			Cursor cursor = db.query(DATABASE_TABLE_NAME, null, null, null,
					null, null, null);
			int count = cursor.getCount();
			cursor.close();
			return count;
		}

		private SQLiteDatabase openDatabase(String databaseName) {
			db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
			return db;
		}

		private boolean exist(String databaseName) {
			// TODO Auto-generated method stub
			Log.i(TAG, "called fun : exist()");
			boolean flag = false;
			try {
				db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
				Log.i(TAG, "database/" + databaseName + " exist");
				flag = true;
			} finally {
				if (db != null)
					db.close();
				db = null;
			}
			return flag;
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}
	}
		
		
		
}