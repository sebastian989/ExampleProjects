package com.pruebamaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Provider;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONObject;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.maps.GeoPoint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends android.support.v4.app.FragmentActivity 
	implements OnMapClickListener{
	
	private final LatLng UPV = new LatLng(39.4699075,-0.3762881);
	private LatLng yo;
	private GoogleMap mapa;
	Location location;
	ArrayList<LatLng> markerPoints;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//MAPA================================================
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	      mapa = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		  //mapa.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
	      mapa.setMapType(GoogleMap.MAP_TYPE_NORMAL);
	      //mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(UPV, 15));
	      mapa.setMyLocationEnabled(true);
	      mapa.getUiSettings().setZoomControlsEnabled(true);
	      mapa.getUiSettings().setCompassEnabled(true);
	      /*mapa.addMarker(new MarkerOptions()
	            .position(UPV)
	            .title("UPV")
	            .snippet("Universidad Polit�cnica de Valencia")
	            .icon(BitmapDescriptorFactory
	                   .fromResource(R.drawable.ic_launcher))
	            .anchor(0.5f, 0.5f));*/
	      mapa.setOnMapClickListener(this);
	      //====================================================
	     
	      //MIS CORDENADAS=======================================
	      LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
	      Criteria criteria = new Criteria();

          // Getting the name of the best provider
          String provider = locationManager.getBestProvider(criteria, true);

          // Getting Current Location
          location = locationManager.getLastKnownLocation(provider);
          
          if(location!=null){
          // Getting latitude of the current location
          double latitude = location.getLatitude();

          // Getting longitude of the current location
          double longitude = location.getLongitude();

          // Creating a LatLng object for the current location
          yo = new LatLng(location.getLatitude(), location.getLongitude());
          mapa.moveCamera(CameraUpdateFactory.newLatLngZoom(yo, 15));
          //mapa.addMarker(new MarkerOptions().position(yo).title("Start"));
          
          }
         //=========================================================
          
         //RUTA====================================================
       // Initializing
          markerPoints = new ArrayList<LatLng>();
   
          // Getting reference to SupportMapFragment of the activity_main
          SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
   
          // Getting reference to Button
          Button btnDraw = (Button)findViewById(R.id.btn_draw);
   
   
          // Setting onclick event listener for the map
          mapa.setOnMapClickListener(new OnMapClickListener() {
   
              @Override
              public void onMapClick(LatLng point) {
   
                  // Already 10 locations with 8 waypoints and 1 start location and 1 end location.
                  // Upto 8 waypoints are allowed in a query for non-business users
                  if(markerPoints.size()>=10){
                      return;
                  }
   
                  // Adding new item to the ArrayList
                  markerPoints.add(point);
   
                  // Creating MarkerOptions
                  MarkerOptions options = new MarkerOptions();
   
                  // Setting the position of the marker
                  options.position(point);
   
                  /**
                  * For the start location, the color of marker is GREEN and
                  * for the end location, the color of marker is RED and
                  * for the rest of markers, the color is AZURE
                  */
                  if(markerPoints.size()==1){
                      options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                  }else if(markerPoints.size()==2){
                      options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                  }else{
                      options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                  }
   
                  // Add new marker to the Google Map Android API V2
                  mapa.addMarker(options);
              }
          });
          
          // The map will be cleared on long click
           mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
   
              @Override
              public void onMapLongClick(LatLng point) {
                  // Removes all the points from Google Map
                  mapa.clear();
   
                  // Removes all the points in the ArrayList
                  markerPoints.clear();
              }
          });
           
        // Click event handler for Button btn_draw
           btnDraw.setOnClickListener(new OnClickListener() {
    
               @Override
               public void onClick(View v) {
                   // Checks, whether start and end locations are captured
                   if(markerPoints.size() >= 2){
                       LatLng origin = markerPoints.get(0);
                       LatLng dest = markerPoints.get(1);
    
                       // Getting URL to the Google Directions API
                       String url = getDirectionsUrl(origin, dest);
    
                       DownloadTask downloadTask = new DownloadTask();
    
                       // Start downloading json data from Google Directions API
                       downloadTask.execute(url);
                   }
               }
           });
         //========================================================
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onMapClick(LatLng point) {
		mapa.addMarker(new MarkerOptions().position(point).
		         icon(BitmapDescriptorFactory
		            .defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));
		
	}
	
	//======================================
	private String getDirectionsUrl(LatLng origin,LatLng dest){
		 
        // Origin of route
        String str_origin = "origin="+origin.latitude+","+origin.longitude;
 
        // Destination of route
        String str_dest = "destination="+dest.latitude+","+dest.longitude;
 
        // Sensor enabled
        String sensor = "sensor=false";
 
        // Waypoints
        String waypoints = "";
        for(int i=2;i<markerPoints.size();i++){
            LatLng point  = (LatLng) markerPoints.get(i);
            if(i==2)
                waypoints = "waypoints=";
            waypoints += point.latitude + "," + point.longitude + "|";
        }
     // Building the parameters to the web service
        String parameters = str_origin+"&"+str_dest+"&"+sensor+"&"+waypoints;
 
        // Output format
        String output = "json";
 
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/"+output+"?"+parameters;
 
        return url;
    }
	
	/** A method to download json data from url */
    private String downloadUrl(String strUrl) throws IOException{
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
 
            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();
 
            // Connecting to url
            urlConnection.connect();
 
            // Reading data from url
            iStream = urlConnection.getInputStream();
 
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
 
            StringBuffer sb  = new StringBuffer();
 
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
 
            data = sb.toString();
 
            br.close();
 
        }catch(Exception e){
            Log.d("Exception while downloading url", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }
 
    // Fetches data from url passed
    private class DownloadTask extends AsyncTask<String, Void, String>{
 
        // Downloading data in non-ui thread
        @Override
        protected String doInBackground(String... url) {
 
            // For storing data from web service
 
            String data = "";
 
            try{
                // Fetching the data from web service
                 data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }
 
        // Executes in UI thread, after the execution of
        // doInBackground()
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
 
            ParserTask parserTask = new ParserTask();
 
            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);
        }
    }
 
    /** A class to parse the Google Places in JSON format */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String,String>>> >{
 
        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {
 
        JSONObject jObject;
        List<List<HashMap<String, String>>> routes = null;
 
            try{
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
 
                // Starts parsing data
                routes = parser.parse(jObject);
            }catch(Exception e){
                e.printStackTrace();
            }
            return routes;
        }
 
        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
 
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
 
            // Traversing through all the routes
            for(int i=0;i<result.size();i++){
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();
 
                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);
 
                // Fetching all the points in i-th route
                for(int j=0;j<path.size();j++){
                    HashMap<String,String> point = path.get(j);
 
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);
 
                    points.add(position);
                }
 
                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(4);
                lineOptions.color(Color.BLUE);
            }
 
             // Drawing polyline in the Google Map for the i-th route
             mapa.addPolyline(lineOptions);
         }
    }
	//========================================
}
