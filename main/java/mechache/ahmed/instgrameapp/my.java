package mechache.ahmed.instgrameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class my extends AppCompatActivity {
    CalendarView calendarView;
    Calendar calendar;

    String dat;
    int mm =3;

    Button SherchBtn;

    private Spinner spinnerTextSize;
    private Spinner spinnerTextSize2;

    TextView btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        //calander
        calendarView = findViewById(R.id.clandera);
        calendar = Calendar.getInstance();


     //   btn = findViewById(R.id.nextBtn);

     //   btn.setOnClickListener(new View.OnClickListener() {
          //  @Override
       //     public void onClick(View view) {

             //   Intent intent = new Intent(my.this,RetriveDqtqlnRecyclerViez.class);

             //   extra p1 = new extra();
              //  p1.setTn(60);
              //  intent.putExtra("object",(Serializable) p1);
              //  startActivity(intent);

        //    }
       // });
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                int m = month+1;
                Toast.makeText(my.this, day+"/"+m+"/"+year, Toast.LENGTH_SHORT).show();

                String d = String.valueOf(day+"/"+m+"/"+year);
                setDate(day,month,year);

                mm=2;
                dat = d;
                mm=3;




            }
        });
        spinnerTextSize = findViewById(R.id.spinnerTexeta);
        spinnerTextSize2 = findViewById(R.id.spinnerTexet2a);

        String[] textSizes= getResources().getStringArray(R.array.font_size);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);


        String[] textSizes2= getResources().getStringArray(R.array.font_size);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize2.setAdapter(adapter2);


        SherchBtn = findViewById(R.id.Inserta);


        SherchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

        String text = spinnerTextSize.getSelectedItem().toString();
        String text2 = spinnerTextSize2.getSelectedItem().toString();

                if (mm==3){
                    getDate();
                }

        System.out.println(dat+"/*/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
        System.out.println(text+"/*/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");
        System.out.println(text2+"/*/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");

                extra p1 = new extra();
                p1.setTn(1);
                p1.setDatesersh(dat);
                p1.setSourcesersh(text);
                p1.setDessersh(text2);


                Intent intent = new Intent(my.this,RetriveDqtqlnRecyclerViez.class);
                intent.putExtra("object",(Serializable) p1);
                startActivity(intent);



            }
        });

    }
    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
        this.dat=selected_date;

    }
    public void  setDate(int day,int month,int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH,month );
        calendar.set(Calendar.DAY_OF_MONTH,day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);

    }
}