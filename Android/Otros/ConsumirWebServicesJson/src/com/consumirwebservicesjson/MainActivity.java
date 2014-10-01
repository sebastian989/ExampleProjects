package com.consumirwebservicesjson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private ProgressDialog pd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void pasar(View view) {
		Intent intent = new Intent(getApplicationContext(), MostrarR.class);
		startActivity(intent);
	}
}
