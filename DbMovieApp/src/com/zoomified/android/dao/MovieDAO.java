package com.zoomified.android.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MovieDAO {

	private DatabaseHelper dbHelper;
	
	private SQLiteDatabase database;
	/**
	 * Movie table related constants.
	 */
	public final static String MOVIE_TABLE="favorite_movie";
	
	public final static String MOVIE_NAME="name";
	
	public final static String MOVIE_DESC="description";
	public final static String MOVIE_ID="_id";
	/**
	 * 
	 * @param context
	 */
	public MovieDAO(Context context){
		dbHelper = new DatabaseHelper(context);
		database = dbHelper.getWritableDatabase();
	}
	/**\
	 * Creates a new movie
	 * @param name
	 * @param desc
	 * @return
	 */
	public long createMovie(String name,String desc){
		ContentValues values = new ContentValues();
		values.put(MOVIE_NAME, name);
		values.put(MOVIE_DESC, desc);
		return database.insert(MOVIE_TABLE, null, values);
	}
	/**
	 * Fetch all movies
	 * @return
	 */
	public Cursor fetchAllMovies(){
		Cursor mCursor = database.query(true, MOVIE_TABLE, new String[] {
				MOVIE_NAME, MOVIE_DESC,MOVIE_ID},null
				, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}
	/**
	 * 
	 */
	public int updateMovie(String name,String desc,String id){
		ContentValues values = new ContentValues();
		values.put(MOVIE_NAME, name);
		values.put(MOVIE_DESC, desc);
		return database.update(MOVIE_TABLE, values,MOVIE_ID + "=?",new String[]{id});
	}
	/**
	 * Deletes a movie
	 * @param id
	 * @return
	 */
	public boolean delete(String id){
		return database.delete(MOVIE_TABLE, MOVIE_ID + "=" + id, null) > 0;
	}
}
