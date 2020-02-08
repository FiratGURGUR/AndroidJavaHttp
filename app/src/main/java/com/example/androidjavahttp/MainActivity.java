package com.example.androidjavahttp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidjavahttp.APIGeneral.APIClass;
import com.example.androidjavahttp.Joke.JokeModel;
import com.example.androidjavahttp.Joke.flagsModel;

public class MainActivity extends AppCompatActivity {
    APIClass apiClass;
    TextView jokeText;
    Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiClass = new APIClass();
        jokeText = findViewById(R.id.jokeTv);
        next = findViewById(R.id.button);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               new Thread(new Runnable() {
                   @Override
                   public void run() {
                       makeJoke();
                   }
               }).start();

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                makeJoke();
            }
        }).start();
    }

    public void makeJoke(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                apiClass.getJoke(hnd);
            }
        }).start();
    }


    Handler hnd = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 92:
                    String hata = (String)msg.obj;
                    break;
                case 91:
                    JokeModel model = (JokeModel)msg.obj;
                    jokeText.setText(model.getJoke());
                    break;
            }
        }
    };

}
