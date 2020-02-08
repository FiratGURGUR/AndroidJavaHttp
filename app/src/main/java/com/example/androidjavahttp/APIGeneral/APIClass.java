package com.example.androidjavahttp.APIGeneral;

import android.os.Handler;
import android.util.Log;

import com.example.androidjavahttp.Joke.JokeModel;
import com.example.androidjavahttp.Joke.flagsModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class APIClass {


    public void getJoke(final Handler hnd){

        new Thread(new Runnable() {
            @Override
            public void run() {
                String sonuc=HttpClass.GetHttp(UrlList.randomJoke);
                Log.i("frt", sonuc);
                if(sonuc!=null && !sonuc.isEmpty()){
                    try {
                        JSONObject jg=new JSONObject(sonuc);
                        Gson gson = new GsonBuilder().create();
                        JokeModel userIdentifier = gson.fromJson(jg.toString(), JokeModel.class);
                        flagsModel fmodel = gson.fromJson(jg.getString("flags"),flagsModel.class);
                        userIdentifier.setFmodel(fmodel);
                        hnd.obtainMessage(91,userIdentifier).sendToTarget();
                    } catch (JSONException e) {
                        hnd.obtainMessage(90,"Json Parse Hatası").sendToTarget();
                    }
                }else{
                    hnd.obtainMessage(90,"Server Hatası").sendToTarget();
                }

            }
        }).start();
    }


}
