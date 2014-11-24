package com.kla.faceinfo_ui1;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

public class ShowProfile extends Fragment{
	boolean setedittxt;
	int show_id[][];
	View rootView ;
	ImageButton edit;
	ImageButton delete;

	public ShowProfile(){}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
	
		
		rootView = inflater.inflate(R.layout.acitivity_showprofile, container,false);
		show_id = new int[][]{{R.id.show_txt_name,R.id.show_edit_name}
										,{R.id.show_txt_phone,R.id.show_edit_phone}
										,{R.id.show_txt_email,R.id.show_edit_email}
										,{R.id.show_txt_fullname,R.id.show_edit_fullname}
										,{R.id.show_txt_facebook,R.id.show_edit_facebook}
										,{R.id.show_txt_twitter,R.id.show_edit_twitter}
										,{R.id.show_txt_birthday,R.id.show_edit_birthday}
										,{R.id.show_txt_address,R.id.show_edit_address}
										,{R.id.show_txt_other,R.id.show_edit_other}
										};
		edit = (ImageButton) rootView.findViewById(R.id.profile_edit);
		delete = (ImageButton) rootView.findViewById(R.id.profile_bin);
		setedittxt=false;
		setedit();
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(setedittxt){
					setedittxt=false;
					edit.setImageResource(R.drawable.edit_mini);
					
				}else{
					setedittxt=true;
					edit.setImageResource(R.drawable.save);
					
				}
				setedit();
				
				
			}
		});
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Alert
				//delete to database
				//go to all contacks page
				
			}
		});
	
		return rootView;
	}
	private void setedit(){
		for(int i=0;i<show_id.length;i++){
				EditText et = (EditText) rootView.findViewById(show_id[i][1]);
				et.setEnabled(setedittxt);	
		}
		if(!setedittxt){
			//Update to database
		}
	}
		
		
	
	
	
	
	
}
