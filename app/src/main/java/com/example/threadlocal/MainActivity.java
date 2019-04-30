package com.example.threadlocal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivityThradLocal";
    private ThreadLocal<Boolean> mThreadLocalBoolean = new ThreadLocal<>();
    private ThreadLocal<Integer> mThreadLocalInt = new ThreadLocal<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mThreadLocalBoolean.set(true);
        mThreadLocalInt.set(3);
        Log.d(TAG, "MainThread onCreate() returned: " + mThreadLocalInt.get() + "/" + mThreadLocalBoolean.get());
        thread1();
        thread2();
    }

    private void thread1() {
        new Thread("Thread 1") {
            @Override
            public void run() {
                mThreadLocalBoolean.set(false);
                mThreadLocalInt.set(1);
                Log.d(TAG, "Thread 1 run() returned: " + mThreadLocalInt.get() + "/" + mThreadLocalBoolean.get());
            }
        }.start();
    }

    private void thread2() {
        new Thread("Thread 2") {
            @Override
            public void run() {
                mThreadLocalBoolean.set(true);
                mThreadLocalInt.set(2);
                Log.d(TAG, "Thread 2 run() returned: " + mThreadLocalInt.get() + "/" + mThreadLocalBoolean.get());
            }
        }.start();
    }

}
