package com.example.background;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class OwnService extends Service {

    private Timer timer;
    private TimerTask timerTask;
    Random random=new Random();
    static int punch1 = 0;
    static int punch2 = 0;
    int random_min_1 , random_min_2 ;
    String message1,message2;
    static int check_day;

    static String[] days = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

    public OwnService(Context applicationContext) {
        super();
    }

    public OwnService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        NotificationHelper NH = new NotificationHelper(this);
        NH.createNotification("Service Running" ,"", "10001" , "name1", 1);

        startTimer();
        return START_STICKY;
    }


    // this is called when  stopService(mServiceIntent); is called
    @Override
    public void onDestroy() {
        NotificationHelper NH = new NotificationHelper(this);
        NH.createNotification("Service Stopped" ,"" , "10001" , "name1", 1);
        stoptimertask(); // actually this stops the timer which makes service useless
        super.onDestroy();
    }

    public void startTimer() {
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                noty();
            }
        };
        timer.schedule(timerTask, 300000, 300000); //
    }

    public void stoptimertask() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    private void noty() {

        random_min_1 = random.nextInt(10) + random.nextInt(10);
        random_min_2 = random.nextInt(10);

        Date currentTime = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK); // 1-sunday , 2-monday , 5-thursday , 7-saturday
        int hourofday = calendar.get(Calendar.HOUR_OF_DAY); //21
        int minute = calendar.get(Calendar.MINUTE); // 9
        int seconds = calendar.get(Calendar.SECOND); // 9


        if(check_day != day )
        {
            check_day = day;
            punch1 = 0;
            punch2 = 0;
        }

        if(punch1==1)
        {
            message1 = "Punch One - Yes";
        }
        else
        {
            message1 = "Punch One - No";
        }

        if(punch2==1)
        {
            message2 = "Punch Two - Yes";
        }
        else
        {
            message2 = "Punch Two - No";
        }


        if( day == 2 || day == 3 || day == 4 || day == 5 || day == 6 )
        {
            NotificationHelper NH = new NotificationHelper(this);
            NH.createNotification( days[day-1]+" "+currentTime,random_min_1+":"+random_min_2+" "+message1+" | "+message2 , "10001" , "name1", 1);

            if( ( hourofday>=9 && hourofday<=12) && punch1!=1 && minute > random_min_1 )
            {
                punch1++;
                startapp();
                NotificationHelper NH2 = new NotificationHelper(this);
                NH2.createNotification2("Punch Service One Was Initiated At" ,""+currentTime , "10002" , "name2", 2);
            }

            if( ( ( hourofday>=21 && minute > 30+random_min_2 ) && hourofday<=22 ) && punch2!=1  )
            {
                punch2++;
                startapp();
                NotificationHelper NH3 = new NotificationHelper(this);
                NH3.createNotification3("Punch Service Two Was Initiated At" ,""+currentTime, "10003" , "name3", 3);
            }

        }
        else
        {
            NotificationHelper NH = new NotificationHelper(this);
            NH.createNotification(days[day-1]+" - Not Week Day" ,"" , "10001" , "name1", 1);
        }
    }

    private void startapp() {
        Intent i = getPackageManager().getLaunchIntentForPackage("com.example.autoclick");
        startActivity(i);
    }


}