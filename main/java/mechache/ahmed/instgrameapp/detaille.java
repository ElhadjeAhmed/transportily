package mechache.ahmed.instgrameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class detaille extends AppCompatActivity {
    Button back;
    ImageView detal;

    TextView tx,tx2,tx3,ph,de,information,tx5,tx6;

    String idd;
    private static final int REQUESt_CALL = 1;
    private TextView mEditTextNumber;
    private FirebaseAuth mAuth;

    AlertDialog.Builder builder;

    String dl;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaille);


        mAuth = FirebaseAuth.getInstance();
       idd= mAuth.getCurrentUser().getUid();

        mEditTextNumber = findViewById(R.id.phone);//cbn
        final Button insertBtn  = findViewById(R.id.apple);
        final Button deletebtn  = findViewById(R.id.delete);

       builder = new AlertDialog.Builder(this);

        detal =findViewById(R.id.imgede);

        tx = findViewById(R.id.txt1);//destination
        tx2 = findViewById(R.id.text2);
        tx3 = findViewById(R.id.txt3);
        // ph = findViewById(R.id.phone);


        final extra p1 = (extra) getIntent().getSerializableExtra("object");

        String nom = p1.fn;//destinasion
        String url = p1.url;//image
        String pr = p1.ls; //nome
        String ss = p1.ss;//source
        String phone = p1.mobil;
        String dt = p1.date;//date
        System.out.println( nom+"*********************************************************");
        System.out.println( url+"*********************************************************");
        Picasso.get().load(url).into(detal);
        tx.setText(ss+" vers "+nom);
        tx2.setText(pr);
        tx3.setText(phone);
        mEditTextNumber.setText(dt);
        System.out.println(phone+"*********************************************************");



        System.out.println(p1.id+"-ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");
        System.out.println(p1.ed+"dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
        dl=p1.ed;
        System.out.println(idd+"+ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff");

        String t= p1.id;
        String admin = "rkvpo8Tx18ckzeQ8JHjJ98Gg2No1";


        if (idd.equals(t) || idd.equals(admin)){
            deletebtn.setVisibility(View.VISIBLE);
        }


        // System.out.println( nom+"*********************************************************");
        //tx.setText(nom);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                builder.setTitle("Are you Sure?")
                        .setMessage("Deleted data can't be Undo.")
                        .setCancelable(true)
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference().child("trucks").child(dl).removeValue();
                                Toast.makeText(detaille.this, "The data has been erased ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(detaille.this,MainActivity3.class);
                                startActivity(intent);



                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        }).show();


            }
        });



    }
    private void makePhoneCall(){
        String number = mEditTextNumber.getText().toString();
        if(number.trim().length() > 0){

            if(ContextCompat.checkSelfPermission(detaille.this,
                    android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(detaille.this,
                        new String[] {Manifest.permission.CALL_PHONE}, REQUESt_CALL);
            } else {
                String dial="tel:"  + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }else {
            Toast.makeText(detaille.this, "Entre Phone Numbre", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUESt_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(detaille.this, "Permission Denind ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}