package com.consumirwebservicesjson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class TraerDatos extends Activity {

	private String url = "http://www.pruebaandroid.tk/";
	private String result;
	private JSONArray jArray;

	public JSONArray conect(){
		InputStream is = null;
		List valores = new ArrayList();
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			
			//Por POST:
			HttpPost request = new HttpPost(this.url);
			UrlEncodedFormEntity entiti=null;
			valores.add(new BasicNameValuePair("a", "dos"));
			entiti = new UrlEncodedFormEntity(valores);
		    if (entiti!=null) {
		    ((HttpPost)request).setEntity(entiti);
		    }
		    
		    //Por GET:
		    /*
		     HttpGet request = new HttpGet(this.url+"?a=uno");
		    */
		    
            HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			sb.append(reader.readLine() + "\n");

			String line = "0";
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			result = sb.toString();
			jArray = new JSONArray(result);
		} 
		catch (ClientProtocolException e) {
			Log.d("HTTPCLIENT", e.getLocalizedMessage());
		} 
		catch (IOException e) {
			Log.d("HTTPCLIENT", e.getLocalizedMessage());
		}
		catch (Exception e) {
			Log.e("**ERROR", "Error conexion " + e.toString());
		}
		return jArray;
	}
}