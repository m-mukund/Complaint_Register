package com.example.complaintregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class UserHome extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);


        Button regComp=(Button)findViewById(R.id.RegComp);
        Button logoutB=(Button)findViewById(R.id.LogOutBtn);

        logoutB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(UserHome.this,"Logged Out",Toast.LENGTH_SHORT).show();
                finish();
//                startActivity(new Intent(UserHome.this,Login_complaint.class));
            }
        });

        regComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent=new Intent(UserHome.this,SubmitComp.class);
                startActivity(regIntent);
            }
        });

    }
}