package com.questionpaper.Login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.questionpaper.MainActivity;
import com.questionpaper.Model.RegisterData;
import com.questionpaper.R;
import com.questionpaper.databinding.ActivityRegisterBinding;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    ActivityRegisterBinding binding;
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mAuth = FirebaseAuth.getInstance();
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();


          binding.registerbtn.setOnClickListener(v -> {
              String email = binding.mobilenoet.getText().toString().trim();
              String password = binding.emppassword.getText().toString().trim();
              String username = binding.empnameet.getText().toString().trim();
              String course = binding.empcourse.getText().toString().trim();
              String enno = binding.enrollment.getText().toString().trim();

              if (email.isEmpty()){
                  binding.mobilenoet.setError("Enter Mobile Number");
                  binding.mobilenoet.requestFocus();
                  return;
              }


              if (TextUtils.isEmpty(password)){
                  binding.emppassword.setError("Password is Empty");
                  return;
              }
              if (password.length()<6){
                  binding.emppassword.setError("Password is short");
                  return;
              }
              if (username.isEmpty()){
                  binding.empnameet.setError("Enter Name");
                  return;
              }
              mAuth.createUserWithEmailAndPassword(binding.mobilenoet.getText().toString(), binding.emppassword.getText().toString())
                      .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {


                          @Override
                          public void onComplete(@NonNull Task<AuthResult> task) {
                              if (task.isSuccessful()) {
                                  // Sign in success, update UI with the signed-in user's information
                                  Log.d(TAG, "signInWithEmail:success");
                                  FirebaseUser user = mAuth.getCurrentUser();
                                  String uid=  FirebaseAuth.getInstance().getCurrentUser().getUid();
                                  RegisterData register = new RegisterData(username, password, email,uid,course,enno);

                                  reference.child("user").child(uid).setValue(register);
                                  Toast.makeText(RegisterActivity.this, "Successfully Registered.. ", Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                 // updateUI(user);
                              } else {
                                  // If sign in fails, display a message to the user.
                                  Log.w(TAG, "signInWithEmail:failure", task.getException());
                                  Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                          Toast.LENGTH_SHORT).show();
                                 // updateUI(null);
                              }
                          }
                      });
          });
    }
}