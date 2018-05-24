package com.example.obed.pollosdelmonte;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/* Clase SplashScreen.
        3  *
        4  * @author Obed Alvarado
       5  * @version 18/05/2018
*/
public class SplashScreen extends AppCompatActivity {
    /**
     * on create, metodo que se ejecuta al comienzo del activity.
     * @param savedInstanceState: parametro que guarda la instancia.

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent =new Intent(SplashScreen.this,MainActivity.class);
                startActivity(intent);
                finish();


            }
        },3000);
    }
}
