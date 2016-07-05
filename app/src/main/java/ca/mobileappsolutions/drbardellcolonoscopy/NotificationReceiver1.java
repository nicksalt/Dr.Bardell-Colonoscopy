package ca.mobileappsolutions.drbardellcolonoscopy;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationReceiver1 extends BroadcastReceiver {
    String notText;
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TESTING", "RUNNING NOT");
        genNot(context);

    }
    public void genNot(Context context){
        Log.d("TESTING", "RUNNING NOT");

        notText = "Your colonoscopy is in less than five days. Remember to avoid " +
                        "nuts and seeds from now until the colonoscopy ends.";


        android.support.v4.app.NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle(context.getResources().getString(R.string.app_name))
                        .setContentText(notText)
                        .setStyle(new NotificationCompat.BigTextStyle().bigText(notText))
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true);
        Intent resultIntent = new Intent(context, HomeScreen.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 8,
                resultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(1, mBuilder.build());
    }
}

