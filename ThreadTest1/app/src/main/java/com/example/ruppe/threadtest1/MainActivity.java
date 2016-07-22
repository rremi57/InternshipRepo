package com.example.ruppe.threadtest1;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ruppe on 21/07/2016.
 */
public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Thread for Files
        RandomStuff myRunnable = new RandomStuff(this);
        Thread myThread = new Thread(myRunnable);
        myThread.start();



        context = this;
    }
}
