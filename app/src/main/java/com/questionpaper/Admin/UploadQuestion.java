package com.questionpaper.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.questionpaper.Model.Book;
import com.questionpaper.Model.RegisterData;
import com.questionpaper.R;
import com.questionpaper.databinding.ActivityUploadQuestionBinding;

import java.io.File;

public class UploadQuestion extends AppCompatActivity {
    private static final String TAG = "UploadQuestion";
    ActivityUploadQuestionBinding binding;
    Uri  pdfuri;
    boolean seleted= false;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityUploadQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference();
       binding.pdfname.setText("Not Seleted");
        binding.selectpdf.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("application/pdf");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select PDF"), 1);
        });

       binding.uploadbtn.setOnClickListener(v -> {
           if(seleted){

               Book register = new Book();
                  register.setName(binding.qname.getText().toString());
                  register.setUrl(String.valueOf(pdfuri));

               String id= reference.push().getKey();
                   reference.child("book").child(id).setValue(register);
//               FirebaseStorage storage = FirebaseStorage.getInstance();
//               Uri file = pdfuri;
//               StorageReference storageRef = storage.getReferenceFromUrl("gs://question-paper-b59d4.appspot.com/");
//               StorageReference riversRef = storageRef.child("images/"+file.getLastPathSegment());
//             UploadTask    uploadTask = riversRef.putFile(file);
//
//// Register observers to listen for when the download is done or if it fails
//               uploadTask.addOnFailureListener(new OnFailureListener() {
//                   @Override
//                   public void onFailure(@NonNull Exception exception) {
//                       // Handle unsuccessful uploads
//                       Log.d(TAG, "onFailure: "+exception);
//                   }
//               }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                   @Override
//                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                       // taskSnapshot.getMetadata() contains file metadata such as size, content-type, etc.
//                       // ...
//                       Log.d(TAG, "onSuccess: "+taskSnapshot);
//                       Toast.makeText(UploadQuestion.this, "Succesfull", Toast.LENGTH_SHORT).show();
//                   }
//               });
           } else {
               Toast.makeText(this, "Select Pdf First", Toast.LENGTH_SHORT).show();
           }
       });


//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageRef = storage.getReferenceFromUrl("gs://your-app-name.com");
//
//        Uri file = Uri.fromFile(new File("data/data/file-path/file-name"));
//        Log.d("file", file.getPath());
//
//
//        StorageReference riversRef = storageRef.child("firebase-storage");
//
//        UploadTask uploadTask = riversRef.putFile(file);
//
//// Register observers to listen for when the download is done or if it fails
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle unsuccessful uploads
//                Log.d("uploadFail", "" + exception);
//
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
//               // sendNotification("upload backup", 1);
//
//                Uri downloadUrl = taskSnapshot.getDownloadUrl();
//
//                Log.d("downloadUrl", "" + downloadUrl);
//            }
//        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent result) {
        super.onActivityResult(requestCode, resultCode, result);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                pdfuri = result.getData();
                Toast.makeText(this, ""+pdfuri, Toast.LENGTH_SHORT).show();
                binding.pdfname.setText("Seleted");
                seleted= true;
            }
        }
    }
}