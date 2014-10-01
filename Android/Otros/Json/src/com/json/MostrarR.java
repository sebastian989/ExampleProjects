package com.json;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import com.globalConfiguration.GlobalConfiguration;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MostrarR extends Activity {

	private ProgressDialog pd;
	List<Persona> lst = new ArrayList<Persona>();
	public JSONArray jArray;
	String result;
	ProgressDialog dialog; 
	String ja;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostrar_r);
		dialog = new ProgressDialog(this);
        dialog.setMessage("Descargando...");
        dialog.setTitle("Progreso");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(false);
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
		this.lst=c.getList();
		Toast.makeText(getApplicationContext(), "Ya", Toast.LENGTH_LONG).show();
		ImageView iv = (ImageView) findViewById(R.id.imageView1);
		iv.setImageBitmap(c.getList().get(0).getImagen());
		Toast.makeText(getApplicationContext(), c.getList().get(0).getNombre()+" "+c.getList().get(0).getApellido(), Toast.LENGTH_LONG).show();
		Archivos ar = new Archivos(this.ja);
		String[] archivos = fileList();
		try {
			OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
			        "notas.txt", Activity.MODE_PRIVATE));
			ar.grabar(archivo);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Toast.makeText(getApplicationContext(), "Guardo", Toast.LENGTH_LONG).show();
	}
	
	public void pasar(View view){
		GlobalConfiguration.getSingletonObject().setSelectedPersona(this.lst.get(0));
		Intent intent = new Intent(getApplicationContext(), MostrarTwo.class);
		startActivity(intent);
	}

	private class Connection extends AsyncTask<String, Float, Integer> {

		TraerDatos tda;
		List<Persona> lsta = new ArrayList<Persona>();
		ProgressBar progressBar;
		 Button buttonStartProgress;
		
		public Connection (TraerDatos t){
			tda=t;
		}
		
		@Override
		protected void onPreExecute() {
			dialog.setProgress(0);
            dialog.setMax(100);
                dialog.show(); //Mostramos el diálogo antes de comenzar
			//pd.setMessage("Descargando Recetas...");
			//pd.setTitle("Un Momento");
			//pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			//pd.show();
		}

		@Override
		protected Integer doInBackground(String... args) {
			jArray = tda.conect();
			ja=jArray.toString();
			JSONObject json_data = null;
			try {
				for (int i = 0; i < jArray.length(); i++) {
					json_data = jArray.getJSONObject(i);
					lsta.add(new Persona(json_data.getString("nombre"),
							json_data.getString("ciudad"), json_data.getString("imagen")));
					try {Thread.sleep(200); }
                    catch (InterruptedException e) {}
					publishProgress(i/250f);
				}
				
			} catch (Exception e) {
				Log.e("**ERROR", "Error conexion " + e.toString());
				Toast.makeText(getBaseContext(), e.toString(),
						Toast.LENGTH_LONG).show();
			}
			return 250;
		}

		@Override
		protected void onPostExecute(Integer result) {
			dialog.dismiss();
		}
		
		protected void onProgressUpdate (Float... valores) {
            int p = Math.round(100*valores[0]);
            dialog.setProgress(p);
        }
		
		public List<Persona> getList(){
			return this.lsta;
		}
	}
}
