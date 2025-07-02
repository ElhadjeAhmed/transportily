package mechache.ahmed.instgrameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class dlete extends AppCompatActivity {
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dlete);
        mAuth = FirebaseAuth.getInstance();
        System.out.println(mAuth.getCurrentUser().getUid()+"1111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111");
    }
}