package com.questionpaper.Student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.questionpaper.Adapter.WithdrawAdapter;
import com.questionpaper.Login.LoginActivity;
import com.questionpaper.Model.Book;
import com.questionpaper.R;
import com.questionpaper.databinding.ActivityStudentDashBoardBinding;

import java.util.ArrayList;

public class StudentDashBoard extends AppCompatActivity {
    private static final String TAG = "StudentDashBoard";
    ActivityStudentDashBoardBinding boardBinding;
    ArrayList<Book> book;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        boardBinding= ActivityStudentDashBoardBinding.inflate(getLayoutInflater());
        setContentView(boardBinding.getRoot());
        book= new ArrayList<>();
        boardBinding.logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();

            startActivity(new Intent(this, LoginActivity.class));
        });
        getbook();

    }
    private void getbook() {

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("book");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Book movie = dataSnapshot.getValue(Book.class);
                    book.add(movie);
                    Log.d(TAG, "onDataChange: "+ snapshot);
                    // Toast.makeText(getContext(), ""+ movie, Toast.LENGTH_SHORT).show();
//                  int d= getTimedifference(movie.getBuyDate());
//                  if(getTimedifference(movie.getBuyDate())<=Integer.parseInt(movie.getExpiryDate()))


                }

                WithdrawAdapter withdrawAdapter= new WithdrawAdapter(StudentDashBoard.this,book);
                // mwalletamu.setText(String.valueOf(invest+interest));
                boardBinding.booklist.setLayoutManager(new LinearLayoutManager(StudentDashBoard.this));
                boardBinding.booklist.setAdapter(withdrawAdapter);





            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}