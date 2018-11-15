package com.example.edu.myapp1115_progressbar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private int mProgressBarStatus = 0;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = (ImageView)findViewById(R.id.imageView2);

        ((Button)findViewById(R.id.runButton2)).setOnClickListener(this);
        ((Button)findViewById(R.id.backButton2)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.backButton2) {
            finish();
        }
        else if(v.getId()==R.id.runButton2) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String strUri = "https://www.google.co.kr/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png";

                    try {
                        URL url = new URL(strUri);
                        HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                        httpURLConnection.connect();
                        InputStream inputStream = httpURLConnection.getInputStream();
                        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        imageView.post(new Runnable() {
                            @Override
                            public void run() {
                                imageView.setImageBitmap(bitmap);
                            }
                        });

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
