package com.example.takotlin;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login extends AppCompatActivity {

    private Button btnMasuk;
    TextInputEditText user, loginPassword;
    TextView tvBuat;
    String nama,pwdf;
    private static final String TAG = "Login";
    public static final  String SHARED_PREFS = "shared";
    public static final  String TEXT = "text";
    DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnMasuk = findViewById(R.id.btnMasuk);
        tvBuat = findViewById(R.id.registerakun);
        loginPassword = findViewById(R.id.logpwd);
        user = findViewById(R.id.logusr);
        database = FirebaseDatabase.getInstance().getReference("user");
        String text = "Belum Mendaftar? Daftar di sini";
        SpannableString sini = new SpannableString(text);
        ClickableSpan klik = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                daftar();
            }
        };

        sini.setSpan(klik, 27, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvBuat.setText(sini);
        tvBuat.setMovementMethod(LinkMovementMethod.getInstance());

        btnMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String uname = user.getText().toString();
                final String pwd = loginPassword.getText().toString();
                if (uname.isEmpty() || pwd.isEmpty()){
                    showMessage("Lengkapi data login");
                } else {
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final String uname = user.getText().toString();
                            final String pwd = loginPassword.getText().toString();
                            nama = String.valueOf(dataSnapshot.child(uname).child("nama").getValue());
                            pwdf = String.valueOf(dataSnapshot.child(uname).child("pass").getValue());

                            if (nama == "null") {
                                showMessage("Anda belum melakukan registrasi");
                            }
                            else if (pwd.equals(pwdf)) {
                                showMessage("Berhasil masuk sebagai " + nama);
                                masukHome();
                            }
                            else if (pwdf != pwd){
                                showMessage("Password anda salah");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }
    public void daftar(){


        Intent intent = new Intent(this, regis.class);
        startActivity(intent);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("device_info");

        String status_register = ("status_register");

        String kt = (status_register);
        myRef.child(kt).setValue(1);

    }

    private void showMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }

    private void masukHome() {


        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TEXT, user.getText().toString());
        editor.apply();

        Intent intent = new Intent(this, main.class);
        startActivity(intent);

    }
}
