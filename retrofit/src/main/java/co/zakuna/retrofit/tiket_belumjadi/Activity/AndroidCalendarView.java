package co.zakuna.retrofit.tiket_belumjadi.Activity;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.Toast;
import android.app.Activity;

import java.util.Calendar;

import co.zakuna.retrofit.R;


/**
 * Created by Lenovo on 20/07/2016.
 */

public class AndroidCalendarView extends Activity {
    CalendarView calendar;
    String date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sets the main layout of the activity
        setContentView(R.layout.activity_calendarview);

        //initializes the calendarview
        initializeCalendar();
    }

    public void initializeCalendar() {
        Long currentDate = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();
        calendar = (CalendarView) findViewById(R.id.calendar);

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        //The background color for the selected week.
        calendar.setSelectedWeekBackgroundColor(getResources().getColor(R.color.green));

        //sets the color for the dates of an unfocused month.
        calendar.setUnfocusedMonthDateColor(getResources().getColor(R.color.transparent));

        //sets the color for the separator line between weeks.
        calendar.setWeekSeparatorLineColor(getResources().getColor(R.color.transparent));

        //sets the color for the vertical bar shown at the beginning and at the end of the selected date.
        calendar.setSelectedDateVerticalBar(R.color.darkgreen);
        calendar.setMinDate(currentDate);
        cal.add(Calendar.DAY_OF_MONTH,91);
        calendar.setMaxDate(cal.getTimeInMillis());

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                Toast.makeText(getApplicationContext(), day + "/" + month + "/" + year, Toast.LENGTH_LONG).show();
                date = ""+year+month+day;
            }
        });
    }

    public String getDate() {
        return date;
    }
}
