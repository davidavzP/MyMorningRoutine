package com.example.mymorningroutine.inputoutput;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;

import android.net.Uri;


public class PlaySound {
    private final Context context;
    private final Ringtone r;


    public PlaySound(Context context){
        this.context = context;
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        this.r = RingtoneManager.getRingtone(context, notification);


    }
    public void playSound(){
        r.play();
    }

    public void stopSound(){
        r.stop();
    }
}
