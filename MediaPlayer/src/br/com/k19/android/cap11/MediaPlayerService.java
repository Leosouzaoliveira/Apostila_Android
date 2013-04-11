package br.com.k19.android.cap11;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.IBinder;
import android.widget.Toast;

public class MediaPlayerService extends Service implements OnPreparedListener, OnErrorListener {
	private MediaPlayer mMediaPlayer;
	
	public int onStartCommand(Intent intent, int flags, int startId) {
		Toast.makeText(this, "Iniciando o Serviço", Toast.LENGTH_SHORT).show();
		
		try{
		mMediaPlayer = new MediaPlayer();	
		Uri path = Uri.parse("andoid.resource://br.k19.android.cap11/" + R.raw.sample);
		
		mMediaPlayer.setDataSource(getApplicationContext(),path);
		mMediaPlayer.setOnPreparedListener(this);
		mMediaPlayer.prepareAsync();
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onPrepared(MediaPlayer mp) {
		// TODO Auto-generated method stub
		mMediaPlayer.start();
	}


	@Override
	public boolean onError(MediaPlayer mp, int what, int extra) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}

