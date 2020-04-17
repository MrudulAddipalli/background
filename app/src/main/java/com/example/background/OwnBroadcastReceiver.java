package com.example.background;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OwnBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, OwnService.class));;
    }

}

