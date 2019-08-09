package com.zulfa.furnitureapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button pesan;
    Dialog epicdialog;
    EditText name, passwd, regnama, regpasswd;
    Button login, daftar;
    TextView register;
    ImageView closed;
    FirebaseAuth firebaseAuth, mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        epicdialog = new Dialog(this);

        pesan = findViewById(R.id.btn_pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
    }

    public void dialog(){
        mAuth = FirebaseAuth.getInstance();
        epicdialog.setContentView(R.layout.login_popup);
        closed = epicdialog.findViewById(R.id.close);
        name = epicdialog.findViewById(R.id.et_nama);
        passwd = epicdialog.findViewById(R.id.et_pass);
        login = epicdialog.findViewById(R.id.btn_masuk);
        register = epicdialog.findViewById(R.id.tv_daftart);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicdialog.dismiss();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog proo = ProgressDialog.show(MainActivity.this, "Tunggu", "Proses", true);
                (mAuth.signInWithEmailAndPassword(name.getText().toString(), passwd.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                            proo.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Yashhh Masuk", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(MainActivity.this, RegiterActivity.class);
                                intent.putExtra("Email", mAuth.getCurrentUser().getEmail());
                                startActivity(intent);
                            }
                            else {
                                Log.e( "ERROR", task.getException().toString());
                                Toast.makeText(MainActivity.this, task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                            }
                        });
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daftarr();
            }
        });
        epicdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicdialog.show();
    }

    public void daftarr(){
        firebaseAuth = FirebaseAuth.getInstance();
        epicdialog.setContentView(R.layout.regist_popup);
        closed = epicdialog.findViewById(R.id.close);
        regnama = epicdialog.findViewById(R.id.et_dafnama);
        regpasswd = epicdialog.findViewById(R.id.et_dafpass);
        daftar = epicdialog.findViewById(R.id.btn_daftar);
        closed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                epicdialog.dismiss();
            }
        });
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressDialog = ProgressDialog.show(MainActivity.this, "Tunggu", "Proses", true);
                (firebaseAuth.createUserWithEmailAndPassword(regnama.getText().toString(), regpasswd.getText().toString()))
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();

                                if (task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Daftar Berhasil", Toast.LENGTH_LONG).show();
                                    dialog();
                                }else {
                                    Log.e("ERROR", task.getException().toString());
                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                }
                            }
                        });
            }
        });
        epicdialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        epicdialog.show();
    }
}
