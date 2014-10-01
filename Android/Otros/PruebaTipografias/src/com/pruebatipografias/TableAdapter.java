package com.pruebatipografias;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class TableAdapter extends BaseAdapter {
    
	private final Activity activity;
	private Context mContext;
    private final ArrayList <String> list;
    private int type;
    private View view;
    private String [] colors;
    private int count;
 
    // el constructor necesita el contexto de la actividad donde se utiliza el
    // adapter
    public TableAdapter(Activity activity, ArrayList list, int type) {
        this.activity = activity;
        this.list = new ArrayList<String>();
        String a="Lun";
        String b="Mar";
        String c="Mie";
        String d="Jue";
        String e="Vie";
        String f="Sab";
        String g="Dom";
        this.list.add(a);
        this.list.add(b);
        this.list.add(c);
        this.list.add(d);
        this.list.add(e);
        this.list.add(f);
        this.list.add(g);
        this.type=type;
        this.completeColors();
    }
 
    public int getCount() {// devuelve el número de elementos que se introducen
            // en el adapter
        return this.list.size();
    }
 
    public Object getItem(int position) {
        // este método debería devolver el objeto que esta en esa posición del
        // adapter. No es necesario en este caso más que devolver un objeto null.
        return null;
    }
 
    public long getItemId(int position) {
        // este método debería devolver el id de fila del item que esta en esa
        // posición del adapter. No es necesario en este caso más que devolver 0.
        return 0;
    }
 
    // crear un nuevo ImageView para cada item referenciado por el Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
    	LayoutInflater inflater = activity.getLayoutInflater();
    	this.view = inflater.inflate(R.layout.elem, null,true);
    	TextView textView1=(TextView)view.findViewById(R.id.textView1);
    	textView1.setTextColor(Color.parseColor("#000000"));
        textView1.setText(this.list.get(position));
        if(position==1){
        	this.view.setBackgroundColor(Color.WHITE);
        }
        else{
        	this.view.setBackgroundColor(Color.CYAN);
        }
        return view;
    }
    
    public void completeColors(){
    	this.colors = new String [10];
    	this.colors[0]="#008A00";
    	this.colors[1]="#E3C800";
    	this.colors[2]="#AA00FF";
    	this.colors[3]="#FA6800";
    	this.colors[4]="#0050EF";
    	this.colors[5]="#A4C400";
    	this.colors[6]="#FF0000";
    	this.colors[7]="#6A00FF";
    	this.colors[8]="#60A917";
    	this.colors[9]="#D80073";
    }
}
