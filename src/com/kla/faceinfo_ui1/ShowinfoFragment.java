package com.kla.faceinfo_ui1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ShowinfoFragment extends Fragment{
	public ShowinfoFragment(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_showinfo, container,false);
		ImageView im_accuracy1 =  (ImageView)rootView.findViewById(R.id.im_accuracy1);
		ImageView im_accuracy2 =  (ImageView)rootView.findViewById(R.id.im_accuracy2);
		ImageView im_accuracy3 =  (ImageView)rootView.findViewById(R.id.im_accuracy3);
		
		TextView txt_accuracy1 = (TextView)rootView.findViewById(R.id.txt_accuracy1);
		TextView txt_accuracy2 = (TextView)rootView.findViewById(R.id.txt_accuracy2);
		TextView txt_accuracy3 = (TextView)rootView.findViewById(R.id.txt_accuracy3);
		
		
		return rootView;
	}
}
