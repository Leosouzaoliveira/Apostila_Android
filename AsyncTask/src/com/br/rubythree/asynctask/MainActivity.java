package com.br.rubythree.asynctask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends Activity {
	private ProgressDialog dialog;
	private Button startButton;
	private ImageView imgView;
	private DownloadImageTask task;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		imgView = (ImageView) findViewById(R.id.image_view);
		startButton = (Button) findViewById(R.id.start_button);
		
		startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog = ProgressDialog.show(MainActivity.this,getString(R.string.download),getString(R.string.downloading));
				task = new DownloadImageTask();
				task.execute("http://www.ramitalia.net/bcb/MV%20AGUSTA/MV%20Agusta%20Brutale/BRUTALE%20910.jpg");
				
			}
		});
	}
	
	protected void onDestoy(){
		if (dialog != null && dialog.isShowing()){
			dialog.dismiss();
			dialog = null;
		}
		if (task != null){
			task.cancel(true);
		}
		super.onDestroy();
	}
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{

		@Override
		protected Bitmap doInBackground(String... params) {
			try{
			return downloadBitmap(params[0]);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPreExecute(){
			super.onPreExecute();
			dialog.show();
		}
		protected void onPostExecute(Bitmap result){
			super.onPostExecute(result);
			dialog.dismiss();
			if(result != null){
				imgView.setImageBitmap(result);
			}
		}
		private Bitmap downloadBitmap(String url) throws IOException {
			URL imgUrl = null;
			try{
				imgUrl = new URL(url);
			}catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			Bitmap bitmapImage = null;
			try{
				HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
				conn.setDoInput(true);
				conn.connect();
				InputStream is = conn.getInputStream();
				
				bitmapImage = BitmapFactory.decodeStream(is);
			}catch (Exception e) {
				e.printStackTrace();
			}
			return bitmapImage;
		}
	}
}
