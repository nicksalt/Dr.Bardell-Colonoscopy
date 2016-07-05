package ca.mobileappsolutions.drbardellcolonoscopy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;


public class NotificationReciever5 extends BroadcastReceiver {
    String notText;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TESTING", "RUNNING NOT");
        genNot(context);

    }
    public void genNot(Context context){
        Log.d("TESTING", "RUNNING NOT");

        notText = "In one hour, drink one packet of PICO-SALAX, followed by three glasses " +
                "of water.";


        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getResources().getString(R.string.app_name))
                        .setContentText(notText)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notText))
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setPriority(Notification.PRIORITY_MAX)
                        .setAutoCancel(true);
        Intent resultIntent = new Intent(context, HomeScreen.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 12,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(5, mBuilder.build());
        SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", MODE_PRIVATE);
        Editor e = myPrefs.edit();
        e.putBoolean("running", false);
        e.apply();
        context.stopService(new Intent(context, TimeService.class));
    }
}

