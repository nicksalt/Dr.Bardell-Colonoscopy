package ca.mobileappsolutions.drbardellcolonoscopy;


import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Nick on 2016-06-10.
 */

public class TimeService extends Service {

    private SharedPreferences myPrefs;
    private Calendar appointment;
    private Calendar oneWeek;
    private Calendar fiveDays;
    private Calendar threeDays;
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;
    private long apTime;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        getAppointment();
        appointment.set(year,month, day, hour, minute);
        apTime = appointment.getTimeInMillis();
        sentNot();
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void sentNot(){
        /*  - Create Calendars for all dates
            - Create Services for all notifications
            - Set Alarms and intents
         */
    }
    public void getAppointment() {
        year = myPrefs.getInt("year", 2016);
        month = myPrefs.getInt("month", 0);
        day = myPrefs.getInt("day", 1);
        hour = myPrefs.getInt("hour", 12);
        minute = myPrefs.getInt("minute", 0);
        Log.d("TESTING", Integer.toString(year) + ' ' + Integer.toString(month) + ' ' + Integer.toString(day) + ' ' +
                Integer.toString(hour) + ' ' + Integer.toString(minute));

    }


}
