package com.questionpaper.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.questionpaper.Login.RegisterActivity;
import com.questionpaper.R;
import com.questionpaper.databinding.ActivityDashBoardBinding;

public class DashBoard extends AppCompatActivity {
      ActivityDashBoardBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityDashBoardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.uploadquestion.setOnClickListener(v -> {
            startActivity(new Intent(this, UploadQuestion.class));
        });
    }
}