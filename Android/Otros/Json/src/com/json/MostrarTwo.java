package com.json;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.globalConfiguration.GlobalConfiguration;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarTwo extends Activity {

	String a;
	Persona p;
	JSONArray jarr;
	String img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_two);
		Persona p=null;
		TextView tev = (TextView) findViewById(R.id.textView10);
		ImageView iv = (ImageView) findViewById(R.id.imageView2);
		/*
		if(GlobalConfiguration.getSingletonObject().getSelectedPersona()!=null){
        	this.p = GlobalConfiguration.getSingletonObject().getSelectedPersona();
        	iv.setImageBitmap(p.getImagen());
		}
		*/
		Archivos ar = new Archivos(null);
		
		try {
			String[] archivos = fileList();
			InputStreamReader archivo = new InputStreamReader(
			        openFileInput("notas.txt"));
			String a = ar.leer(archivos, archivo);
			String [] f = a.split("ciudad");
			Toast.makeText(getApplicationContext(), String.valueOf(f.length), Toast.LENGTH_LONG).show();
			for(int i=0; i<f.length; i++){
				Toast.makeText(getApplicationContext(), f[i], Toast.LENGTH_LONG).show();
			}
			tev.setText(a);
			jarr= new JSONArray(a);
			JSONObject json_data = null;
			for (int i = 0; i < jarr.length(); i++) {
				json_data = jarr.getJSONObject(i);
				p = new Persona("n"+json_data.getString("nombre"), json_data.getString("ciudad"), json_data.getString("imagen"));
				try {Thread.sleep(200); }
                catch (InterruptedException e) {}
			}
			
			Toast.makeText(getApplicationContext(), p.getNombre()+p.getApellido(), Toast.LENGTH_LONG).show();
			iv.setImageBitmap(p.getImagen());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostrar_two, menu);
		return true;
	}

}
