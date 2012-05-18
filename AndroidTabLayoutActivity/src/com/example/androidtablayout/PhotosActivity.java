package com.example.androidtablayout;
 
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
 
public class PhotosActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photos_layout);
        
        Button button = (Button) findViewById(R.id.button1);
        final EditText et1 = (EditText) findViewById(R.id.editText1);
        
        
        button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AndroidTabLayoutActivity.arrayPeople = et1.getText().toString();
			}

       }); 

        
    }
  
}
