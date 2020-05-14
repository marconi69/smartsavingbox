package com.example.takotlin;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pusher.pushnotifications.PushNotifications;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.takotlin.login.TEXT;


public class main extends AppCompatActivity {
    private Button btnlogout,btnunlock,his;
    private TextView name, nohp, waktu, addrs;
    public String Username, nama,notif;
    DatabaseReference myRef, brankas;
    public static final String SHARED_PREFS = "shared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PushNotifications.start(getApplicationContext(), "ced6f5a4-94dd-4782-99d5-6055b1602e30");
        PushNotifications.addDeviceInterest("hello");
        btnlogout = findViewById(R.id.logout);
        btnunlock = findViewById(R.id.unlock);
        his = findViewById(R.id.history);
        name = findViewById(R.id.Nama);
        addrs = findViewById(R.id.address);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        Username = sharedPreferences.getString(TEXT, "");
        myRef = FirebaseDatabase.getInstance().getReference("user");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                Username = sharedPreferences.getString(TEXT, "");

                nama = String.valueOf(dataSnapshot.child(Username).child("nama").getValue());
                String address = String.valueOf(dataSnapshot.child(Username).child("alamat").getValue());
                name.setText(nama);
                addrs.setText(address);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Gagal menampilkan profil", Toast.LENGTH_LONG).show();
            }
        });


        his.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Anda melihat history ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(main.this, ListHistory.class));
                //startActivity(new Intent(main.this, history.class));
            }
        });

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Anda telah logout ", Toast.LENGTH_LONG).show();
                startActivity(new Intent(main.this, login.class));
            }
        });
        btnunlock.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Anda telah membuka brankas", Toast.LENGTH_LONG).show();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("device_info");

                String status_pintu = ("status_pintu");
                String kt = (status_pintu);
                myRef.child(kt).setValue(1);

                kirim_history();


            }

        });



        }
        public void kirim_history() {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("History");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();
            myRef.push().setValue(new History(nama, sdf.format(now), "Android"));
            Toast.makeText(getApplicationContext(), "History save", Toast.LENGTH_SHORT).show();
    }
}

