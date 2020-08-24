package com.example.complaintregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

public class AdminHome extends AppCompatActivity {

    ListView compList;
    Button logoutAdm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        compList=(ListView)findViewById(R.id.CompList);
        logoutAdm=(Button)findViewById(R.id.AdminLogout);

    }
}