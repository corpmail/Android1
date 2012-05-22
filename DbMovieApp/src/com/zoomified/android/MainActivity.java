package com.zoomified.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener{
    
	private Button newBtn = null;
	private Button listBtn = null;
	/** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        newBtn = (Button)findViewById(R.id.newBtn);
        listBtn = (Button)findViewById(R.id.listBtn);
        newBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
    }
	/**
	 * Handles button click on this view a
	 */
	@Override
	public void onClick(View v) {
		int clickedElement = v.getId();
		if(clickedElement == R.id.listBtn){
			Intent intnt = new Intent(this,MovieListActivity.class);
			startActivity(intnt);
		}
		else if (clickedElement == R.id.newBtn){
			Intent intnt = new Intent(this,NewMovieActivity.class);
			intnt.putExtra("action", "add");
			startActivity(intnt);
		}
		
	}
	
}