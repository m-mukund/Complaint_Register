package com.example.complaintregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button complaint=(Button)findViewById(R.id.register_button);
        Button login=(Button)findViewById(R.id.adminlogin_button);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent adminIntent=new Intent(MainActivity.this,AdminLogin.class);
                startActivity(adminIntent);
            }
        });
        complaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent complaintIntent=new Intent(MainActivity.this,Login_complaint.class);
                startActivity(complaintIntent);
            }
        });
    }
}
