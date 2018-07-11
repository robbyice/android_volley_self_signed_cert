package com.example.pivotal.selfsignedcert;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RequestQueueSingleton.cacheDirectory = getCacheDir();
        RequestQueueSingleton.applicationContext = getApplicationContext();

        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View view) {
        try {
            RequestQueueSingleton.getRequestQueue().add(makeRequest());
        } catch (Exception e) {
            setResultText("Missing config see RequestQueueFactory");
        }
    }

    private StringRequest makeRequest() {
        return new StringRequest(Request.Method.GET, "https://10.37.2.6:8000", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                setResultText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                setResultText(error.getMessage());
            }
        });
    }

    private void setResultText(String text) {
        ((TextView) findViewById(R.id.response_result)).setText(text);
    }


}
