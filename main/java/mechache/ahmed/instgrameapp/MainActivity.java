package mechache.ahmed.instgrameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();
                {
                    try {
                        sleep(5000);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                        Intent welcomIntent = new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(welcomIntent);
                    }
                }
            }
        };
        thread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
         startActivity(new Intent(MainActivity.this,MainActivity3.class));
         finish();

    }
}
}
//onstarte methode
//@Override
//protected void onStart() {
//  super.onStart();
//if (FirebaseAuth.getInstance().getCurrentUser() != null){
//  startActivity(new Intent(MainActivity.this,MainActivity2.class));
//finish();

//}
//}
//import com.google.firebase.auth.FirebaseAuth;