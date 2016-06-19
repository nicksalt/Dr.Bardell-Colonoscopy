package ca.mobileappsolutions.drbardellcolonoscopy;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.GregorianCalendar;


public class HomeScreen extends Activity {
    private SharedPreferences myPrefs;
    private View dateDialogView;
    private View timeDialogView;
    private AlertDialog alertDialog;
    private AlertDialog alertDialog2;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private int day;
    private int month;
    private int year;
    private int hour;
    private int minute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        myPrefs = getSharedPreferences("myPrefs", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        dateDialogView = View.inflate(this, R.layout.date_picker, null);
        alertDialog = new AlertDialog.Builder(this).create();
        timeDialogView = View.inflate(this, R.layout.time_picker, null);
        alertDialog2 = new AlertDialog.Builder(this).create();


    }

    public void editDate(View v) {
        datePicker = (DatePicker) dateDialogView.findViewById(R.id.date_picker);

        Calendar calendar = new GregorianCalendar(datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth());

        //long time = calendar.getTimeInMillis();
        alertDialog.dismiss();
        alertDialog.setView(dateDialogView);
        alertDialog.show();
    }

    public void editTime(View v) {
        day = datePicker.getDayOfMonth();
        month = datePicker.getMonth();
        year = datePicker.getYear();
        alertDialog.dismiss();

        timePicker = (TimePicker) timeDialogView.findViewById(R.id.time_picker);

        final Calendar c = Calendar.getInstance();
        timePicker.setCurrentHour(c.get(Calendar.HOUR_OF_DAY));
        timePicker.setCurrentMinute(c.get(Calendar.MINUTE));
        //long time = calendar.getTimeInMillis();
        alertDialog2.dismiss();
        alertDialog2.setView(timeDialogView);
        alertDialog2.show();
        timeDialogView.findViewById(R.id.time_set).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                hour = timePicker.getCurrentHour();
                minute = timePicker.getCurrentMinute();
                alertDialog2.dismiss();
                Intent startIntent = new Intent(HomeScreen.this, TimeService.class);
                saveDate(year, month, day, hour, minute);
                HomeScreen.this.startService(startIntent);
            }
        });

    }

    public void saveDate (int year, int month,  int day, int hour, int minute){
        SharedPreferences.Editor e = myPrefs.edit();
        e.putInt("year", year);
        e.putInt("month", month);
        e.putInt("day", day);
        e.putInt("hour", hour);
        e.putInt("minute", minute);
        e.apply();
    }

}




