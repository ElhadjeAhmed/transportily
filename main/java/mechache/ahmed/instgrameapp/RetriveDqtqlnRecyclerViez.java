package mechache.ahmed.instgrameapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class RetriveDqtqlnRecyclerViez extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    FirebaseStorage mStorage;

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;
    List<StudentModel> studentMdlList;

   TextView home, ajouter;

   int t,s;

   String datee,source,destination,idd;

    private FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrive_dqtqln_recycler_viez);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("trucks");//truks  student
        mStorage= FirebaseStorage .getInstance();
        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        studentMdlList = new ArrayList<StudentModel>();
        studentAdapter = new StudentAdapter(RetriveDqtqlnRecyclerViez.this,studentMdlList);
        recyclerView.setAdapter(studentAdapter);

        mAuth = FirebaseAuth.getInstance();
        idd = mAuth.getCurrentUser().getUid();

      //  ajouter = findViewById(R.id.ajouter);
      //  home = findViewById(R.id.hhome);

        final extra p1 = (extra) getIntent().getSerializableExtra("object");
        if (p1 != null) {
            t=p1.tn;
            datee = p1.datesersh;
            source = p1.sourcesersh;
            destination = p1.dessersh;
          s= p1.sp;

            System.out.println(p1.tn + "   11111111111111111111111111111111111111111111111111111  etat chercher ou etat ajouter");
            System.out.println(p1.ed + "   11111111111111111111111111111111111111111111111111111  etat chercher ou etat ajouter");
        }


    //    final extra z1 = (extra) getIntent().getSerializableExtra("ob");
     //   if (z1 != null) {
       //     int r = z1.tn;
       //     System.out.println(r+"   111111111111111111111111111111111111111111111111111111111111111111111");
      //  }else {
        //    System.out.println("   00000000000000000000000000000000000000000000000000000000000000000000000000");
      //  }





  //      ajouter.setOnClickListener(new View.OnClickListener() {
    //        @Override
      //      public void onClick(View view) {
        //        Intent intent = new Intent(RetriveDqtqlnRecyclerViez.this,instgrameapp.ahmed.mechache.add.class);
          //      extra p1 = new extra();
         //       intent.putExtra("object",(Serializable) p1);
           //     p1.setEta1("add");
          //      p1.setType(0);
         //       System.out.println(p1.eta1+"*********************************************************");
           //     startActivity(intent);
            //    finish();
          //  }
     //   });


     //   home.setOnClickListener(new View.OnClickListener() {
       //     @Override
       //     public void onClick(View view) {
         //       startActivity(new Intent(RetriveDqtqlnRecyclerViez.this,MainActivity3.class));
      //      }
     //   });



        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                StudentModel studentModel = snapshot.getValue(StudentModel.class);
                if (t == 2) {  //etat sherche
                    studentMdlList.add(studentModel);
                    studentAdapter.notifyDataSetChanged();

                }
                if (t == 1) {



                         String t1 = studentModel.getReservationDate();
                        String t2 = studentModel.getSource();
                        String t3 = studentModel.getDestination();




                       HashSet<String> sours = new HashSet<String>();
                       sours.add(t2);
                       System.out.println(sours);

                       HashSet<String> destinatio = new HashSet<String>();
                       destinatio.add(t3);
                       System.out.println(destinatio);

                       HashSet<String> date = new HashSet<String>();
                      date.add(t1);
                      System.out.println(date);


                    System.out.println(source+"/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");
                    System.out.println(destination+"/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");
                    System.out.println(datee+"/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");


                     if ((sours.contains(source))&&(destinatio.contains(destination))&&(date.contains(datee))){

                    //   System.out.println("/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");

                    //

                         studentMdlList.add(studentModel);
                         studentAdapter.notifyDataSetChanged();

                    //    if (t==6){
                    //      System.out.println("4444444/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");
                    //  }



                     }

                }

                if (t == 3){
                    String i = studentModel.getId();

                    HashSet<String> id = new HashSet<String>();
                    id.add(i);
                    System.out.println(id);

                    System.out.println(idd+"/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");
                    if (id.contains(idd)){

                        //   System.out.println("/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");

                        //

                        studentMdlList.add(studentModel);
                        studentAdapter.notifyDataSetChanged();




                        //    if (t==6){
                        //      System.out.println("4444444/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**/**");
                        //  }



                    }

                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}