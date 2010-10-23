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
 *
 */
public class AccountsDbAdapter {

	//表字段定义
    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_SERVER = "server";
    public static final String KEY_ROWID = "_id";

    //Tag定义
    private static final String TAG = "AccountsDbAdapter";
    //数据库操作类
    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;
    
    /**
     * Database creation sql statement
     */
    //数据库创建sql
    private static final String DATABASE_CREATE =
            "create table accounts (_id integer primary key autoincrement, "
                    + "username text not null, password text not null," 
                    + "server text not null);";

    private static final String DATABASE_NAME = "womobile.db";
    private static final String DATABASE_TABLE = "accounts";
    private static final int DATABASE_VERSION = 1;

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
    public AccountsDbAdapter(Context ctx) {
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
    public AccountsDbAdapter open() throws SQLException {
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
    public long createAccount(String username, String password, String server) {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_USERNAME, username);
        initialValues.put(KEY_PASSWORD, password);
        initialValues.put(KEY_SERVER, server);

        return mDb.insert(DATABASE_TABLE, null, initialValues);
    }

    /**
     * Delete the account with the given rowId
     * 
     * @param rowId id of account to delete
     * @return true if deleted, false otherwise
     */
    public boolean deleteAccount(long rowId) {

        return mDb.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;
    }

    /**
     * Return a Cursor over the list of all accounts in the database. 
     * 
     * @return Cursor over all notes
     */
    public Cursor fetchAllAccounts() {

        return mDb.query(DATABASE_TABLE, new String[] {KEY_ROWID, KEY_USERNAME,
                KEY_PASSWORD, KEY_SERVER}, null, null, null, null, null);
    }

    /**
     * Return a Cursor positioned at the account that matches the given rowId
     * 
     * @param rowId id of account to retrieve
     * @return Cursor positioned to matching account, if found
     * @throws SQLException if note could not be found/retrieved
     */
    public Cursor fetchAccount(long rowId) throws SQLException {

        Cursor mCursor =
        		mDb.query(true, DATABASE_TABLE, new String[] {KEY_ROWID,
                        KEY_USERNAME, KEY_PASSWORD, KEY_SERVER}, KEY_ROWID + "=" + rowId, null,
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
    public boolean updateAccount(long rowId, String username, String password, String server) {
        ContentValues args = new ContentValues();
        args.put(KEY_USERNAME, username);
        args.put(KEY_PASSWORD, password);
        args.put(KEY_SERVER, server);

        return mDb.update(DATABASE_TABLE, args, KEY_ROWID + "=" + rowId, null) > 0;
    }
}
