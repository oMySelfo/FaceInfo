package com.kla.faceinfo_ui1;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AddINFOFragment extends Fragment {
	public AddINFOFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_addinfo, container,
				false);

		Button bt_field = (Button) rootView.findViewById(R.id.bt_field);
		registerForContextMenu(bt_field);
		bt_field.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Button) v).showContextMenu();
			}
		});
		return rootView;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		//menu.setHeaderTitle("Context Menu");
		menu.add(0, v.getId(), 0, "Full Name");
		menu.add(0, v.getId(), 0, "Facebook");
		menu.add(0, v.getId(), 0, "Twitter");
		menu.add(0, v.getId(), 0, "Email");
		menu.add(0, v.getId(), 0, "Birthday");
		menu.add(0, v.getId(), 0, "Address");
		menu.add(0, v.getId(), 0, "Others");
	}

	public boolean onContextItemSelected(MenuItem item) {
		if (item.getTitle() == "Full Name") {
			System.out.println("Full Name");
		} else if (item.getTitle() == "Facebook") {
			
		} else if (item.getTitle() == "Twitter") {
			
		} else {
			return false;
		}
		return true;
	}

}
