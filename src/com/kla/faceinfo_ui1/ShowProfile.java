package com.kla.faceinfo_ui1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ShowProfile extends Fragment{

	public ShowProfile(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.acitivity_showprofile, container,false);
		ImageButton edit = (ImageButton) rootView.findViewById(R.id.profile_edit);
		
		setHasOptionsMenu(true);
		return rootView;
	}
	
	
}
