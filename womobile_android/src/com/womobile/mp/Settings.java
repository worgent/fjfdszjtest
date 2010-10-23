/*
 * Copyright (C) 2009 The Sipdroid Open Source Project
 * 
 * This file is part of Sipdroid (http://www.sipdroid.org)
 * 
 * Sipdroid is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This source code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this source code; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.womobile.mp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener, OnClickListener {

	// Current settings handler
	private static SharedPreferences settings;
	// Context definition
	private Settings context = null;

	// Path where to store all profiles - !!!should be replaced by some system variable!!!
	private final static String profilePath = "/sdcard/wombile/";
	// Path where is stored the shared preference file - !!!should be replaced by some system variable!!!
	private final String sharedPrefsPath = "/data/data/com.womobile/shared_prefs/";
	// Shared preference file name - !!!should be replaced by some system variable!!!
	private final String sharedPrefsFile = "com.womobile.conf_preferences";
	// List of profile files available on the SD card
	private String[] profileFiles = null;
	// Which profile file to delete
	private int profileToDelete;
	
	public static final String PREF_USERNAME = "username";
	public static final String PREF_PASSWORD = "password";
	
	public static final String	DEFAULT_USERNAME = "";
	public static final String	DEFAULT_PASSWORD = "";
	
	// IDs of the menu items
	private static final int MENU_IMPORT = 0;
	private static final int MENU_DELETE = 1;

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferences);
		setDefaultValues();
	}
	
	private void setDefaultValues() {
		settings = getSharedPreferences(sharedPrefsFile, MODE_PRIVATE);
		settings.registerOnSharedPreferenceChangeListener(this);
	}

	
	/**
	 * Purpose      : 说明
	 * @param arg0
	 * @param arg1 
	 */
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		// TODO Auto-generated method stub
		/*
		if(key.equals(PREF_USERNAME)){
			//sharedPreferences.getString(key, defValue)
			//setSettingsTitle();
		}
		*/
		updateSummaries();
		// Export settings only if there is some username and server
		if (! settings.getString(PREF_USERNAME, "").equals("")) {
			exportSettings();
		}
		
	}

	public void updateSummaries(){
		getPreferenceScreen().findPreference(PREF_USERNAME).setSummary(settings.getString(PREF_USERNAME, DEFAULT_USERNAME));
		getPreferenceScreen().findPreference(PREF_PASSWORD).setSummary(settings.getString(PREF_PASSWORD, DEFAULT_PASSWORD));
	}
	
	//自动文件名:用户名
    private String getProfileNameString() {
    	return getProfileNameString(settings);
    }
    
    public static String getProfileNameString(SharedPreferences s) {
    	String provider = s.getString(PREF_USERNAME, DEFAULT_USERNAME);

    	if (! s.getString(PREF_USERNAME, "").equals("")) {
    		provider = s.getString(PREF_USERNAME, DEFAULT_USERNAME);
    	}

    	return s.getString(PREF_USERNAME, DEFAULT_USERNAME) + "@" + provider;
    }
    
    //导出
    private void exportSettings() {
        try {
        	// Create the directory for the profiles
        	new File(profilePath).mkdirs();

        	// Copy shared preference file on the SD card
        	copyFile(new File(sharedPrefsPath + sharedPrefsFile + ".xml"), new File(profilePath + getProfileNameString()));
        } catch (Exception e) {
//            Toast.makeText(this, getString(R.string.settings_profile_export_error), Toast.LENGTH_SHORT).show();
        }
    }
    //文件复制
    public void copyFile(File in, File out) throws Exception {
        FileInputStream  fis = new FileInputStream(in);
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while ((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (fis != null) fis.close();
            if (fos != null) fos.close();
        }
    }
	/**
	 * Purpose      : 说明
	 * @param arg0
	 * @param arg1 
	 */
	@Override
	public void onClick(DialogInterface arg0, int arg1) {
		Editor edit = settings.edit();
		edit.commit();
	}
	//====================================================
	//动态菜单
    public boolean onCreateOptionsMenu(Menu menu) {
    	// Get the content of the directory
    	profileFiles = getProfileList();

    	// Create menu items - show only if there are already some profiles
        if (profileFiles != null && profileFiles.length > 0) {
	        menu.add(0, MENU_IMPORT, 0, getString(R.string.btnimport)).setIcon(android.R.drawable.ic_menu_upload);
        	menu.add(0, MENU_DELETE, 0, getString(R.string.btndelete)).setIcon(android.R.drawable.ic_menu_delete);
        }

        return true;
    }
    public static String[] getProfileList() {
    	File dir = new File(profilePath);
    	return dir.list();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
    	context = this;

    	switch (item.getItemId()) {
            case MENU_IMPORT:
            	// Get the content of the directory
            	profileFiles = getProfileList();
            	if (profileFiles != null && profileFiles.length > 0) {
	            	// Show dialog with the files
	    			new AlertDialog.Builder(this)
	    			.setTitle(getString(R.string.btnimport))
	    			.setIcon(android.R.drawable.ic_menu_upload)
	    			.setItems(profileFiles, profileOnClick)
	    			.show();
            	} else {
	                Toast.makeText(this, "No profile found.", Toast.LENGTH_SHORT).show();
            	}
                return true;

            case MENU_DELETE:
            	// Get the content of the directory
            	profileFiles = getProfileList();
            	new AlertDialog.Builder(this)
                .setTitle(getString(R.string.btndelete))
                .setIcon(android.R.drawable.ic_menu_delete)
    			.setItems(profileFiles, new DialogInterface.OnClickListener() {
    				// Ask the user to be sure to delete it
    				public void onClick(DialogInterface dialog, int whichItem) {
        				profileToDelete = whichItem;
    					new AlertDialog.Builder(context)
    	                .setIcon(android.R.drawable.ic_dialog_alert)
    	                .setTitle(getString(R.string.btndelete))
    	                .setMessage(getString(R.string.deletemsg, profileFiles[whichItem]))
    	                .setPositiveButton(android.R.string.ok, deleteOkButtonClick)
    	                .setNegativeButton(android.R.string.cancel, null)
    	                .show();
    				}
    			})
                .show();
                return true;
        }

        return false;
    }   
    
	private OnClickListener profileOnClick = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichItem) {
			try {
				copyFile(new File(profilePath + profileFiles[whichItem]), new File(sharedPrefsPath + sharedPrefsFile + ".xml"));
            } catch (Exception e) {
                Toast.makeText(context, getString(R.string.importmsg), Toast.LENGTH_SHORT).show();
                return;
            }

   			setDefaultValues();
    
   			settings.unregisterOnSharedPreferenceChangeListener(context);
   			settings.registerOnSharedPreferenceChangeListener(context);
		}
	};

	private OnClickListener deleteOkButtonClick = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
        	File profile = new File(profilePath + profileFiles[profileToDelete]);
        	boolean rv = false;
        	// Check if the file exists and try to delete it
        	if (profile.exists()) {
        		rv = profile.delete();
        	}
        	if (rv) {
        		Toast.makeText(context, getString(R.string.importmsg), Toast.LENGTH_SHORT).show();
        	} else {
        		Toast.makeText(context, getString(R.string.deletemsg), Toast.LENGTH_SHORT).show();
        	}
		}
	};
}