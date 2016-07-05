package ca.mobileappsolutions.drbardellcolonoscopy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.sql.Time;

/**
 * Created by Nick on 2016-06-26.
 */

public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, TimeService.class);
            SharedPreferences myPrefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
            if (myPrefs.getBoolean("running", false)) {
                context.startService(pushIntent);
            }
        }
    }
}