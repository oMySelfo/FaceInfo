package com.kla.faceinfo_ui1;

import facebook.TestFacebookDatabaseFrangment;
import group.AddGroupFragment;
import group.PeopleOfGroup;

import group.GrouplistFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;


import com.sromku.simple.fb.SimpleFacebook;
import com.kla.faceinfo_ui1.AllContactsFragment;
import database.DBManager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;


public class MainActivity extends Activity {
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private DrawerLayout mDrawerLayout;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	private String[] navMenuTitles;
	private FaceProcessing fp;
	Typeface tf;
	public JSONObject result;
	private SimpleFacebook mSimpleFacebook;
	public String namegroup2;
	private DBManager dbManager;



	public String profilename;
	


	public String[][] data = new String[][] { { "Fah", R.drawable.fah + "" },
			{ "Jay", R.drawable.jay + "" }, { "Kla", R.drawable.kla + "" },
			{ "Mhee", R.drawable.mhee + "" },
			{ "P'Mike", R.drawable.mike + "" },
			{ "Plam", R.drawable.palm + "" }, { "Tae", R.drawable.tae + "" },
			{ "Coach", R.drawable.coach + "" } };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tf = Typeface.createFromAsset(getAssets(), "SukhumvitSet.ttc");

		dbManager = new DBManager(this);
		
		mSimpleFacebook = SimpleFacebook.getInstance(this);


		mTitle = mDrawerTitle = getTitle();

		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		showListFriends();

		if (savedInstanceState == null) {
			// on first time display view
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, new AllContactsFragment())
					.commit();
		}

		new Thread(new Runnable() {
			public void run() {
				fp = new FaceProcessing("kla7016");
			}
		}).start();

	}

	private void showListFriends() {
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.app_name, R.string.app_name) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				// calling onPrepareOptionsMenu() to show action bar icons
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				// calling onPrepareOptionsMenu() to hide action bar icons
				invalidateOptionsMenu();
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		SetMenuSliding();
	}

	public void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new AllContactsFragment();
			break;
		case 1:
			fragment = new AboutusFragment();
			break;
		case 2:
			fragment = new TestFacebookDatabaseFrangment();
			break;
		case 3:
			fragment = new GrouplistFragment();
			break;
		case 9:
			fragment = new ShowinfoFragment();
			break;
		case 10:
			fragment = new AddINFOFragment();
			break;
		case 11:
			fragment = new AddGroupFragment();
			break;
		case 12:
			fragment = new PeopleOfGroup();
			break;
		case 13:
			fragment = new ShowProfile();
			break;
		default:
			break;
		}
		if (fragment != null) {
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null)
					.replace(R.id.frame_container, fragment).commit();

			mDrawerList.setItemChecked(position, true);

			if (position <= 8) {
				setTitle(navMenuTitles[position]);
			} else if (position == 10) {
				setTitle("AddINFO");
			} else if (position == 11) {
				setTitle("AddGroup");
			} 
			mDrawerList.setSelection(position);
			mDrawerLayout.closeDrawer(mDrawerList);
		} else {
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	private void SetMenuSliding() {
		String[][] dataSliding = new String[][] {
				{ R.drawable.ic_contacts + "", "All Contacts" },
				{ R.drawable.ic_aboutus + "", "About Us" },
				{ R.drawable.ic_aboutus + "", "Test" },
				{ R.drawable.ic_group + "", "Group" } };
		String[] from = { "icon", "title" };
		int[] to = { R.id.icon, R.id.title };
		List<HashMap<String, String>> listSliding = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < dataSliding.length; i++) { // Test
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("icon", dataSliding[i][0]);
			hm.put("title", dataSliding[i][1]);
			listSliding.add(hm);
		}
		SimpleAdapter adapterSliding = new SimpleAdapter(this, listSliding,
				R.layout.drawer_list_item, from, to);

		mDrawerList.setAdapter(adapterSliding);
	}

	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			displayView(position);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}

		// Handle action bar actions click
		switch (item.getItemId()) {
		// case R.id.action_settings:
		// return true;
		case R.id.bt_plus:
			displayView(10);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public FaceProcessing getFaceProcessing() {
		return fp;
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(mTitle);
	}

	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject res) {
		this.result = res;
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSimpleFacebook = SimpleFacebook.getInstance(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		mSimpleFacebook.onActivityResult(this, requestCode, resultCode, data);
	}

	public DBManager getDbManager(){
		return dbManager;
		
	}
}
