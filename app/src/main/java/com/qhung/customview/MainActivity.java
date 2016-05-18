package com.qhung.customview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qhung.library.widget.CircleProgressBar;

public class MainActivity extends AppCompatActivity {
   private  CircleProgressBar circleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circleProgressBar = (CircleProgressBar) findViewById(R.id.progressbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                circleProgressBar.setMaxValue(100);
                circleProgressBar.setProgress(90);
            }
        },1000);

    }
}
