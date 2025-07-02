package mechache.ahmed.instgrameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class manageotp extends AppCompatActivity {
    EditText t2;
    Button b2;

    String phonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manageotp);
        phonenumber = getIntent().getStringExtra("mobile").toString();
        t2=(EditText) findViewById(R.id.t2);
        b2 = (Button) findViewById(R.id.b2);


    }
}