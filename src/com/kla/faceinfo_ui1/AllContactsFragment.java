package com.kla.faceinfo_ui1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class AllContactsFragment extends Fragment {
	public AllContactsFragment() {}

	FaceProcessing fp;
	Uri uri;
	MainActivity ma;
	public static final int REQUEST_CAMERA = 2;
	JSONObject result;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.activity_allcontacts,
				container, false);
		ma = (MainActivity) this.getActivity();
		fp = ma.getFaceProcessing();//////////////////////////////////
		result = ma.getResult();
		
		
		TextView tv = (TextView) rootView.findViewById(R.id.amount_friends);
		tv.setTypeface(ma.tf);
		tv.setText(ma.data.length + " FRIENDS");

		String[] from = { "pic", "name" };
		int[] to = { R.id.pic, R.id.txtName };

		List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < ma.data.length; i++) {
			HashMap<String, String> hm = new HashMap<String, String>();
			hm.put("name", ma.data[i][0]);
			hm.put("pic", ma.data[i][1] + "");
			aList.add(hm);
		}

		SimpleAdapter adapter = new SimpleAdapter(ma, aList,
				R.layout.activity_row, from, to);
		ListView listView = (ListView) rootView.findViewById(R.id.listview);
		listView.setAdapter(adapter);
		
		
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View container, int position, long id) {
                LinearLayout linearLayoutParent = (LinearLayout) container;
                LinearLayout linearLayoutChild = (LinearLayout ) linearLayoutParent.getChildAt(1);

                TextView tvEN = (TextView) linearLayoutChild.getChildAt(0);
                String tv = tvEN.getText().toString();
                System.out.println(tv);
            }
        });

		//Cick find button
		ImageButton findbtt = (ImageButton) rootView.findViewById(R.id.findbtt);
//		if (rootView == null) System.out.println("sdfdsfsfdssfdsfdsfsdfdsfdsfsdgdgergfgssf");
		findbtt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
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
		});
		return rootView;
	}
	

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
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
                ma.setResult(result);
                ma.displayView(9);
                
            } catch (Exception e) {
                 e.printStackTrace();
            }
        }
		super.onActivityResult(requestCode, resultCode, data);
	}

	
	
}
