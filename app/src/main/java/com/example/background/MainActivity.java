package com.example.background;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Intent mServiceIntent;
    private OwnService service;
    Context ctx;
    public Context getCtx() { return ctx; }

    Button start , stop;
    Switch toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_main);
        service = new OwnService(getCtx());
        mServiceIntent = new Intent(getCtx(), service.getClass());

        start =(Button) findViewById(R.id.start);
        stop =(Button) findViewById(R.id.stop);

//        Intent i = getPackageManager().getLaunchIntentForPackage("com.google.autoclick");
//        startActivity(i);
    }



    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }


    @Override
    protected void onDestroy() {
        stopService(mServiceIntent);
        super.onDestroy();
    }

    public void start(View view) {
        if (!isMyServiceRunning(service.getClass())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                start.setBackgroundColor(getColor(R.color.gold));
                stop.setBackgroundColor(getColor(R.color.white));
            }

            Toast.makeText(getApplicationContext(), "Service Started", Toast.LENGTH_SHORT).show();
            startService(mServiceIntent);

            //for background
            Intent i = new Intent();
            i.setAction(Intent.ACTION_MAIN);
            i.addCategory(Intent.CATEGORY_HOME);
            this.startActivity(i);

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Service Needs To Be Stopped", Toast.LENGTH_SHORT).show();
        }
    }

    public void stop(View view) {
        if (isMyServiceRunning(service.getClass())) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                stop.setBackgroundColor(getColor(R.color.gold));
                start.setBackgroundColor(getColor(R.color.white));
            }

            Toast.makeText(getApplicationContext(), "Service Stopped", Toast.LENGTH_SHORT).show();
            stopService(mServiceIntent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Service Needs To Be Started", Toast.LENGTH_SHORT).show();
        }
    }



}


