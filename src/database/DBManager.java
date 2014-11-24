package database;

import java.util.ArrayList;
import java.util.List;

import com.sromku.simple.fb.entities.Profile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.webkit.WebChromeClient.CustomViewCallback;

public class DBManager extends SQLiteOpenHelper {
	private static String Schema = "faceinfo";
	

	public DBManager(Context context) {
		super(context, Schema, null, 1);
		
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sqlContacts = "CREATE TABLE `contacts` " +
				"(`con_num` INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
				"`con_name` VARCHAR(45) NOT NULL," +
				"`con_tel` VARCHAR(45) NULL," +
				"`con_address` VARCHAR(100) NULL," +
				"`con_face_id` VARCHAR(45) NULL," +
				" `con_face_name` VARCHAR(45) NULL," +
				"`con_face_url` VARCHAR(100) NULL," +
				" `con_email` VARCHAR(45) NULL," +
				"`con_fullname` VARCHAR(45) NULL," +
				" `con_birthday` DATETIME NULL," +
				"`con_api_id` VARCHAR(45) NULL," +
				"`con_time_create` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
				"`con_time_lastupdate` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," +
				" `con_photo_num` VARCHAR(45) NULL," +
				" `con_other` VARCHAR(200) NULL);";
		
		db.execSQL(sqlContacts);
		Log.d("CREATE TABLE","Create Contacts Successfully.");
		
		String sqlPhotos = "CREATE TABLE `photos`" +
				" (`photo_num` INTEGER PRIMARY KEY AUTOINCREMENT  NOT NULL," +
				"`photo_api_id` VARCHAR(100) NULL," +
				"`photo_api_detail` VARCHAR(200) NULL," +
				"`photo_path` VARCHAR(100) NULL," +
				"`photo_name` VARCHAR(45) NULL," +
				" `photo_con_num` INT NOT NULL);"; 

		
		db.execSQL(sqlPhotos);
		Log.d("CREATE TABLE","Create Photos Successfully.");


		
	}
	
	public void insertContactsFacebook(Profile profile){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("con_name", profile.getName());
		values.put("con_face_id", profile.getName());
		values.put("con_email", profile.getName());
		values.put("con_face_name", profile.getName());
		db.insert("contacts", null, values);
		db.close();
	}
	
	public Photo selectPhoto(String photo_num){
		SQLiteDatabase db = this.getReadableDatabase();
		Photo photo = new Photo();
		String sql = "select photo_num,photo_name,photo_path,photo_con_num from photos" +
				" where photo_num = '"+photo_num +"';";
		Cursor cursor =  db.rawQuery(sql, null);
		if(cursor != null){
			cursor.moveToNext();
			photo.setPhoto_num(cursor.getString(0));
			photo.setPhoto_name(cursor.getString(1));
			photo.setPhoto_path(cursor.getString(2));
			photo.setPhoto_con_num(cursor.getString(3));
		}
		
		
		return photo;
	}
	
	public List<Contact> selectContactsAll(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<Contact> listContacts = new ArrayList<Contact>();
		String sql = "select con_num,con_name,con_photo_num from contacts";
		Cursor cursor =  db.rawQuery(sql, null);
		if(cursor != null){
			while(cursor.moveToNext()){
				Contact c = new Contact();
				c.setCon_num(cursor.getString(0));
				c.setCon_name(cursor.getString(1));
				c.setCon_photo_num(cursor.getString(2));
				listContacts.add(c);
			}
		}
		cursor.close();
		db.close();
		return listContacts;
	}
	
	public List<String> selectContacts(){
		SQLiteDatabase db = this.getReadableDatabase();
		List<String> resultList = new ArrayList<String>();
		
		String sql = "select con_name from contacts"; //+ where face_id = '1'
		Cursor cursor =  db.rawQuery(sql, null/*selectcolum*/);//or db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
		if(cursor != null){
			while(cursor.moveToNext()){
				resultList.add(cursor.getString(0));
			}
		}
		cursor.close();
		db.close();
		return resultList;
	}
	public void updateContacts(String face_id,String NewName,String NewTel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", NewName);
		values.put("Tel", NewTel);
		db.update("contacts", values,"face_id = ?",new String[]{face_id} );
		db.close();
	}
	public void deleteContacts(String face_id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("contacts", "face_id = ?",new String[] {face_id});
		db.close();
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
	public void dummyDb(){
		SQLiteDatabase db = this.getWritableDatabase();
		String sql1 = "INSERT INTO `photos` (`photo_num`, `photo_path`, `photo_name`, `photo_con_num`) VALUES ('1', '/sdcard/Faceinfo/coach.jpg', 'coach.jpg', '1');";
		String sql2 = "INSERT INTO `photos` (`photo_num`, `photo_path`, `photo_name`, `photo_con_num`) VALUES ('2', '/sdcard/Faceinfo/kla.jpg', 'kla.jpg', '2');";
		String sql3 = "INSERT INTO `photos` (`photo_num`, `photo_path`, `photo_name`, `photo_con_num`) VALUES ('3', '/sdcard/Faceinfo/jay.jpg', 'jay.jpg', '3');";
		
		String sql4 = "INSERT INTO `contacts` (`con_num`, `con_name`, `con_photo_num`) VALUES ('1', 'Coach', '1');";
		String sql5 = "INSERT INTO `contacts` (`con_num`, `con_name`, `con_photo_num`) VALUES ('2', 'Kla', '2');";
		String sql6 = "INSERT INTO `contacts` (`con_num`, `con_name`, `con_photo_num`) VALUES ('3', 'Jay', '3');";
		



		
		db.execSQL(sql1);	
		db.execSQL(sql2);
		db.execSQL(sql3);
		db.execSQL(sql4);	
		db.execSQL(sql5);
		db.execSQL(sql6);
		System.out.println("SSSDSJIJDSKDSHDJSHJK");
		
	}
	


}
