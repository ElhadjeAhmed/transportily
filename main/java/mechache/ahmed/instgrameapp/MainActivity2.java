package mechache.ahmed.instgrameapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class MainActivity2 extends AppCompatActivity {

    //calnder
   // CalendarView calendarView;
   // Calendar calendar;
// id
    private FirebaseAuth mAuth;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    FirebaseStorage mStorage;
    ImageButton imageButton;

    //4
    EditText editfirst,editph ,editinfo;


    Button btninsert , suivant,otp;
    String dat;


    private static final int Gallery_code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAuth = FirebaseAuth.getInstance();


        //calander
        //   calendarView = findViewById(R.id.cllander);
        //  calendar = Calendar.getInstance();
        //setDate(1,1,2023);
        getDate();

        // calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
        //   @Override
        // public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
        //   Toast.makeText(MainActivity2.this, day+"/"+month+"/"+year, Toast.LENGTH_SHORT).show();


        // }
        // });




        imageButton = findViewById(R.id.image1);

        //3
        editfirst = findViewById(R.id.edit1);
        editph = findViewById(R.id.edit2);



        btninsert = findViewById(R.id.insert);
        // suivant = findViewById(R.id.suivant);
        // otp = findViewById(R.id.OTP);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("trucks");//student
        mStorage= FirebaseStorage .getInstance();
        progressDialog=new ProgressDialog(this);
        //    suivant.setOnClickListener(new View.OnClickListener() {
        //      @Override
        //    public void onClick(View view) {
        // startActivity(new Intent(MainActivity2.this,MainActivity3.class));
        //  }
        //  });
        // otp.setOnClickListener(new View.OnClickListener() {
        //   @Override
        //  public void onClick(View view) {
        //    startActivity(new Intent(MainActivity2.this,otp.class));
        // }
        // });


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
            }
        });
    }
    //calander
    public void getDate(){
        // long date = calendarView.getDate();
        // SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        // calendar.setTimeInMillis(date);
        //  String selected_date = simpleDateFormat.format(calendar.getTime());
        //  Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
        //  this.dat=selected_date;

        final extra p1 = (extra) getIntent().getSerializableExtra("object");
        String dat = p1.date;
        this.dat=dat;

    }
    // public void  setDate(int day,int month,int year){
    //   calendar.set(Calendar.YEAR, year);
    // calendar.set(Calendar.MONTH,month -1);
    // calendar.set(Calendar.DAY_OF_MONTH,day);
    // long milli = calendar.getTimeInMillis();
    // calendarView.setDate(milli);

    //}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Gallery_code && resultCode==RESULT_OK)
        {
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }


        btninsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //2
                String fn=editfirst.getText().toString().trim();
                String ph=editph.getText().toString().trim();

                final extra p1 = (extra) getIntent().getSerializableExtra("object");
                String sorce = p1.source;
                String des = p1.des;


                if (!(sorce.isEmpty() && des.isEmpty() && imageUrl!=null)){

                    progressDialog.setTitle("Uplaoading..");
                    progressDialog.show();
                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t =task.getResult().toString();

                                    //1
                                    DatabaseReference newPost = mRef.push();

                                    //id poste

                                    newPost.child("camion").setValue(fn);//name   FirstName
                                    newPost.child("destination").setValue(des);//destination   LastName
                                    newPost.child("source").setValue(sorce);//source   Name
                                    newPost.child("telephone").setValue(ph);//phone   Date
                                    newPost.child("reservationDate").setValue(dat);//date    phoneAnouce
                                    newPost.child("id").setValue(mAuth.getCurrentUser().getUid());// id
                                    newPost.child("idp").setValue(newPost.getKey());//date    phoneAnouce
                                    newPost.child("image").setValue(task.getResult().toString());//image


                                    progressDialog.dismiss();

                                    Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                                    startActivity(intent);




                                }
                            });
                        }
                    });

                }
            }
        });
    }

}