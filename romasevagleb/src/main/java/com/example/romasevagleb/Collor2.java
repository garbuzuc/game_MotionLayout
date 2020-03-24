package com.example.romasevagleb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.preference.PreferenceManager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.view.View;
import android.widget.ImageView;

public class Collor2 extends AppCompatActivity {
    ImageView knopblue,knopyelow,knopyellowlu2,knopred,knopred1,knopblue1,greenfin,oranglufin,fiollufin;
    private MotionLayout motionLayout;
    int h,b,c= 0;

    private MyService mServ;
    CountDownTimer count;
    private boolean mIsBound = false;
    private SoundPool mSoundPool1;
    ServiceConnection Scon;

    private int mRedSound,mBlueSound,mYelowSound,mGreenSound,mOrangeSound,mFiolSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collor2);
        Intent music = new Intent();
        music.setClass(this,MyService.class);
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
        greenfin = findViewById(R.id.greenfin);


        knopblue = findViewById(R.id.knopblue);
        knopred = findViewById(R.id.knopred);
        knopred1 = findViewById(R.id.knopred1);
        knopblue1 = findViewById(R.id.knopblue1);
        knopyellowlu2 = findViewById(R.id.knopyellowlu2);
        knopyelow = findViewById(R.id.knopyelow);
        AudioAttributes attributes = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build();
        mSoundPool1 = new SoundPool.Builder().setAudioAttributes(attributes).build();
        mRedSound = mSoundPool1.load(this,R.raw.red,1);
        mBlueSound = mSoundPool1.load(this,R.raw.blue,1);
        mYelowSound = mSoundPool1.load(this,R.raw.yelow,1);
        mGreenSound = mSoundPool1.load(this,R.raw.green,1);
        mFiolSound = mSoundPool1.load(this,R.raw.fiol,1);
        mOrangeSound = mSoundPool1.load(this,R.raw.orange,1);
        motionLayout = findViewById(R.id.motionLayout1);
        motionLayout.setTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int i, int i1) {
                knopred.setEnabled(false);
                knopyellowlu2.setEnabled(false);
                knopred1.setEnabled(false);
                knopblue1.setEnabled(false);
                knopblue.setEnabled(false);
                knopyelow.setEnabled(false);
            }
            @Override
            public void onTransitionChange(MotionLayout motionLayout, int i, int i1, float v) {
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int i) {
                if (i == R.id.kiscyelowlu) {


                    knopblue.setEnabled(true);

                }
                if (i == R.id.kiscbluelu) {

                    knopyelow.setEnabled(true);


                }
                if (i == R.id.kiscredlu) {
                    knopyellowlu2.setEnabled(true);
                }
                if (i == R.id.kiscyellowlu) {
                    knopred.setEnabled(true);

                }
                if (i == R.id.kiscred1lu) {
                    knopblue1.setEnabled(true);
                }
                if (i == R.id.kiscbluelu1) {
                    knopred1.setEnabled(true);
                }
                if (i == R.id.Greenluend) {
                    mSoundPool1.play(mGreenSound, 1, 1, 0, 0, 1);
                    knopyellowlu2.setEnabled(true);
                    knopred.setEnabled(true);
                }
                if (i == R.id.Greenlublueend) {
                    mSoundPool1.play(mGreenSound, 1, 1, 0, 0, 1);

                    knopyellowlu2.setEnabled(true);
                    knopred.setEnabled(true);
                }
                if (i == R.id.Orangeluend) {
                    mSoundPool1.play(mOrangeSound, 1, 1, 0, 0, 1);

                    knopred1.setEnabled(true);
                    knopblue1.setEnabled(true);
                }
                if (i == R.id.Orangeluyellow1end) {
                    mSoundPool1.play(mOrangeSound, 1, 1, 0, 0, 1);

                    knopred1.setEnabled(true);
                    knopblue1.setEnabled(true);

                }
                if (i == R.id.Fiolluend) {
                    mSoundPool1.play(mFiolSound, 1, 1, 0, 0, 1);

                    knopblue.setEnabled(true);
                    knopyelow.setEnabled(true);

                }
                if (i == R.id.Fiolluredend) {
                    mSoundPool1.play(mFiolSound, 1, 1, 0, 0, 1);
                    knopblue.setEnabled(true);
                    knopyelow.setEnabled(true);
                }



            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {



            }
        });
    }
    public void knop(View view) {
        switch (view.getId()){





            case R.id.knopyelow:
                h++;
                if(h==2){
                    mSoundPool1.play(mYelowSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.staryelowGreenlu,R.id.endyeloweGreenlu);
                    motionLayout.transitionToEnd();

                    h=0;


                }
                if(h==1){
                    mSoundPool1.play(mYelowSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.startyelow,R.id.endyelow);
                    motionLayout.transitionToEnd();





                }
                break;
            case R.id.knopblue:

                h++;
                if(h==2){
                    mSoundPool1.play(mBlueSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.starblueGreenlu,R.id.endblueGreenlu);
                    motionLayout.transitionToEnd();

                    h=0;


                }
                if(h==1){
                    mSoundPool1.play(mBlueSound,1,1,0,0,1);

                    motionLayout.setTransition(R.id.starblue,R.id.endblue);
                    motionLayout.transitionToEnd();

                }
                break;
            case R.id.knopred:

                b++;
                if(b==1){
                    mSoundPool1.play(mRedSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.startred,R.id.endyred);
                    motionLayout.transitionToEnd();


                }
                if(b==2){
                    mSoundPool1.play(mRedSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.startredOrangelu,R.id.endredOrangelu);
                    motionLayout.transitionToEnd();

                    b=0;

                }


                break;
            case R.id.knopyellowlu2:
                b++;
                if(b==1){
                    mSoundPool1.play(mYelowSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.staryellow1,R.id.endllowcap1);
                    motionLayout.transitionToEnd();

                }
                if(b==2){
                    mSoundPool1.play(mYelowSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.staryellow1Orangelu,R.id.endyellow1Orangelu);
                    motionLayout.transitionToEnd();

                    b=0;

                }
                break;
            case R.id.knopred1:
                c++;
                if(c==1){
                    mSoundPool1.play(mRedSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.startred1,R.id.endred1);
                    motionLayout.transitionToEnd();

                }
                if(c==2){
                    mSoundPool1.play(mRedSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.startredFiollu,R.id.endredFiollu);
                    motionLayout.transitionToEnd();

                    c=0;

                }

                break;
            case R.id.knopblue1:
                c++;
                if(c==1){
                    mSoundPool1.play(mBlueSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.starblue1,R.id.endblue1);
                    motionLayout.transitionToEnd();

                }


                if(c==2){
                    mSoundPool1.play(mBlueSound,1,1,0,0,1);
                    motionLayout.setTransition(R.id.starblue1Fiollu,R.id.endblue1Fiollu);
                    motionLayout.transitionToEnd();

                    c=0;

                }
                break;
            case R.id.greenfin:

                motionLayout.setBackground(getDrawable(R.color.greenCollor));
                mSoundPool1.play(mGreenSound,1,1,0,0,1);



                count = new CountDownTimer(2010, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {



                    }

                    @Override
                    public void onFinish() {


                        motionLayout.setBackground(getDrawable(R.color.scy));





                    }
                }.start();

                break;
            case R.id.oranglufin:

                motionLayout.setBackground(getDrawable(R.color.orangeCollor));
                mSoundPool1.play(mOrangeSound,1,1,0,0,1);



                count = new CountDownTimer(2010, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {



                    }

                    @Override
                    public void onFinish() {

                        motionLayout.setBackground(getDrawable(R.color.scy));





                    }
                }.start();

                break;
            case R.id.fiollufin:

                motionLayout.setBackground(getDrawable(R.color.fiolCollor));
                mSoundPool1.play(mFiolSound,1,1,0,0,1);



                count = new CountDownTimer(2010, 10) {
                    @Override
                    public void onTick(long millisUntilFinished) {



                    }

                    @Override
                    public void onFinish() {

                        motionLayout.setBackground(getDrawable(R.color.scy));





                    }
                }.start();

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
        if (mServ != null){
            mServ.pauseMusic();
        }
        if (mSoundPool1 != null) {
            mSoundPool1.autoPause();
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




        if (mSoundPool1 != null) {
            mSoundPool1.autoResume();
        }


        super.onResume();
    }
    @Override
    protected void onDestroy() {
        doUnbindService();
        super.onDestroy();
    }













}
