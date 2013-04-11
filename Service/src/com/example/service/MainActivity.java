package com.example.service;
 
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
	private Handler handler = new Handler() {
		public void handleMessage(Message message){
			Object path = message.obj;
			if(message.arg1 == RESULT_OK && path != null){
				Toast.makeText(MainActivity.this, getString(R.string.download_success,path.toString()),
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(MainActivity.this, getString(R.string.download_error), 
						Toast.LENGTH_LONG).show();
			}
		
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Button startButton = (Button) findViewById(R.id.start_button);
		startButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, DowloadService.class);
				Messenger messenger = new Messenger(handler);
				intent.putExtra("menssenger", messenger);
				intent.setData(Uri.parse("curso.html"));
				intent.putExtra("urlPath", "http://k19.com.br/cursos");
			}
		});
	}
}
