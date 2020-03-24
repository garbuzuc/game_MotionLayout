package com.example.romasevagleb;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;


public class SettingsActivity extends AppCompatActivity  {
    private boolean mIsBound = false;
    private MyService mServ;
    ServiceConnection Scon;





    public static final String KEY_PREF_EXAMLE_SWITCH = "switch";

    SharedPreferences sharedPref;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        Intent music = new Intent();
        music.setClass(this, MyService.class);
        startService(music);
     sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        boolean enabled = sharedPref.getBoolean(SettingsActivity.KEY_PREF_EXAMLE_SWITCH, true);
        if(enabled){

            Intent intent = new Intent();
            intent.setClass(this, MyService.class);
            startService(intent);
        }else {

            stopService(new Intent(this, MyService.class));

            doUnbindService();

        }










        Scon = new ServiceConnection() {
            public void onServiceConnected(ComponentName name, IBinder
                    binder) {
                mServ = ((MyService.ServiceBinder) binder).getService();
            }
            public void onServiceDisconnected(ComponentName name) {
                mServ = null; }
        };











        doBindService();
        SettingsFragment settingsFragment = new SettingsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, settingsFragment)
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
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
        super.onPause();
        if (mServ != null){
            mServ.pauseMusic();
        }


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



    public static class SettingsFragment extends PreferenceFragmentCompat  {
        SharedPreferences.OnSharedPreferenceChangeListener listener;


       @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

           setPreferencesFromResource(R.xml.root_preferences, rootKey);




           listener=new SharedPreferences.OnSharedPreferenceChangeListener() {
               @Override
               public void onSharedPreferenceChanged(SharedPreferences sharedPref, String key) {

                   if(key.equals(KEY_PREF_EXAMLE_SWITCH)){
                       Preference pref = findPreference("switsh");
                       assert pref != null;
                       Preference switsh = pref.getPreferenceManager().findPreference("switsh");


                       switsh.setSummary(sharedPref.getString("switch",""));
                   }




               }

           };




       }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(listener);
        }
    }

    }











