package com.example.edu.myapp1115_progressbar;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class BeforeActivity extends AppCompatActivity implements View.OnClickListener {

    private final int REQUEST_CODE_1 = 10001;
    private final int REQUEST_CODE_2 = 10002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_before);
        requirespermission();

        ((Button)findViewById(R.id.button1)).setOnClickListener(this);
        ((Button)findViewById(R.id.button2)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button1) {
            Intent intent = new Intent(v.getContext(), MainActivity.class);
            startActivityForResult(intent, REQUEST_CODE_1);
        }
        else if(v.getId()==R.id.button2) {
            Intent intent = new Intent(v.getContext(), Main2Activity.class);
            startActivityForResult(intent, REQUEST_CODE_2);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    final int PERMISSION_REQUEST_CODE = 99999;
    void requirespermission() {
        String[] permissions = new String[] {Manifest.permission.INTERNET};
        ArrayList<String> requestPermissions = new ArrayList<>();

        for(String permission: permissions) {
            if(ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED)
                requestPermissions.add(permission);
        }

        if(!requestPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this, requestPermissions.toArray(new String[requestPermissions.size()]), PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean granted = true;
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                for(int i=0; i<grantResults.length; i++) {
                    if(grantResults[i]!=PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        System.out.println("****************** granted: false");
                    }
                }
        }
    }
}
