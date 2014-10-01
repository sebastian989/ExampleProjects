package com.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;

public class Archivos {
	
	String txt;

	public Archivos(String txt){
		this.txt=txt;
	}
	
	public void grabar(OutputStreamWriter archivo) {
        try {
            archivo.write(this.txt);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
    }
	
	public String leer(String[] archivos, InputStreamReader archivo){
		
		String todo ="";
        if (existe(archivos, "notas.txt"))
            try {
                
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                return todo;
            } catch (IOException e) {
            }
        return todo;
	}
	
	private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }
}
