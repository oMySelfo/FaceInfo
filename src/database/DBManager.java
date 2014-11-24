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
				" `con_photo_id` VARCHAR(45) NULL," +
				" `con_other` VARCHAR(200) NULL);";
		
		
		db.execSQL(sqlContacts);
		Log.d("CREATE TABLE","Create Contacts Successfully.");
		

		
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
	


}
