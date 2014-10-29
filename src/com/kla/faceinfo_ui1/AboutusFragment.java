package com.kla.faceinfo_ui1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class AboutusFragment extends Fragment{
	public AboutusFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_aboutus, container,false);
		return rootView;
	}

}
