package com.consumirwebservicesjson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.Type;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MostrarR extends Activity {

	private ProgressDialog pd;
	List<Persona> lst = new ArrayList<Persona>();
	public JSONArray jArray;
	String result;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_r);
		TraerDatos td = new TraerDatos();
		Connection c = new Connection(td);
		c.execute();
		while(c.getList().isEmpty()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Toast.makeText(getApplicationContext(), c.getList().get(0).getNombre(), Toast.LENGTH_LONG).show();
	}

	private class Connection extends AsyncTask<String, Void, Object> {

		TraerDatos tda;
		List<Persona> lsta = new ArrayList<Persona>();
		
		public Connection (TraerDatos t){
			tda=t;
		}
		
		@Override
		protected void onPreExecute() {
			//pd.setMessage("Descargando Recetas...");
			//pd.setTitle("Un Momento");
			//pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			//pd.show();
		}

		@Override
		protected Object doInBackground(String... args) {
			jArray = tda.conect();
			JSONObject json_data = null;
			try {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					lsta.add(new Persona(json_data.getString("nombre"),
							json_data.getString("apellido")));
					
				}
				
			} catch (Exception e) {
				Log.e("**ERROR", "Error conexion " + e.toString());
				Toast.makeText(getBaseContext(), e.toString(),
						Toast.LENGTH_LONG).show();
			}
			return lsta;
		}

		@Override
		protected void onPostExecute(Object result) {
			// pd.dismiss();
		}
		
		public List<Persona> getList(){
			return this.lsta;
		}
	}
}