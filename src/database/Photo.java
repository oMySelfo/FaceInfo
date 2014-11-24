package database;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class Photo {
	String photo_num;
	String photo_api_id;
	String photo_api_detail;
	String photo_path;
	String photo_name;
	String photo_con_num;
	
	Bitmap photo_bitmap;
	public String getPhoto_num() {
		return photo_num;
	}
	public void setPhoto_num(String photo_num) {
		this.photo_num = photo_num;
	}
	public String getPhoto_api_id() {
		return photo_api_id;
	}
	public void setPhoto_api_id(String photo_api_id) {
		this.photo_api_id = photo_api_id;
	}
	public String getPhoto_api_detail() {
		return photo_api_detail;
	}
	public void setPhoto_api_detail(String photo_api_detail) {
		this.photo_api_detail = photo_api_detail;
	}
	public String getPhoto_path() {
		return photo_path;
	}
	public void setPhoto_path(String photo_path) {
		this.photo_path = photo_path;
	}
	public String getPhoto_name() {
		return photo_name;
	}
	public void setPhoto_name(String photo_name) {
		this.photo_name = photo_name;
	}
	public String getPhoto_con_num() {
		return photo_con_num;
	}
	public void setPhoto_con_num(String photo_con_num) {
		this.photo_con_num = photo_con_num;
	}
	
	
	public Bitmap getBitmap(){
		File imgFile = new  File("/storage/emulated/0/Faceinfo/Img/coach.jpg");

		if(imgFile.exists()){
		    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		    return myBitmap;
		}
		return null;
		
	}

}
