package com.example.ahabdelhak.userapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button buttonLogin;
    private EditText editTextEmail, editTextPassword;
    private String email, password;

    private ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        mDialog = new ProgressDialog(this);

        buttonLogin = findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTextEmail = findViewById(R.id.ededmail);
                editTextPassword = findViewById(R.id.edPass);

                email = editTextEmail.getText().toString().trim();
                password = editTextPassword.getText().toString().trim();
                userLogin();
            }
        });

    }


    private void userLogin(){
        mDialog.setMessage("Login Please Wait...");
        mDialog.setIndeterminate(true);
        mDialog.show();

        if(email.isEmpty()){
            //editTextEmail.setError("Harap Masukan Email");
            editTextEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            //editTextEmail.setError("Tolong Masukan Email Yang Valid");
            editTextEmail.requestFocus();
            return;
        }


        if(password.isEmpty()){
           // editTextPassword.setError("Harap Masukan Password");
            editTextPassword.requestFocus();
            return;
        }

        if(password.length()<6){
           // editTextPassword.setError("Password Harus Memiliki Minimal 6 Karakter");
            editTextPassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (task.isSuccessful()){
                    mDialog.dismiss();
                    updateUI(user);
                }else{
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateUI(FirebaseUser user) {
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();

        Intent in = new Intent(this,GpsLocation.class);
        startActivity(in);
    }
}
