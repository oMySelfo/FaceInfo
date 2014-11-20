package com.kla.faceinfo_ui1;



import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AddINFOFragment extends Fragment{
	public AddINFOFragment(){}
	public static final int REQUEST_CAMERA = 2;
    Uri uri;

    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_addinfo, container,false);
		


		return rootView;
	}
	

}

