package com.kla.faceinfo_ui1;

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
	private String tableName = "members";
	

	public DBManager(Context context) {
		super(context, Schema, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+tableName+" " +"(face_id TEXT(100) PRIMARY KEY AUTOINCREMENT," +" Name TEXT(100)," +" Tel TEXT(100));");

		Log.d("CREATE TABLE","Create Table Successfully.");

		
	}
	
	public void insertData(String face_id,String name,String Tel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("face_id", face_id);
		values.put("Name", name);
		values.put("Tel", Tel);
		db.insert(tableName, null, values);
		db.close();
	}
	
	public void selectData(String face_id){
		SQLiteDatabase db = this.getReadableDatabase();
		
		String sql = "select * from "+tableName; //+ where face_id = '1'
		Cursor cursor =  db.rawQuery(sql, null/*selectcolum*/);//or db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy, limit)
		if(cursor != null){
			while(cursor.moveToNext()){
				System.out.println(cursor.getString(0) +" : "+cursor.getString(1) +" : "+cursor.getString(2));
			}
		}
		cursor.close();
		db.close();
	}
	public void updateData(String face_id,String NewName,String NewTel){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("Name", NewName);
		values.put("Tel", NewTel);
		db.update(tableName, values,"face_id = ?",new String[]{face_id} );
		db.close();
	}
	public void deleteData(String face_id){
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(tableName, "face_id = ?",new String[] {face_id});
		db.close();
	}
	

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
