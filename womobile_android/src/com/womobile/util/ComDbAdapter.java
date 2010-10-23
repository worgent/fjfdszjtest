package com.womobile.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
/**
 * This package is directly based on the Notepad Example by Google. 
 * You can find it at: http://developer.android.com/guide/tutorials/notepad/index.html
 * @author Nushio
 * 修改相关参数,作为自定义数据库操作类
 */
public class ComDbAdapter {
	//表字段定义(主键定义)
    public static String KEY_ROWID = "_id";//主键
    String[] fileds=new String[]{};//字段
    ContentValues initialValues=new ContentValues();//数据
    
    //Tag定义
    private static final String TAG = "AccountsDbAdapter";
    //数据库操作类
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    /**
     * Database creation sql statement
     */
    //数据库创建sql
    private  static String DATABASE_CREATE =
            "create table accounts (_id integer primary key autoincrement, "
                    + "username text not null, password text not null," 
                    + "server text not null);";
    //数据库名
    private  static String DATABASE_NAME = "womobile.db";
    //表名
    private  static String DATABASE_TABLE = "accounts";
    //版本
    private  static int DATABASE_VERSION = 1;

    //相关action
    private final Context mCtx;

    
    //与action相关的数据库操作类
    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL(DATABASE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS accounts");
            onCreate(db);
        }
    }

    /**
     * Constructor - takes the context to allow the database to be
     * opened/created
     * 
     * @param ctx the Context within which to work
     */
    public ComDbAdapter(Context ctx) {
        this.mCtx = ctx;
    }

    /**
     * Open the accounts database. If it cannot be opened, try to create a new
     * instance of the database. If it cannot be created, throw an exception to
     * signal the failure
     * 
     * @return this (self reference, allowing this to be chained in an
     *         initialization call)
     * @throws SQLException if the database could be neither opened or created
     */
    public ComDbAdapter open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }
    
    public void close() {
        mDbHelper.close();
    }


    /**
     * Create a new account using the user, pass and server provided. If the account is
     * successfully created return the new rowId for that account, otherwise return
     * a -1 to indicate failure.
     * 
     * @param username the title of the account
     * @param password the title of the account
     * @param server the title of the account
     * @return rowId or -1 if failed
     */
    public long createData() {
        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }
    
    /**
     * Delete the account with the given rowId
     * 
     * @param rowId id of account to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteData(long rowId) {
        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all accounts in the database. 
     * 
     * @return Cursor over all notes
     */
    public Cursor fetchAllData() {
        return mDb.query(DATABASE_TABLE, fileds, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the account that matches the given rowId
     * 
     * @param rowId id of account to retrieve
     * @return Cursor positioned to matching account, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchData(long rowId) throws SQLException {

        Cursor mCursor =
        		mDb.query(true, DATABASE_TABLE, fileds, KEY_ROWID + "=" + rowId, null,
                        null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    /**
     * Update the account using the details provided. The account to be updated is
     * specified using the rowId, and it is altered to use the username, password and server
     * values passed in
     * 
     * @param rowId id of account to update
     * @param username id of account to update
     * @param password id of account to update
     * @param server id of account to update
     * @return true if the note was successfully updated, false otherwise
     */
    public boolean updateData(long rowId) {
        return mDb.update(DATABASE_TABLE, initialValues, KEY_ROWID + "=" + rowId, null) > 0;
    }

	/**
	 * Purpose      : 说明
	 * @return the dATABASE_CREATE
	 */
	public static String getDATABASE_CREATE() {
		return DATABASE_CREATE;
	}

	/**
	 * Purpose      : 说明
	 * @param database_create the dATABASE_CREATE to set
	 */
	
	public static void setDATABASE_CREATE(String database_create) {
		DATABASE_CREATE = database_create;
	}

	/**
	 * Purpose      : 说明
	 * @return the dATABASE_NAME
	 */
	public static String getDATABASE_NAME() {
		return DATABASE_NAME;
	}

	/**
	 * Purpose      : 说明
	 * @param database_name the dATABASE_NAME to set
	 */
	
	public static void setDATABASE_NAME(String database_name) {
		DATABASE_NAME = database_name;
	}

	/**
	 * Purpose      : 说明
	 * @return the dATABASE_TABLE
	 */
	public static String getDATABASE_TABLE() {
		return DATABASE_TABLE;
	}

	/**
	 * Purpose      : 说明
	 * @param database_table the dATABASE_TABLE to set
	 */
	
	public static void setDATABASE_TABLE(String database_table) {
		DATABASE_TABLE = database_table;
	}

	/**
	 * Purpose      : 说明
	 * @return the dATABASE_VERSION
	 */
	public static int getDATABASE_VERSION() {
		return DATABASE_VERSION;
	}

	/**
	 * Purpose      : 说明
	 * @param database_version the dATABASE_VERSION to set
	 */
	
	public static void setDATABASE_VERSION(int database_version) {
		DATABASE_VERSION = database_version;
	}

	/**
	 * Purpose      : 说明
	 * @return the kEY_ROWID
	 */
	public static String getKEY_ROWID() {
		return KEY_ROWID;
	}

	/**
	 * Purpose      : 说明
	 * @param key_rowid the kEY_ROWID to set
	 */
	
	public static void setKEY_ROWID(String key_rowid) {
		KEY_ROWID = key_rowid;
	}

	/**
	 * Purpose      : 说明
	 * @return the fileds
	 */
	public String[] getFileds() {
		return fileds;
	}

	/**
	 * Purpose      : 说明
	 * @param fileds the fileds to set
	 */
	
	public void setFileds(String[] fileds) {
		this.fileds = fileds;
	}

	/**
	 * Purpose      : 说明
	 * @return the initialValues
	 */
	public ContentValues getInitialValues() {
		return initialValues;
	}

	/**
	 * Purpose      : 说明
	 * @param initialValues the initialValues to set
	 */
	
	public void setInitialValues(ContentValues initialValues) {
		this.initialValues = initialValues;
	}
}
