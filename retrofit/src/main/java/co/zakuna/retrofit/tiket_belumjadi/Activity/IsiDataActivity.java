package co.zakuna.retrofit.tiket_belumjadi.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import co.zakuna.retrofit.R;
import co.zakuna.retrofit.tiket_belumjadi.Objek.Stasiun;

/**
 * Created by Lenovo on 23/07/2016.
 */
public class IsiDataActivity extends AppCompatActivity {
    private AutoCompleteTextView asal,tujuan;
    private Button search;
    private EditText tanggal;
    private StasiunActivity stasiunActivity;
    public static String[] daftarStasiun;
    private String date,tiketDate,origin, dept;
    private Date formatTanggal;
    private MyCLick myCLick;
    private List<Stasiun> stasiuns;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        tanggal = (EditText)findViewById(R.id.tanggal);
        asal = (AutoCompleteTextView) findViewById(R.id.auto_complete_Asal);
        tujuan = (AutoCompleteTextView) findViewById(R.id.auto_complete_tujuan);
        search = (Button) findViewById(R.id.search_button);
        //button handler
        myCLick = new MyCLick();
        search.setOnClickListener(myCLick);

        //set calendar to current day
        Calendar cal = Calendar.getInstance();
        tiketDate = new SimpleDateFormat("dd-MM-yyy").format(cal.getTime());
        date = new SimpleDateFormat("dd-MMM-yyyy").format(cal.getTime());
        tanggal.setText(date);

        tanggal.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    //popup
                    popupWindow();
                }
                return true;
            }
        });

        //asal dan tujuan auto complete
        Intent i = getIntent();
        daftarStasiun = i.getStringArrayExtra("simpleArray");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, daftarStasiun);
        asal.setThreshold(1);
        asal.setAdapter(adapter);
        asal.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                origin = (String) parent.getItemAtPosition(position);
                hidenKeyboard(view);
            }
        });

        tujuan.setThreshold(1);
        tujuan.setAdapter(adapter);
        tujuan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dept = (String) parent.getItemAtPosition(position);
                hidenKeyboard(view);
            }
        });
    }

    public class MyCLick implements View.OnClickListener{

        @Override
        public void onClick(View v) {

            if (origin != null && dept != null && tiketDate != null){

            String idAsal = StasiunActivity.getIdStasiun(origin);
            String idTujuan = StasiunActivity.getIdStasiun(dept);
            String kotaAsal = StasiunActivity.getNamaStasiun(origin);
            String kotaTujuan = StasiunActivity.getNamaStasiun(dept);

                Intent i = new Intent(IsiDataActivity.this, FullActivity.class);
                i.putExtra("tanggal", tiketDate);
                i.putExtra("idAsal", idAsal);
                i.putExtra("idTujuan", idTujuan);
                i.putExtra("kotaAsal", kotaAsal);
                i.putExtra("kotaTujuan", kotaTujuan);
                startActivity(i);
                finish();
            }else{
                Toast.makeText(IsiDataActivity.this,"Mohon isi data dengan benar",Toast.LENGTH_SHORT).show();
            }

        }
    }

    public String popupWindow(){
        final Dialog d = new Dialog(IsiDataActivity.this);

        d.setContentView(R.layout.activity_calendarview);

        final CalendarView calendar = (CalendarView) d.findViewById(R.id.calendar);
        final Button button = (Button)d.findViewById(R.id.ok_button);
        d.setTitle("Pilih tanggal");

        Long currentDate = System.currentTimeMillis();
        Calendar cal = Calendar.getInstance();

        // sets whether to show the week number.
        calendar.setShowWeekNumber(false);

        // sets the first day of week according to Calendar.
        // here we set Monday as the first day of the Calendar
        calendar.setFirstDayOfWeek(2);

        calendar.setMinDate(currentDate);
        cal.add(Calendar.DAY_OF_MONTH, 91);
        calendar.setMaxDate(cal.getTimeInMillis());

        //sets the listener to be notified upon selected date change.
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            //show the selected date as a toast
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int day) {
                SimpleDateFormat fmt = new SimpleDateFormat("dd-MMM-yyyy");
                formatTanggal = new GregorianCalendar(year,month,day).getTime();
                tiketDate = new SimpleDateFormat("dd-MM-yyy").format(formatTanggal);
                date = fmt.format(formatTanggal);
            }
        });
        d.show();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tanggal.setText(date);
                d.dismiss();
            }
        });
        return date;
    }
    public void hidenKeyboard(View view){
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
