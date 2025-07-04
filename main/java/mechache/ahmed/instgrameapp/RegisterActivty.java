package mechache.ahmed.instgrameapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.HashMap;

public class RegisterActivty extends AppCompatActivity {

    private EditText username,name,email,password;
    private boolean passwordShowing = false;
    private boolean conPasswordShowing =  false;


    private TextView register;
    private TextView loginUser;

    private DatabaseReference mRootRef;

    private FirebaseAuth mAuth;

    ProgressDialog pd;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_activty);

        final EditText password = findViewById(R.id.password);
        final EditText conPassword = findViewById(R.id.conpasswordET);
        final ImageView passwordIcon = findViewById(R.id.passwordIcon);
        final ImageView conPasswordIcon  = findViewById(R.id.conpasswordIcon);

        username = findViewById(R.id.username);
        name = findViewById(R.id.mobilET);
        email = findViewById(R.id.email);


        register = findViewById(R.id.signUpBtn);
        loginUser = findViewById(R.id.signInBtn);

        mRootRef = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        pd = new ProgressDialog(this);

        passwordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(passwordShowing){
                    passwordShowing =false;

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.visibility);
                }
                else {
                    passwordShowing = true;
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.visibility_off);
                }
                password.setSelection(password.length());
            }


        });

        conPasswordIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(conPasswordShowing){
                    conPasswordShowing=false;

                    conPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.visibility);
                }
                else {
                    conPasswordShowing = true;
                    conPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    passwordIcon.setImageResource(R.drawable.visibility_off);
                }
                conPassword.setSelection(conPassword.length());
            }
        });


        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivty.this , LoginActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtUsername = username.getText().toString();
                String txtName = name.getText().toString();  //phone
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();




                if (TextUtils.isEmpty(txtUsername) || TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtEmail) || TextUtils.isEmpty(txtPassword)){

                    pd.setMessage("Please Wail!");
                    pd.show();
                    Toast.makeText(RegisterActivty.this, "Empty credentials!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length()<6){
                    Toast.makeText(RegisterActivty.this, "Password too short!", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(txtUsername, txtName, txtEmail , txtPassword);

                }
            }
        });


    }

    private void registerUser(String username, String name, String email, String password) {

        mAuth.createUserWithEmailAndPassword(email , password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                HashMap<String , Object> map = new HashMap<>();
                map.put("mobil",name);
                map.put("email",email);
                map.put("username", username);
                map.put("id",mAuth.getCurrentUser().getUid());

                //zdh
                extra p2 = new extra();
                p2.setMobil(name);
                mRootRef.child("Users").child(mAuth.getCurrentUser().getUid()).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                   if (task.isSuccessful()){
                       pd.dismiss();
                       Toast.makeText(RegisterActivty.this, "Update the profile for " +
                               "better expereince", Toast.LENGTH_SHORT).show();
                       Intent intent = new Intent(RegisterActivty.this ,otp.class);
                       extra p2 = new extra();
                       p2.setMobil(name);
                       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                       intent.putExtra("object",(Serializable) p2);
                       startActivity(intent);
                       finish();
                   }
                    }
                });

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(RegisterActivty.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}