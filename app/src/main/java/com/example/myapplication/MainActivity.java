package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {
    ImageView knopblue,knopyelow,knopyellowlu2,knopred,knopred1,knopblue1,settings;

   private MotionLayout motionLayout;
   int h,b,c = 0;






    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        settings = findViewById(R.id.settings);
        knopblue = findViewById(R.id.knopblue);
        knopred = findViewById(R.id.knopred);
        knopred1 = findViewById(R.id.knopred1);
        knopblue1 = findViewById(R.id.knopblue1);
        knopyellowlu2 = findViewById(R.id.knopyellowlu2);
        knopyelow = findViewById(R.id.knopyelow);


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
               if(i==R.id.kiscyelowlu){

                   knopblue.setEnabled(true);

               }
               if(i==R.id.kiscbluelu){

                   knopyelow.setEnabled(true);


               }
               if(i==R.id.kiscredlu){
                   knopyellowlu2.setEnabled(true);}
               if(i==R.id.kiscyellowlu){
                   knopred.setEnabled(true);

               }
               if(i==R.id.kiscred1lu){
                   knopblue1.setEnabled(true);
               }
               if(i==R.id.kiscbluelu1){
                   knopred1.setEnabled(true );
               }








                if(i==R.id.Greenluend){


                    knopyellowlu2.setEnabled(true);
                    knopred.setEnabled(true);}
                if(i==R.id.Greenlublueend){

                    knopyellowlu2.setEnabled(true);
                    knopred.setEnabled(true);}
                if(i==R.id.Orangeluend){

                    knopred1.setEnabled(true);
                    knopblue1.setEnabled(true);}
                if(i==R.id.Orangeluyellow1end){

                    knopred1.setEnabled(true);
                    knopblue1.setEnabled(true);

                }
                if(i==R.id.Fiolluend){

                    knopblue.setEnabled(true);
                    knopyelow.setEnabled(true);

                }
                if(i==R.id.Fiolluredend){
                    knopblue.setEnabled(true);
                    knopyelow.setEnabled(true);


                }
            }







            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int i, boolean b, float v) {



            }
        });

    }





    public void knopblue(View view) {
        switch (view.getId()){





            case R.id.knopyelow:
                h++;
                if(h==2){
                motionLayout.setTransition(R.id.staryelowGreenlu,R.id.endyeloweGreenlu);
                motionLayout.transitionToEnd();

                    h=0;


              }
                if(h==1){
                    motionLayout.setTransition(R.id.startyelow,R.id.endyelow);
                    motionLayout.transitionToEnd();





                }
                break;
            case R.id.knopblue:

                h++;
                if(h==2){
                    motionLayout.setTransition(R.id.starblueGreenlu,R.id.endblueGreenlu);
                    motionLayout.transitionToEnd();

                    h=0;


                    }
                if(h==1){

                motionLayout.setTransition(R.id.starblue,R.id.endblue);
                motionLayout.transitionToEnd();

                    }
                break;
            case R.id.knopred:

                b++;
                if(b==1){
                motionLayout.setTransition(R.id.startred,R.id.endyred);
                motionLayout.transitionToEnd();


                }
                if(b==2){
                    motionLayout.setTransition(R.id.startredOrangelu,R.id.endredOrangelu);
                    motionLayout.transitionToEnd();

                    b=0;

                }


                break;
            case R.id.knopyellowlu2:
                b++;
                if(b==1){
                motionLayout.setTransition(R.id.staryellow1,R.id.endllowcap1);
                motionLayout.transitionToEnd();

                    }
                if(b==2){
                    motionLayout.setTransition(R.id.staryellow1Orangelu,R.id.endyellow1Orangelu);
                    motionLayout.transitionToEnd();

                    b=0;

                }
                break;
            case R.id.knopred1:
                c++;
                if(c==1){
                    motionLayout.setTransition(R.id.startred1,R.id.endred1);
                    motionLayout.transitionToEnd();

                    }
                if(c==2){
                    motionLayout.setTransition(R.id.startredFiollu,R.id.endredFiollu);
                    motionLayout.transitionToEnd();

                    c=0;

                }

                break;
            case R.id.knopblue1:
                c++;
                if(c==1){
                motionLayout.setTransition(R.id.starblue1,R.id.endblue1);
                motionLayout.transitionToEnd();

                    }


            if(c==2){
                motionLayout.setTransition(R.id.starblue1Fiollu,R.id.endblue1Fiollu);
                motionLayout.transitionToEnd();

                c=0;

            }
                break;
            case R.id.settings:


                break;



    }


        }


    @Override
    protected void onPostResume() {
        super.onPostResume();

    }
    public void knop(View view) {
        if (view.getId() == R.id.settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        }



    }
}


