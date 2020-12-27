package com.iot.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.android.Services.BaseAPIService;
import com.iot.android.Services.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    CardView updateArduino, turnOn, turnOff;
    TextView arduinoState, arduinoPosition;
    String life;
    BaseAPIService mApiService;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getAPIService();
        mContext = this;
        autoUpdate();
        arduinoState = findViewById(R.id.arduino_state);
        arduinoPosition = findViewById(R.id.arduino_position);
        updateArduino = findViewById(R.id.card_update);
        updateArduino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
        turnOn = findViewById(R.id.card_turn_on);
        turnOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                life = "on";
                setTurnArduino(life);
            }
        });
        turnOff = findViewById(R.id.card_turn_off);
        turnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                life = "off";
                setTurnArduino(life);
            }
        });
    }

    private void autoUpdate() {
        mApiService.getStateArduino().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        String state = jsonRESULTS.getString("state");
                        String position = jsonRESULTS.getString("position");
                        arduinoState.setText(state);
                        arduinoPosition.setText(position);
                        Toast.makeText(mContext, "Berhasil Update", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(mContext, "Error, hubungi developer!!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(mContext, "Error, hubungi developer!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Tidak berhasil Update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setTurnArduino(String life) {
        final String lifu = life;
        mApiService.turnStateArduino(lifu).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    Toast.makeText(mContext, "Turn "+lifu+" arduino berhasil\n Tunggu beberapa saat", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "Turn "+lifu+" arduino gagal", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "Turn "+lifu+" arduino gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void update() {
        mApiService.getStateArduino().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        String state = jsonRESULTS.getString("state");
                        String position = jsonRESULTS.getString("position");
                        arduinoState.setText(state);
                        arduinoPosition.setText(position);
                        Toast.makeText(mContext, "Berhasil Update", Toast.LENGTH_SHORT).show();
                    } catch (JSONException e){
                        e.printStackTrace();
                        Toast.makeText(mContext, "Error, hubungi developer!!", Toast.LENGTH_SHORT).show();
                    } catch (IOException e){
                        e.printStackTrace();
                        Toast.makeText(mContext, "Error, hubungi developer!!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mContext, "Tidak berhasil Update", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}