package com.example.complaintregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_complaint extends AppCompatActivity {

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_complaint);
        ImageButton back_comp=(ImageButton)findViewById(R.id.Back_button_comp);

        back_comp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        final EditText emailLog=(EditText)findViewById(R.id.Email);
        final EditText passLog=(EditText)findViewById(R.id.Password_comp);
        Button logInB=(Button)findViewById(R.id.Login_button);

        auth=FirebaseAuth.getInstance();

        logInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailTxt=emailLog.getText().toString();
                String passTxt=passLog.getText().toString();
                loginUser(emailTxt,passTxt);
            }
        });

        Button signupButton=(Button)findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent=new Intent(Login_complaint.this,complaint_signup.class);
                startActivity(signupIntent);
            }
        });

    }

    private void loginUser(String emailTxt, String passTxt) {
        auth.signInWithEmailAndPassword(emailTxt,passTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                startActivity(new Intent(Login_complaint.this,UserHome.class));
                finish();
            }
        });
    }
}