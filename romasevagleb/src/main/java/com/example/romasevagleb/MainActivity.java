package com.example.romasevagleb;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceManager;


import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioAttributes;

import android.media.SoundPool;

import android.os.Build;
import android.os.Bundle;

import android.os.IBinder;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    ImageView cap11, palitra,settings_icon;


    private boolean mIsBound = false;
    private MyService mServ;
    SoundPool mSoundPool;
    int mStart;
    ServiceConnection Scon;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this,R.xml.root_preferences,false);





        Intent music = new Intent();
        music.setClass(this, MyService.class);
        startService(music);
        Scon = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder
                    binder) {
                mServ = ((MyService.ServiceBinder) binder).getService();

            }

            public void onServiceDisconnected(ComponentName name) {

                mServ = null;
            }

        };
        doBindService();


        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build();
        mSoundPool = new SoundPool.Builder().setAudioAttributes(attributes).build();
        mStart = mSoundPool.load(this, R.raw.start, 1);
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

                mSoundPool.play(mStart, 1, 1, 0, 0, 1);

            }
        });


        cap11 = findViewById(R.id.cap11);
        palitra = findViewById(R.id.Palitra);
        settings_icon = findViewById(R.id.settings_icon);


    }







    void doBindService() {
        bindService(new Intent(this, MyService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService() {
        if (mIsBound) {
            unbindService(Scon);
            mIsBound = false;
        }
    }









    @Override
    protected void onPause() {
        if (mServ != null){
            mServ.pauseMusic();
        }
        if (mSoundPool != null) {
            mSoundPool.autoPause();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean enabled = sharedPref.getBoolean(SettingsActivity.KEY_PREF_EXAMLE_SWITCH,true);
        if(enabled){

            Intent music = new Intent();
            music.setClass(this, MyService.class);
            startService(music);
            }else {

            stopService(new Intent(this, MyService.class));

            doUnbindService();

        }

        if (mServ != null) {
            mServ.resumeMusic();
        }



        super.onResume();

    }










    @Override
    protected void onDestroy() {
        if (mServ != null) {
            stopService(new Intent (this,MyService.class));

        doUnbindService();
            }
        if (mSoundPool != null) {
            mSoundPool.stop(mStart);
            mSoundPool.release();
           }






        super.onDestroy();

    }






    public void transition(View view) {
        Bundle bundle = null;


        switch (view.getId()) {
            case R.id.cap11:

                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    View v = findViewById(R.id.cap11);
                    if (v != null) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, MainActivity.this.getString(R.string.cap));
                        bundle = options.toBundle();
                    }
                }
                Intent intent = new Intent(MainActivity.this, CollorWan.class);
                if (bundle == null) {
                    startActivity(intent);

                } else {
                    startActivity(intent, bundle);
                }

                break;
            case R.id.Palitra:
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                    View b = findViewById(R.id.Palitra);
                    if (b != null) {
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, view, getString(R.string.palitra));
                        bundle = options.toBundle();
                    }
                }

                Intent collor = new Intent(MainActivity.this, Collor2.class);
                if (bundle == null) {
                    startActivity(collor);
                } else {
                    startActivity(collor, bundle);
                }
                break;
            case R.id.settings_icon:

                Intent settings_icon = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(settings_icon);
                break;


        }
    }



    }






