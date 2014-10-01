package com.json;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class Persona {

	String nombre;
	String apellido;
	String data;
	Bitmap imagen;
	
	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}

	public Persona(String nombre, String apellido, String data) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.data = data;
	    try {   
	      byte[] byteData = Base64.decode(data, Base64.DEFAULT);
	      this.imagen = BitmapFactory.decodeByteArray( byteData, 0, byteData.length);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public void setData(String data) {
	    this.data = data;
	    try {   
	      byte[] byteData = Base64.decode(data, Base64.DEFAULT);
	      this.imagen = BitmapFactory.decodeByteArray( byteData, 0, byteData.length);
	    }
	    catch(Exception e) {
	      e.printStackTrace();
	    }
	  }
	
	public String getData() {
	    return data;
	  }
}
