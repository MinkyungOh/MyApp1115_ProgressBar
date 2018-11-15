package com.example.edu.myapp1115_progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private int mProgressBarStatus = 0;
    private ProgressBar progressBarPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.googlelogo_color_272x92dp);

        progressBarPost = (ProgressBar)findViewById(R.id.progressBar);

        ((Button)findViewById(R.id.runButton)).setOnClickListener(this);
        ((Button)findViewById(R.id.button3)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.button3) {
            finish();
        }
        else if(v.getId()==R.id.runButton) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (mProgressBarStatus <100) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mProgressBarStatus ++;
                        progressBarPost.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBarPost.setProgress(mProgressBarStatus);
                            }
                        });
                    }
                }
            }).start();
        }
    }
}
