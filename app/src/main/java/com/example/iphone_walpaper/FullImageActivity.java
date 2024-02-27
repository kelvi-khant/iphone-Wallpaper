package com.example.iphone_walpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullImageActivity extends AppCompatActivity {

    private static int REQUEST_CODE = 100;
    ImageView full_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_full_image);

        full_image = findViewById (R.id.full_image);

        Glide.with (this).load (getIntent().getStringExtra ("image")).into(full_image);

        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
            if (checkSelfPermission (android.Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
//                String[] pemissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
//                requestPermissions (pemissions,PERMISSION_STORE_CODE);
            }else{
//                Downloading();
            }
        }
        else {
//            Downloading();
        }
    }
}