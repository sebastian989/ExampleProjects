package com.pruebadialog;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.text.Html;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button b;
	TextView tv;
	Dialog dialog;
	String text = "<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>"+
			"<b>Dario Perez:</b>"+"<br/>"+"Es un gran evento, la aplicacion es supremamente buena" +
			" me encanto super mucho.<br/><br/>";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void pasar(View view){
		dialog = new Dialog(this); 
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog);
        dialog.setTitle("Comentarios");
        tv = (TextView) dialog.findViewById(R.id.textView1);
        b=(Button) dialog.findViewById(R.id.button1);
        tv.setText(Html.fromHtml(text));
        b.setOnClickListener(new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {
        	comentar();
        }
        });
        dialog.show();
	}
	
	public void comentar(){
		EditText et = (EditText) dialog.findViewById(R.id.editText1);
		String coment = "<b>Sebastian Gomez:</b>"+"<br/>"+et.getText().toString()+"<br/><br/>";
		text = text + coment;
		tv.setText(Html.fromHtml(text));
		et.setText("");
	}
}
