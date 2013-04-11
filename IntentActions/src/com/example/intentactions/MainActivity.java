package com.example.intentactions;

import com.example.intentactions.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button viewSiteButton = (Button) findViewById(R.id.view_site_button);
		Button sendEmailButton = (Button) findViewById(R.id.send_email_button);
		Button makeCallButton = (Button) findViewById(R.id.make_call_button);
		
		viewSiteButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://google.com"));
				startActivity(intent);
			}
		});

		
	sendEmailButton.setOnClickListener(new OnClickListener () {
		
		@Override
		public void onClick(View v){
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.setType("plain/text");
			intent.putExtra(Intent.EXTRA_EMAIL, new String [] {"victor.r3cotia@gmail.com"});
			startActivity(Intent.createChooser(intent, "Enviar email"));
			
		}
	});
	makeCallButton.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:0000-02323"));
			startActivity(intent);
			
			}
		});
	}
}

