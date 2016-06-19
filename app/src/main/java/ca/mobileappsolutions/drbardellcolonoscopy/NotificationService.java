package ca.mobileappsolutions.drbardellcolonoscopy;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

/**
 * Created by Nick on 2016-06-10.
 */

public class NotificationService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        genNot();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onDestroy(){
        super.onDestroy();
    }
    public void genNot(){
        Log.d("TESTING", "RUNNING NOT");
        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(getResources().getString(R.string.app_name))
                        .setContentText("TESTING");
        NotificationManager mNotifyMgr =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(0, mBuilder.build());
        onDestroy();
    }
}


