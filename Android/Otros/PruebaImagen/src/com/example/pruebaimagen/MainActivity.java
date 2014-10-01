package com.example.pruebaimagen;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		int display_mode = getResources().getConfiguration().orientation;

		if (display_mode == 1) {
		    setContentView(R.layout.activity_main);
		} else {
		    setContentView(R.layout.activity_main_land);
		}    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
