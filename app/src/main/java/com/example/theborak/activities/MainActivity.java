package com.example.theborak.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.theborak.R;
import com.example.theborak.connections.RetrofitConnectivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Toolbar toolbar = findViewById(R.id.idToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        Call<ResponseBody> call = RetrofitConnectivity
                .getRetrofitConnectivity()
                .api()
                .create();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String responseMessage = response.body().string();
                    JSONObject jsonObject = new JSONObject(responseMessage);
                    JSONArray jsonArray = jsonObject.getJSONArray("requests");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        String name = obj.getString("name");
                        if (name.equals("Get All Movies")){
                            String url = obj.getString("url");
                            String auth = obj.getString("auth");
                            JSONObject object = new JSONObject(auth);
                            String type = object.getString("type");
                            JSONArray bearerJsonArray = object.getJSONArray("bearer");
                            for (int j = 0; j < bearerJsonArray.length(); j++){
                                JSONObject bearerJSONObject = bearerJsonArray.getJSONObject(j);
                                String key = bearerJSONObject.getString("key");
                                String value = bearerJSONObject.getString("value");
                            }
                        }

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
