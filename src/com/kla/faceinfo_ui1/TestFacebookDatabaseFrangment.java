package com.kla.faceinfo_ui1;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class TestFacebookDatabaseFrangment extends Fragment{
	public TestFacebookDatabaseFrangment(){}
	
	Button btnLogin;
	Button btnReset;
	TextView txtDetail;
	MainActivity ma;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.activity_testfacebook, container,false);
		
		ma = (MainActivity) this.getActivity();
		
		btnLogin = (Button) rootView.findViewById(R.id.login_facebook_button);
		btnReset = (Button) rootView.findViewById(R.id.reset_button);
		txtDetail = (TextView) rootView.findViewById(R.id.facebook_detail_text);
		
		

		btnLogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				txtDetail.setText("Login");
				System.out.println("Login");
				
			}
		});
		
		btnReset.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				txtDetail.setText("Reset");
				System.out.println("Reset");
			}
		});
		
		
		
		
		return rootView;
	}

}
