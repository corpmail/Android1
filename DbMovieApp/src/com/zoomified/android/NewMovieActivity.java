package com.zoomified.android;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.zoomified.android.dao.MovieDAO;
/**
 * Activity to create or edit new movie
 * @author 712806
 *
 */
public class NewMovieActivity extends Activity implements OnClickListener{
	
	private Button saveBtn = null;
	
	private MovieDAO dao = null;
	
	private String action = null;
	/**
	 * 
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_movie);
		saveBtn = (Button)findViewById(R.id.saveBtn);
		saveBtn.setOnClickListener(this);
		//Create a dao to handle new movies
		dao = new MovieDAO(this);
		action = getIntent().getExtras().getString("action");
	}
	/**
	 * 
	 */
	@Override
	public void onClick(View v) {
		int clickedElement = v.getId();
		if(clickedElement == R.id.saveBtn){
			if(action.equalsIgnoreCase("add")){
				addMovie();
			}
			
		}
	}
	
	private void addMovie(){
		//Add a new movie
		//Get movie name and description
		String name = ((TextView)findViewById(R.id.movieNameTxt)).getText().toString();
		String desc = ((TextView)findViewById(R.id.movieDesTxt)).getText().toString();
		//Insert the movie after data validation.for now, lets skip data validation
		long insertedId = dao.createMovie(name, desc);
		Log.d("CRUD","New movie with id= "+insertedId+" has been created ");
		//Clearing the fields
		((TextView)findViewById(R.id.movieNameTxt)).setText("");
		((TextView)findViewById(R.id.movieDesTxt)).setText("");
	}
}
