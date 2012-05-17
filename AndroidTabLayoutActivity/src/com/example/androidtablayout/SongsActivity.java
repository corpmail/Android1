package com.example.androidtablayout;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

;

public class SongsActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.songs_layout);
		// AndroidTabLayoutActivity.arrayPeople	
		
        Button b1 = (Button) findViewById(R.id.song_button1);
        final TextView tw1 = (TextView)findViewById(R.id.songs_textView1);
        
        
        b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tw1.setText(AndroidTabLayoutActivity.arrayPeople);
				
			}

        });
		

	}
	
	
	
	
    @Override
    protected void onResume() {
        super.onResume();
        // The activity has become visible (it is now "resumed").
        //AndroidTabLayoutActivity.arrayPeople = "hier Resume";
        TextView tw2 = (TextView)findViewById(R.id.songs_textView1);
        if (AndroidTabLayoutActivity.arrayPeople.length()<=0){
        	tw2.setText("--RESUME--");
        }
        else {
        	
        	tw2.setText(AndroidTabLayoutActivity.arrayPeople);
        }
    }
	
	
	
	
	
	
	
	
}
