package mechache.ahmed.instgrameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.concurrent.TimeUnit;

public class otp extends AppCompatActivity {




    TextView btnverify,btnsend;

    EditText code;
    FirebaseAuth mAuth;
    String verificationID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);



        code = findViewById(R.id.code);
        btnsend = findViewById(R.id.btnsend);
        btnverify = findViewById(R.id.btnvrfy);
        mAuth = FirebaseAuth.getInstance();



        btnsend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final extra p2 = (extra) getIntent().getSerializableExtra("object");
               String tel = p2.mobil;
               if (TextUtils.isEmpty(tel)) {
                   Toast.makeText(otp.this, "Enter valid phone no.", Toast.LENGTH_SHORT).show();
               }
               else {


                   sendverificationcode(tel);
               }

           }
       });

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (TextUtils.isEmpty(code.getText().toString())) {
                    Toast.makeText(otp.this, "Wrong OTP Entered", Toast.LENGTH_SHORT).show();
                }else {
                    verifyvode(code.getText().toString());
                }


            }
        });

    }



    private void sendverificationcode(String phoneNumber) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+213"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
   private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential)
        {
            final  String code = credential.getSmsCode();
            if (code!= null)
            {
                verifyvode(code);
            }




        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(otp.this, "Verification Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCodeSent(@NonNull String s, PhoneAuthProvider.@NonNull ForceResendingToken token)
        {
          super.onCodeSent(s,token);
          verificationID = s;


        }
    };
    private void verifyvode(String Code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID,Code);
        signinbyCredentials(credential);


    }

    private void signinbyCredentials(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@androidx.annotation.NonNull Task<AuthResult> task)
                    {
                    if (task.isSuccessful())
                    {
                        Toast.makeText(otp.this, "login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(otp.this,MainActivity3.class));
                    }


                    }
                });
    }
}