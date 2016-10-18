package me.rokevin.voice;

import android.app.Application;

import me.rokevin.lib.voice.VoiceUtil;

/**
 * Created by luokaiwen on 16/9/27.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        VoiceUtil.register(getApplicationContext(), getPackageName());
    }
}
