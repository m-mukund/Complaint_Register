package com.example.complaintregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class complaint_signup extends AppCompatActivity {

    private FirebaseAuth auth;

    EditText email;
    EditText phone;
    EditText pass;
    EditText confpass;
    EditText age;
    EditText username;
    Button signupB;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_signup);

        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.PhoneNo);
        pass=(EditText)findViewById(R.id.signupPassword);
        confpass=(EditText)findViewById((R.id.confirmPassword));
        age=(EditText)findViewById(R.id.age);
        username=(EditText)findViewById(R.id.username);
        signupB=(Button)findViewById(R.id.complaintSignupReg);
        spinner = (Spinner) findViewById(R.id.gender_spinner);


        ImageButton back_signup=(ImageButton)findViewById(R.id.back_signup);
        String[] genders=getResources().getStringArray(R.array.gendersArray);
        auth=FirebaseAuth.getInstance();

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_spinner_item,genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);




        back_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        signupB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emailTxt=email.getText().toString();
                String passTxt=pass.getText().toString();
                String confTxt=confpass.getText().toString();
                String phoneTxt=phone.getText().toString();
                String ageTxt=age.getText().toString();
                String usrname=username.getText().toString();
                String sex = spinner.getSelectedItem().toString();


                if(TextUtils.isEmpty(emailTxt)||TextUtils.isEmpty(passTxt)||TextUtils.isEmpty(confTxt)||TextUtils.isEmpty(usrname)||TextUtils.isEmpty(sex)){
                    Toast.makeText(complaint_signup.this,"Empty Credentials",Toast.LENGTH_LONG).show();
                }
                if(!(passTxt.equals(confTxt))){
                    Toast.makeText(complaint_signup.this,"Passwords do not match",Toast.LENGTH_SHORT).show();
                }
                else{
                    regUser(emailTxt,passTxt,phoneTxt,ageTxt,usrname,sex);
                }
            }
        });

    }

    private void regUser(final String emailTxt, final String passTxt, final String phoneTxt, final String ageTxt, final String usrname, final String sex) {
        auth.createUserWithEmailAndPassword(emailTxt,passTxt).addOnCompleteListener(complaint_signup.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(complaint_signup.this,"Account Created",Toast.LENGTH_SHORT).show();
                    FirebaseDatabase.getInstance().getReference().child("User").child(usrname).setValue(usrname);
                    HashMap<String,Object> map=new HashMap<>();
                    map.put("Email",emailTxt);
                    map.put("Pass",passTxt);
                    map.put("PhoneNo",phoneTxt);
                    map.put("Age",ageTxt);
                    map.put("Sex",sex);
                    FirebaseDatabase.getInstance().getReference().child("User").child(usrname).updateChildren(map);
                    auth.signInWithEmailAndPassword(emailTxt,passTxt).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(complaint_signup.this,"Logged In",Toast.LENGTH_SHORT).show();
                            Intent signupInt=new Intent(complaint_signup.this,UserHome.class);
                            startActivity(signupInt);

                        }
                    });
                }else{
                    Toast.makeText(complaint_signup.this,"Account Not Created",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}