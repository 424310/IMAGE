package com.example.image;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageMetadata;
import com.google.firebase.storage.StorageReference;

public class readImage extends AppCompatActivity {

    private ImageView imageView;

    private String userID, name, url;

    private FirebaseStorage storage;
    private StorageReference storageRef, mstorageRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_image);

        imageView = (ImageView) findViewById(R.id.imageView);

        userID = "서민지";
        name = "갯마을";

        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        mstorageRef = storageRef.child(userID).child("Category");

        mstorageRef.child(name+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               url = uri.toString();
               Glide.with(getApplicationContext()).load(url).into(imageView);
            }
        });
    }
}
