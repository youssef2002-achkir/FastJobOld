package com.example.job_portal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ACCESSADMINDASHACTIVITY extends AppCompatActivity {

    TextInputEditText secretkeyfield;
    Button toDashBtn;
    DatabaseReference DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessadmindashactivity);

        secretkeyfield = findViewById(R.id.secretKey);
        toDashBtn = findViewById(R.id.toDashboard);
        DB = FirebaseDatabase.getInstance().getReference();
        DatabaseReference secretKey = DB.child("SecretKey");
        secretKey.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                System.out.println(snapshot.getValue().toString());
                toDashBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (secretkeyfield.getText().toString().equals(snapshot.getValue().toString())){
                            startActivity(new Intent(ACCESSADMINDASHACTIVITY.this, AdminDashbordActivity.class));
                        }else{
                            Toast.makeText(ACCESSADMINDASHACTIVITY.this, "ACCESS DENIED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}