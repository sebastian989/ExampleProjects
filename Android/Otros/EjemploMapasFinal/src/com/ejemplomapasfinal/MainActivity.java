package com.ejemplomapasfinal;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends Activity {
	private WebView mapView;
	 LocationListener locationListener;
	 Location location;
	 String centerURL;
	 Double lati;
	 Double longi;
	 double latitudetwo = 5.071708;
     double longitudetwo = -75.522619;
     LocationListener milocListener;
     JSONObject todo = new JSONObject();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
        mapView = (WebView) findViewById(R.id.mapView);
        mapView.getSettings().setJavaScriptEnabled(true);
		String MAP_URL = "http://pruebaandroid.tk/Vista/mapa.html";
        mapView.loadUrl(MAP_URL);
        LocationManager milocManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		milocListener = new MiLocationListener();
		milocManager.requestLocationUpdates( LocationManager.NETWORK_PROVIDER, 1000000, 0, milocListener);
        }
	
	public void mapa(View view){
		centerURL = "javascript:cambiarMapa()";
		mapView.loadUrl(centerURL);
	}
	
	public void viaje(View view){
		centerURL = "javascript:modoDeViaje()";
		mapView.loadUrl(centerURL);
	}
	
	public class MiLocationListener implements LocationListener {
		
		public void onLocationChanged(Location loc) {
			lati = loc.getLatitude();
			longi = loc.getLongitude();
			mostrar();
		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Desactivado",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
			Toast.makeText(getApplicationContext(), "Gps Activo",
					Toast.LENGTH_SHORT).show();
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
	
	public void mostrar(){
		JSONArray json = new JSONArray();
		JSONObject yo = new JSONObject();
		JSONObject obra = new JSONObject();
		try {
			yo.put("descripcion", "Aqui estoy yo.");
			yo.put("latitud", lati);
			yo.put("longitut", longi);
			obra.put("descripcion", "7:30pm <strong>Esta es una obra de prueba</strong>");
			obra.put("latitud", latitudetwo);
			obra.put("longitut", longitudetwo);
			json.put(yo);
			json.put(obra);
			todo.put("valores", json);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String f = "'"+todo.toString()+"'";
		//Toast.makeText(getApplicationContext(), todo.toString(), Toast.LENGTH_LONG).show();
		centerURL = "javascript:ruta(" + f + ")";
		
		mapView.setWebViewClient(new WebViewClient(){ 
            @Override
            public void onPageFinished(WebView view, String url) 
            {
                mapView.loadUrl(centerURL);
            }
 
        });

		mapView.loadUrl(centerURL);
	}
}