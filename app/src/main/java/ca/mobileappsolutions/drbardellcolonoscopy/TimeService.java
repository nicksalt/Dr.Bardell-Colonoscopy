package ca.mobileappsolutions.drbardellcolonoscopy;


import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.IBinder;

import java.util.Calendar;

public class TimeService extends Service {

    private SharedPreferences myPrefs;
    SharedPreferences.Editor e;
    Calendar appointment;
    long apTime;
    private int month;
    private int day;
    private int year;
    private int hour;
    private int minute;
    private Intent i1;
    private Intent i2;
    private Intent i3;
    private Intent i4;
    private Intent i5;
    private Intent i6;
    private PendingIntent pi1;
    private PendingIntent pi2;
    private PendingIntent pi3;
    private PendingIntent pi4;
    private PendingIntent pi5;
    private PendingIntent pi6;
    private Calendar not4;
    private Calendar not5;
    private Calendar not6;
    private AlarmManager am1;
    private AlarmManager am2;
    private AlarmManager am3;
    private AlarmManager am4;
    private AlarmManager am5;
    private AlarmManager am6;


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        e = myPrefs.edit();
        e.putBoolean("running", true);
        e.apply();
        appointment = Calendar.getInstance();
        getAppointment();
        appointment.set(year,month, day, hour, minute);
        apTime = appointment.getTimeInMillis();
        setIntents();
        setCalendars();
        am1 =( AlarmManager)getSystemService(Context.ALARM_SERVICE);
        am2 =( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am3 =( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am4 =( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am5=( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am6 =( AlarmManager)this.getSystemService(Context.ALARM_SERVICE);
        am1.set(AlarmManager.RTC_WAKEUP,apTime-(1000*60*60*24*7), pi1);
        am2.set(AlarmManager.RTC_WAKEUP, apTime-(1000*60*60*24*5), pi2);
        am3.set(AlarmManager.RTC_WAKEUP, apTime-1000*60*60*24*2, pi3);
        am4.set(AlarmManager.RTC_WAKEUP, not4.getTimeInMillis(), pi4);
        am5.set(AlarmManager.RTC_WAKEUP, not5.getTimeInMillis(), pi5);
        am6.set(AlarmManager.RTC_WAKEUP, not6.getTimeInMillis(), pi6);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void getAppointment() {
        year = myPrefs.getInt("year", 2016);
        month = myPrefs.getInt("month", 0);
        day = myPrefs.getInt("day", 1);
        hour = myPrefs.getInt("hour", 12);
        minute = myPrefs.getInt("minute", 0);
    }
    public void setIntents(){
        i1 = new Intent(TimeService.this, NotificationReceiver.class);
        i2 = new Intent(TimeService.this, NotificationReceiver1.class);
        i3 = new Intent(TimeService.this, NotificationReciever2.class);
        i4 = new Intent(TimeService.this, NotificationReciever3.class);
        i5 = new Intent(TimeService.this, NotificationReciever4.class);
        i6 = new Intent(TimeService.this, NotificationReciever5.class);
        pi1 = PendingIntent.getBroadcast(TimeService.this, 0, i1, 0);
        pi2 = PendingIntent.getBroadcast(TimeService.this, 1, i2, 0);
        pi3 = PendingIntent.getBroadcast(TimeService.this, 2, i3, 0);
        pi4 = PendingIntent.getBroadcast(TimeService.this, 3, i4, 0);
        pi5 = PendingIntent.getBroadcast(TimeService.this, 4, i5, 0);
        pi6 = PendingIntent.getBroadcast(TimeService.this, 5, i6, 0);
    }

    public void setCalendars(){
        not4= Calendar.getInstance();
        not5= Calendar.getInstance();
        not6= Calendar.getInstance();
        not4.set(year, month, day-1, 12, 0);
        not5.set(year, month, day-1, 17, 0);
        not6.set(year, month, day-1, 20, 0);
    }


}
