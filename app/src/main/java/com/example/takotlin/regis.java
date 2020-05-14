package com.example.takotlin;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class regis extends AppCompatActivity {
    private TextInputEditText editNama, editUname, editPassword, editAddress, editHP, editEmail;
    private Button btnRegis;
    TextView login;

    DatabaseReference databaseUser, db;

    List<User> daftarUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);
        databaseUser = FirebaseDatabase.getInstance().getReference("user");
        db = FirebaseDatabase.getInstance().getReference("device_info");
        daftarUser = new ArrayList<User>();
        ////////////////////////////////////////////

        editNama = findViewById(R.id.regisname);
        editHP = findViewById(R.id.regisphn);
        editUname = findViewById(R.id.regisuser);
        editEmail = findViewById(R.id.regisemail);
        editAddress = findViewById(R.id.regisaddr);
        editPassword = findViewById(R.id.regispwd);
        btnRegis = findViewById(R.id.register);
        login = findViewById(R.id.sudahpunya);



        btnRegis.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View v) {
                final String nama = editNama.getText().toString();
                final String hp = editHP.getText().toString();
                final String uname = editUname.getText().toString();
                final String email = editEmail.getText().toString();
                final String alamat = editAddress.getText().toString();
                final String pwd = editPassword.getText().toString();
                if (nama.isEmpty() || hp.isEmpty() || alamat.isEmpty() || uname.isEmpty() || email.isEmpty()|| pwd.isEmpty()){
                    showMessage("Lengkapi data registrasi");
                } else {
                    login();
                    showMessage("Akun berhasil didaftarkan, silahkan scan wajah dan sidik jari untuk melengkapi data ");
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(), login.class));

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("device_info");

                String status_register = ("status_register");

                String kt = (status_register);
                myRef.child(kt).setValue(0);

            }
        });
    }


    private void showMessage(String message){
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
    }



    public void login(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        String status_register = ("status_register");
        String pemilik = ("pemilik");
        String nama =  editNama.getText().toString();
        String hp = editHP.getText().toString();
        String uname =  editUname.getText().toString();
        String email = editEmail.getText().toString();
        String alamat = editAddress.getText().toString();
        String pass =  editPassword.getText().toString();

        String id = (uname);
        myRef.child(id).setValue(uname);
        myRef.child(id).child("nama").setValue(nama);
        myRef.child(id).child("nomorHP").setValue(hp);
        myRef.child(id).child("email").setValue(email);
        myRef.child(id).child("username").setValue(uname);
        myRef.child(id).child("pass").setValue(pass);
        myRef.child(id).child("alamat").setValue(alamat);

        String kt = (status_register);
        myRef.child(kt).setValue(1);

        String user = (pemilik);
        myRef.child(user).setValue(uname);

        Intent intent = new Intent(this, login.class);
        startActivity(intent);

    }
}
