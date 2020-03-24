package com.example.romasevagleb;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.motion.widget.MotionScene;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
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
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class CollorWan extends AppCompatActivity {


    ImageView cap1, yelowcap1, redcap, bluecap, yelowcap, bluecap1, redcap1;
    private MotionLayout motion_container;


    CountDownTimer count;
    CountDownTimer count1;
    CountDownTimer count2;



    int h= 0;

    private boolean mIsBound = false;
    private MyService mServ;
    private SoundPool mSoundPool;
    ServiceConnection Scon;




    private int mRedSound, mBlueSound, mYelowSound, mCap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collor_wan);



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

        mRedSound = mSoundPool.load(this, R.raw.red, 1);
        mBlueSound = mSoundPool.load(this, R.raw.blue, 1);
        mYelowSound = mSoundPool.load(this, R.raw.yelow, 1);
        mCap = mSoundPool.load(this, R.raw.cap, 1);
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {

                mSoundPool.play(mCap, 1, 1, 0, 0, 1);

            }
        });


        cap1 = findViewById(R.id.cap1);


        bluecap = findViewById(R.id.bluecap);


        bluecap1 = findViewById(R.id.bluecap1);


        redcap = findViewById(R.id.redcap);


        redcap1 = findViewById(R.id.redcap1);


        yelowcap1 = findViewById(R.id.yelowcap1);

        yelowcap = findViewById(R.id.yelowcap);


        motion_container = findViewById(R.id.motionLayout);


        motion_container.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {


                redcap1.setEnabled(false);
                redcap.setEnabled(false);
                yelowcap1.setEnabled(false);
                yelowcap.setEnabled(false);
                bluecap.setEnabled(false);
                bluecap1.setEnabled(false);


            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {


            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {


                if (h >= 6) {



                    count = new CountDownTimer(2010, 10) {

                        @Override
                        public void onTick(long millisUntilFinished) {


                        }

                        @Override
                        public void onFinish() {

                            motion_container.setTransition(R.id.capa, R.id.capa_end);
                            motion_container.transitionToEnd();
                            motion_container.setBackground(getDrawable(R.color.redCollor));
                            mSoundPool.play(mRedSound, 1, 1, 0, 0, 1);



                        }
                    }.start();



                    count1 = new CountDownTimer(4010, 10) {

                        @Override
                        public void onTick(long millisUntilFinished) {


                        }

                        @Override
                        public void onFinish() {

                            motion_container.setBackground(getDrawable(R.color.blueCollor));
                            mSoundPool.play(mBlueSound, 1, 1, 0, 0, 1);



                        }
                    }.start();

                    count2 = new CountDownTimer(6010, 10) {

                        @Override
                        public void onTick(long millisUntilFinished) {


                        }

                        @Override
                        public void onFinish() {

                            motion_container.setBackground(getDrawable(R.color.yelowCollor));
                            mSoundPool.play(mYelowSound, 1, 1, 0, 0, 1);






                        }
                    }.start();

                    count2 = new CountDownTimer(8010, 10) {

                        @Override
                        public void onTick(long millisUntilFinished) {


                        }

                        @Override
                        public void onFinish() {
                            onBackPressed();


                        }
                    }.start();





                }













                redcap.setEnabled(true);
                redcap1.setEnabled(true);
                yelowcap1.setEnabled(true);
                yelowcap.setEnabled(true);
                bluecap.setEnabled(true);
                bluecap1.setEnabled(true);
            }


            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {


            }

        });


    }




    public void cap(View view) {


        switch (view.getId()) {

            case R.id.yelowcap:
                mSoundPool.play(mYelowSound, 1, 1, 0, 0, 1);
                motion_container.setTransition(R.id.start_yelowcap, R.id.end_yelowcap);
                motion_container.transitionToEnd();
                h++;


                break;
            case R.id.bluecap1:
                mSoundPool.play(mBlueSound, 1, 1, 0, 0, 1);
                motion_container.setTransition(R.id.start_bluecap1, R.id.end_bluecap1);
                motion_container.transitionToEnd();
                h++;


                break;
            case R.id.bluecap:
                mSoundPool.play(mBlueSound, 1, 1, 0, 0, 1);
                motion_container.setTransition(R.id.start_bluecap, R.id.end_bluecap);
                motion_container.transitionToEnd();
                h++;


                break;
            case R.id.yelowcap1:
                mSoundPool.play(mYelowSound, 1, 1, 0, 0, 1);
                motion_container.setTransition(R.id.start_yelowcap1, R.id.end_yelowcap1);
                motion_container.transitionToEnd();
                h++;


                break;
            case R.id.redcap1:
                mSoundPool.play(mRedSound, 1, 1, 0, 0, 1);

                motion_container.setTransition(R.id.start_redcap1, R.id.end_redcap1);
                motion_container.transitionToEnd();
                h++;


                break;
            case R.id.redcap:
                mSoundPool.play(mRedSound, 1, 1, 0, 0, 1);

                motion_container.setTransition(R.id.start, R.id.end);
                motion_container.transitionToEnd();
                h++;


                break;

        }
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
        if (mServ != null) {
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

            if (mServ != null) {
                mServ.resumeMusic();
            }
        }else{

            stopService(new Intent (this,MyService.class));

            doUnbindService();

        }



        if (mSoundPool != null) {
            mSoundPool.autoResume();
        }


        super.onResume();
    }


    @Override
    protected void onDestroy() {


        doUnbindService();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

            super.onBackPressed();

        }



    }




























