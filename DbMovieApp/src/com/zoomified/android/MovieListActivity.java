package com.zoomified.android;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import com.zoomified.android.dao.MovieDAO;

public class MovieListActivity extends ListActivity{

	private MovieDAO dao;
	
	private SimpleCursorAdapter dbAdapter;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dao = new MovieDAO(this);
        Cursor moviesList = dao.fetchAllMovies();
        String[] from = new String[]{MovieDAO.MOVIE_NAME,MovieDAO.MOVIE_DESC};
        int[] target = new int[]{R.id.movieNameHolder,R.id.movieDescHolder};
        dbAdapter = new SimpleCursorAdapter(this,R.layout.movie_row_def,moviesList,from,target);
        setListAdapter(dbAdapter);
    }
}
