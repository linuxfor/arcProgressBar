package com.qhung.customview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.qhung.library.widget.ArcProgressBar;

public class MainActivity extends AppCompatActivity {
   private ArcProgressBar arcProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        arcProgressBar = (ArcProgressBar) findViewById(R.id.progressbar);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                arcProgressBar.setMaxValue(100);
                arcProgressBar.setProgress(100);
            }
        },1000);

    }
}
