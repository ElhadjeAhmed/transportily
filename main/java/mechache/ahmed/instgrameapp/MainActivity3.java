package mechache.ahmed.instgrameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class MainActivity3 extends AppCompatActivity{
    public CardView home,add,sersh,suprime,profile,logaot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        home =(CardView) findViewById(R.id.home);
        add = (CardView) findViewById(R.id.add);
        sersh = (CardView) findViewById(R.id.search);
        suprime = (CardView) findViewById(R.id.delete);
        profile = (CardView) findViewById(R.id.present);
        logaot = (CardView) findViewById(R.id.logout);




        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extra p1 = new extra();
                p1.setTn(2);

                Intent intent = new Intent(MainActivity3.this,RetriveDqtqlnRecyclerViez.class);
                intent.putExtra("object",(Serializable) p1);
                startActivity(intent);
             //   finish();

            }
        });

      add.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent = new Intent(MainActivity3.this, mechache.ahmed.instgrameapp.add.class);
              extra p1 = new extra();
              intent.putExtra("object",(Serializable) p1);
              p1.setEta1("add");
              p1.setType(0);
              System.out.println(p1.eta1+"*********************************************************");
              startActivity(intent);
           //   finish();
          }
      });
        sersh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,my.class);
              //  extra p1 = new extra();
              //  intent.putExtra("object",(Serializable) p1);
              //  p1.setEta1("sersh");
              //  p1.setType(1);
               // System.out.println(p1.eta1+"*********************************************************");
                startActivity(intent);
             //   finish();



            }
        });

        logaot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,LoginActivity.class);
                startActivity(intent);
                  finish();

            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,ListS.class);
                startActivity(intent);


            }
        });

        suprime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity3.this,RetriveDqtqlnRecyclerViez.class);
                extra p1 = new extra();
                p1.setTn(3);//etata my
                p1.setSp(1); //eta suprime

                intent.putExtra("object",(Serializable) p1);
                startActivity(intent);


            }
        });


    }



}