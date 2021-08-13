package com.questionpaper.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.questionpaper.Model.Book;
import com.questionpaper.R;

import java.io.File;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WithdrawAdapter extends RecyclerView.Adapter<WithdrawAdapter.ViewHolder> {
    private static final String TAG = "Recharg";

    private Context mContext;
    private List<Book> mUsers;
    int selectedPosition = -1;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    public WithdrawAdapter(Context mContext, List<Book>mUsers){
        this.mContext= mContext;
        this.mUsers=mUsers;


        Log.d(TAG, "Recharg: "+mUsers);

    }
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bookitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  WithdrawAdapter.ViewHolder holder, int position) {
        final Book user= mUsers.get(position);
        Log.d(TAG, "onBindViewHolder: "+user);
        holder.name.setText("Remarks: " +user.getName());

        holder.date.setVisibility(View.VISIBLE);
      holder.download.setOnClickListener(v -> {
          File file = new File(user.getUrl());

          if (file.exists()) {

              Uri pdfPath = Uri.fromFile(file);
              Intent intent = new Intent(Intent.ACTION_VIEW);
              intent.setDataAndType(pdfPath, "application/pdf");

              try {
                  mContext.startActivity(intent);
              } catch (ActivityNotFoundException e) {
                  //if user doesn't have pdf reader instructing to download a pdf reader
              }


          }
      });


    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name,date,download;

        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.name);
            date=(TextView)itemView.findViewById(R.id.date);
            download=(TextView)itemView.findViewById(R.id.download);

        }
    }

}
