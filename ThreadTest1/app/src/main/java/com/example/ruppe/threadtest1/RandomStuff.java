package com.example.ruppe.threadtest1;
import android.util.Log;

public class RandomStuff implements Runnable {

    Thread backgroundThread;

    public void start() {
        if (backgroundThread == null) {
            backgroundThread = new Thread(this);
            backgroundThread.start();
        }
    }

    public void stop() {
        if (backgroundThread != null) {
            backgroundThread.interrupt();
        }
    }
    public void doingStuff()
    {
        Log.i("ThreadRunning", "I'm Running and spamming over there");
    }

    public void run() {
        try {
            Log.d("Thread", "Thread starting.");
            while (!backgroundThread.interrupted()) {
                doingStuff();
                Thread.sleep(4000);
            }
            Log.i("Thead", "Thread stopping.");
        } catch (InterruptedException ex) {
            // important you respond to the InterruptedException and stop processing
            // when its thrown!  Notice this is outside the while loop.
            Log.i("Thread", "Thread shutting down as it was requested to stop.");
        } finally {
            backgroundThread = null;
        }
    }
}