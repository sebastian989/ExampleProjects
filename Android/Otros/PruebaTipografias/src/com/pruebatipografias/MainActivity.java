package com.pruebatipografias;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class MainActivity extends Activity {

	public GridView gridview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		String t = "Nombre: Festival" +
				"\nApellido: Teatro" +
				"\nCiudad: Manizales";
		
		TextView texto = (TextView) findViewById(R.id.fuente);
		Typeface font = Typeface.createFromAsset(getAssets(), "DINPro-Regular.otf");
		texto.setTypeface(font);
		texto.setText(t);
		
		String tes = "Festival Internacional de teatro de Manizales";
		TextView t2 = (TextView) findViewById(R.id.textView1);
		Typeface font2 = Typeface.createFromAsset(getAssets(), "olivier_demo.ttf");
		t2.setTypeface(font2);
		t2.setText(tes);
		t2.setTextSize(30);
		
        gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new TableAdapter(this, null, 1));
        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            	for(int i = 0; i<gridview.getChildCount(); i++){
            		gridview.getChildAt(i).setBackgroundColor(Color.CYAN);
            	}
            	gridview.getChildAt(position).setBackgroundColor(Color.WHITE);
            	Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
