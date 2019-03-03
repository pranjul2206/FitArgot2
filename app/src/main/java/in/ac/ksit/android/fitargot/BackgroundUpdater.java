package in.ac.ksit.android.fitargot;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

import java.io.IOException;

import in.ac.ksit.android.fitargot.Network.ArgoApiClient;
import in.ac.ksit.android.fitargot.Network.ArgotAPI;

public class BackgroundUpdater extends Service {
    public BackgroundUpdater() {
    }

    public String  TAG=BackgroundUpdater.class.getSimpleName();

    public  void getSocialStatus(){

        SharedPreferences preferences=getApplicationContext().getSharedPreferences("APP_DATA",MODE_PRIVATE);
        if(preferences.getBoolean("LOGGED",false)){
            String uid=preferences.getString("UID",null);
            try {
                if(ArgoApiClient.getClient(Constants.ARGOT_BASE_PATH).create(ArgotAPI.class).getScs(uid).execute().body().getStatus()){

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            {
                Looper.prepare();
                Log.d(TAG,"Reading social status");
                getSocialStatus();
                handler=new Handler();
                handler.postDelayed(runnable,60000);
            }
        }
    };
    Handler handler;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Thread thread=new Thread(runnable);
        thread.start();

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
