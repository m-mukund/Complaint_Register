package com.example.complaintregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SubmitComp extends AppCompatActivity {

    EditText subject;
    EditText desc;
    EditText date;
    EditText place;
    EditText userName;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_comp);
        subject=(EditText)findViewById(R.id.Subject);
        desc=(EditText)findViewById(R.id.Complaint_Descriptioon);
        date=(EditText)findViewById(R.id.Date_Inp);
        place=(EditText)findViewById(R.id.Place);
        Button sbtBtn=(Button)findViewById(R.id.SubmitComplaint);
        userName=(EditText)findViewById(R.id.usernameForComp);


        sbtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectTxt=subject.getText().toString();
                String description=desc.getText().toString();
                String dateTxt=date.getText().toString();
                String placeTxt=place.getText().toString();
                String usrname=userName.getText().toString();
                if(TextUtils.isEmpty(subjectTxt)||TextUtils.isEmpty(description)||TextUtils.isEmpty(description)||TextUtils.isEmpty(usrname)){
                    Toast.makeText(SubmitComp.this,"Empty Fields",Toast.LENGTH_SHORT).show();
                }
                else {
                    regComp(subjectTxt,description,dateTxt,placeTxt,usrname);
                }
            }
        });

    }

    private void regComp(String subjectTxt, String description, String dateTxt, String placeTxt, String usrname) {
        HashMap<String,Object> map=new HashMap<>();
        map.put("Subject",subjectTxt);
        map.put("Description",description);
        map.put("Date",dateTxt);
        map.put("Place",placeTxt);

        FirebaseDatabase.getInstance().getReference().child("User").child(usrname).push().updateChildren(map);
        Toast.makeText(SubmitComp.this,"Complaint Submitted",Toast.LENGTH_SHORT).show();
        finish();

    }
}