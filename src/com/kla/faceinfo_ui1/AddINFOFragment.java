package com.kla.faceinfo_ui1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import widget.CreateEdittext;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class AddINFOFragment extends Fragment {
	public AddINFOFragment() {
	}

	FaceProcessing fp;
	JSONObject result;
	CreateEdittext edittext;
	MainActivity ma;
	List<CreateEdittext> edit;
	ImageButton take_picture;
	View rootView;
	Button btn;
	Button btn_Done;
	Uri uri;
	public static final int REQUEST_CAMERA = 2;

	String[] Namemenu = new String[] { "Full Name", "Facebook", "Twitter",
			"Birthday", "Address", "Others" };


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		

		rootView = inflater
				.inflate(R.layout.activity_addinfo, container, false);
		take_picture = (ImageButton) rootView.findViewById(R.id.add_pic);
		take_picture.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
			}
		});
		

		edit = new ArrayList<CreateEdittext>();
		ma = (MainActivity) this.getActivity();
		fp = ma.getFaceProcessing();
		for (int i = 0; i < Namemenu.length; i++) {
			edittext = new CreateEdittext(ma);
			edittext.setId(i);
			edittext.setWidth(dpToPx(340));
			edittext.setHint(Namemenu[i]);
			edittext.setShow(false);
			edit.add(edittext);
		}
		

		btn = new Button(ma);
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
		
		btn_Done = new Button(ma);
		btn_Done.setText("Done");
		btn_Done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}	
		});
		layout.addView(btn_Done);
		
		registerForContextMenu(take_picture);
		take_picture.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				((ImageButton) v).showContextMenu();
			}
		});
		

		return rootView;
	}
	
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub\
		if (requestCode == REQUEST_CAMERA && resultCode == ma.RESULT_OK) {
            ma.getContentResolver().notifyChange(uri, null); 
            ContentResolver cr = ma.getContentResolver();
            try {
                Bitmap bitmap = Media.getBitmap(cr, uri);
                //imageView.setImageBitmap(bitmap);
                System.out.println(bitmap.getWidth()+" "+bitmap.getHeight());
           
               
                Toast.makeText(ma.getApplicationContext()
                        , uri.getPath(), Toast.LENGTH_LONG).show();
                result = fp.FaceIdentify(bitmap);
                System.out.println(result.getJSONArray("face"));
                if(result.getJSONArray("face").length()==0 || result.getJSONArray("face").length()>1){
                	//Dialog
                }
                else{
                	take_picture.setImageBitmap(bitmap);
                	//
                }
                //ma.setResult(result);
                
               // ma.displayView(9);
                
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
		super.onActivityResult(requestCode, resultCode, data);
	}
	



	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		// menu.setHeaderTitle("Context Menu");
		if (v.getId() == -1) {
			menu.add(0, v.getId(), 0, "Full Name");
			menu.add(0, v.getId(), 0, "Facebook");
			menu.add(0, v.getId(), 0, "Twitter");
			menu.add(0, v.getId(), 0, "Birthday");
			menu.add(0, v.getId(), 0, "Address");
			menu.add(0, v.getId(), 0, "Others");
		} else {
			menu.add(0, v.getId(), 0, "TakePicture");
			menu.add(0,v.getId(),0,"Gallery");
		}
	}

	public boolean onContextItemSelected(MenuItem item) {
		for (int i = 0; i < Namemenu.length; i++) {
			if (item.getTitle().equals(Namemenu[i])) {
				if (!edit.get(i).isShow()) {
					edit.get(i).setShow(true);
					LinearLayout layout = (LinearLayout) rootView
							.findViewById(R.id.layout);
					TextView tv = new TextView(ma);
					tv.setText(Namemenu[i]);
					tv.setTextSize(20);

					LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(
							LayoutParams.WRAP_CONTENT,
							LayoutParams.WRAP_CONTENT);
					llp.setMargins(20, 0, 0, 0); // llp.setMargins(left, top,
													// right, bottom);
					tv.setLayoutParams(llp);
					edit.get(i).setLayoutParams(llp);
					
					layout.removeView(btn);
					layout.addView(tv);
					layout.addView(edit.get(i));
					layout.addView(btn);
					
					layout.removeView(btn_Done);
					layout.addView(btn_Done);					

				}
			}
		}if(item.getTitle().equals("TakePicture")){
			
			 Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             String timeStamp = 
                     new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
             String imageFileName = "IMG_" + timeStamp + ".jpg";
             File f = new File(Environment.getExternalStorageDirectory()
                     , "DCIM/Camera/" + imageFileName);
             uri = Uri.fromFile(f);
             it.putExtra(MediaStore.EXTRA_OUTPUT, uri);
             startActivityForResult(Intent.createChooser(it
                     , "Take a picture"), REQUEST_CAMERA);
			
		}
			

		

		return true;
	}

	public int dpToPx(int dp) {
		return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
	}

}
