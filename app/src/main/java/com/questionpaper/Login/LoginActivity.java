package com.questionpaper.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.questionpaper.Admin.DashBoard;
import com.questionpaper.MainActivity;
import com.questionpaper.R;
import com.questionpaper.Student.StudentDashBoard;
import com.questionpaper.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();
        if (!(users ==null)) {
            // User is signed in
            Intent i = new Intent(LoginActivity.this, StudentDashBoard.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        } else {
            // User is signed out
            Log.d(TAG, "onAuthStateChanged:signed_out");
        }
        binding.signuplink.setOnClickListener(v -> {
            startActivity(new Intent(this, RegisterActivity.class));
        });

        binding.adminlogin.setOnClickListener(v -> {
            startActivity(new Intent(this, DashBoard.class));
        });

        binding.cirLoginButton.setOnClickListener(v -> {
            FirebaseAuth mfire;
            mfire=FirebaseAuth.getInstance();
            mfire.signInWithEmailAndPassword(binding.editTextEmail.getText().toString(),binding.editTextPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                  @Override
                                  public void onComplete(@NonNull Task<AuthResult> task) {

                                      if (task.isSuccessful()){
                                        //  progressBar.setVisibility(View.GONE);
                                         // Toast.makeText(Login.this, "login successful", Toast.LENGTH_SHORT).show();
                                         // setdata();
                                          startActivity(new Intent(getApplicationContext(), StudentDashBoard.class));

                                      }else {
                                          Toast.makeText(LoginActivity.this, "Error  ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                      }
                                  }
                              });
        });
    }
}