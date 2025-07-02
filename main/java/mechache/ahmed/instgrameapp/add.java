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
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class add extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;

    String dat;

    Button insertBtn;

    private Spinner spinnerTextSize;
    private Spinner spinnerTextSize2;
    String etat;
    int i2 =3;


    int mm =3;//the same date in calander

    int wj=0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final extra p1 = (extra) getIntent().getSerializableExtra("object");
        this.i2 = p1.type;
        this.etat=p1.eta1;
        System.out.println(i2+"/*/*/*/*/*/*/*/*/*/**/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/*/");


        //calander
        calendarView = findViewById(R.id.clander);
        calendar = Calendar.getInstance();
        // setDate(1,1,2023);

        //if (dt == 1) {
          //  getDate();
          //  dt =3 ;
        //}
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                int m = month+1;
                Toast.makeText(add.this, day+"/"+m+"/"+year, Toast.LENGTH_SHORT).show();

                String d = String.valueOf(day+"/"+m+"/"+year);
                setDate(day,month,year);
                mm=2;
                dat = d;
                mm=3;



            }
        });





        spinnerTextSize = findViewById(R.id.spinnerTexet);
        spinnerTextSize2 = findViewById(R.id.spinnerTexet2);





        String[] textSizes= getResources().getStringArray(R.array.font_size);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize.setAdapter(adapter);





        String[] textSizes2= getResources().getStringArray(R.array.font_size);
        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, textSizes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTextSize2.setAdapter(adapter2);

        insertBtn = findViewById(R.id.Insert);


        insertBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                extra p1 = new extra();
                extra s1 = new extra();
                String text = spinnerTextSize.getSelectedItem().toString();
                String text2 = spinnerTextSize2.getSelectedItem().toString();

                if (mm==3){
                    getDate();
                }

                  if (i2 == 0){
                      p1.setDate(dat);
                      p1.setSource(text);

                      System.out.println(text+"*********************************************************");
                      p1.setDes(text2);
                      System.out.println( text2+"*********************************************************");

                      Intent i = new Intent(add.this,MainActivity2.class);
                      i.putExtra("object",(Serializable) p1);
                      i2=3;
                      startActivity(i);
                     // finish();
                  }
                  if (i2==1){
                    //  p1.setDatesersh(dat);
                    //  p1.setSourcesersh(text);
                    //  p1.setDessersh(text2);
                      //

                      //
                      Toast.makeText(add.this, "date = "+dat+" source = "+text+ " destination = "+text2, Toast.LENGTH_SHORT).show();
                      Intent intent = new Intent(add.this,ListS.class);
                      //
                      intent.putExtra("object",(Serializable) p1);
                      s1.setTn(40);
                      i2=3;
                      wj=1;
                      startActivity(intent);
                     // finish();
                  }



            }
        });


    }
    //calander
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