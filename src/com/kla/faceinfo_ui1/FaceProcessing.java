package com.kla.faceinfo_ui1;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;



public class FaceProcessing {
	///test1234
	String ID = null;
	HttpRequests httpRequests;
	JSONObject result = null;
	String temp = null;
	public Bitmap bmp=null;

	public FaceProcessing(String ID){
		
		httpRequests = new HttpRequests("a5a3b214d4689870f34c11fb4d89d50b", "O42Yf4ExbRTyNrTDL4PKxWxsOMKLr3cz", false, true);
		this.ID = ID;
	}
	
	public void FaceIdentify(Bitmap bmp){
		System.out.println("____________________");
		System.out.println("____________________");
		System.out.println("____________________");
		System.out.println("____________________");
		this.bmp = bmp;
		System.out.println(bmp.getWidth() + "x" + bmp.getHeight());

		 new Thread(new Runnable() {
				public void run() {
					try {
						ByteArrayOutputStream stream = new ByteArrayOutputStream();
						FaceProcessing.this.bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
						byte[] byteArray = stream.toByteArray();
						System.out.println(httpRequests.trainIdentify(new PostParameters().setGroupName(ID)));
						result = httpRequests.recognitionIdentify(new PostParameters().setGroupName(ID).setImg(byteArray));
						System.out.println(result.getJSONArray("face").getJSONObject(0).getJSONArray("candidate").getJSONObject(0).get("person_name"));
						System.out.println("______________");
						System.out.println("______________");
						System.out.println("______________");
						System.out.println(result.getJSONArray("face").getJSONObject(0).getJSONArray("candidate"));
						
//						System.out.println(httpRequests.detectionDetect(new PostParameters().setImg(byteArray)));
						
					} catch (FaceppParseException e) {
						e.printStackTrace();
					}
					catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}).start();
		
		
		
	}
	
}
