package com.kla.faceinfo_ui1;

import java.util.ArrayList;
import java.util.List;

import widget.CreateEdittext;
import android.app.Fragment;
import android.content.res.Resources;
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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AddINFOFragment extends Fragment {
	public AddINFOFragment() {}
	
	CreateEdittext edittext;
	MainActivity ma;
	List<CreateEdittext> edit;
	View rootView;
	Button btn;
	String[] Namemenu = new String[]{"Full Name","Facebook","Twitter","Birthday","Address","Others"};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.activity_addinfo, container,
				false);
		edit = new ArrayList<CreateEdittext>();
		ma = (MainActivity) this.getActivity();
		for(int i=0;i<Namemenu.length;i++){
			edittext = new CreateEdittext(ma);
			edittext.setId(i);
			edittext.setWidth(dpToPx(340));
			edittext.setHint(Namemenu[i]);
			edittext.setShow(false);
			edit.add(edittext);
		}
		
		btn= new Button(ma);
		btn.setText("Add another field");
		registerForContextMenu(btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Button) v).showContextMenu();
				// TODO Auto-generated method stub
				
			}
		});
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout);
		layout.addView(btn);
		return rootView;
	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		//menu.setHeaderTitle("Context Menu");
		menu.add(0, v.getId(), 0, "Full Name");
		menu.add(0, v.getId(), 0, "Facebook");
		menu.add(0, v.getId(), 0, "Twitter");
		menu.add(0, v.getId(), 0, "Birthday");
		menu.add(0, v.getId(), 0, "Address");
		menu.add(0, v.getId(), 0, "Others");
	}

	public boolean onContextItemSelected(MenuItem item) {
		for(int i=0;i< Namemenu.length;i++){
			if(item.getTitle().equals(Namemenu[i])){
				if(!edit.get(i).isShow()){
					edit.get(i).setShow(true);
					LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.layout);
					TextView tv = new TextView(ma);
					tv.setText(Namemenu[i]);
					tv.setTextSize(20);
    
					LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					llp.setMargins(20, 0, 0, 0); // llp.setMargins(left, top, right, bottom);
					tv.setLayoutParams(llp);
					edit.get(i).setLayoutParams(llp);
					layout.removeView(btn);
					layout.addView(tv);
					layout.addView(edit.get(i));
					layout.addView(btn);
					
					}
			}
			
		}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 

		return true;
	}
	public int dpToPx(int dp)
	{
	    return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}

}
