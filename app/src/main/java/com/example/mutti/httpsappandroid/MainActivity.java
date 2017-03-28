package com.example.mutti.httpsappandroid;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.example.mutti.httpsappandroid.WebServices.ServerResponse;
import com.example.mutti.httpsappandroid.WebServices.WebServiceAPI;
import com.google.gson.Gson;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button buttonGetRequest;
    Button buttonPostRequest;
    Activity activity = this;
    private WebServiceAPI wsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonGetRequest = (Button) findViewById(R.id.sendGetRequest);
        buttonPostRequest = (Button) findViewById(R.id.sendPostRequest);
        wsAPI = new WebServiceAPI(activity);


        //GET REQUEST
        buttonGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.getRequest("get-batch", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {//Callback da resposta  pode ser um erro
                        Gson gson = new Gson();
                        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);//Parse do json segundo o modelo SeverResponse

                        if (serverResponse.isSuccess()) {

                            Toast.makeText(activity,serverResponse.getResponse().toString(), Toast.LENGTH_LONG ).show();

                        } else {
                            Toast.makeText(activity,serverResponse.getMessage().toString(), Toast.LENGTH_LONG ).show();
                        }
                    }
                });
            }
        });

        //POST REQUEST
        final JSONObject request = new JSONObject();
        try {
            request.put("productID", "Prateada");
        } catch (Exception e) {
            Log.d("Error JSON", "Batch manager");
        }

        buttonPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.postRequest(request.toString(),"register-batch", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {//Callback da resposta  pode ser um erro
                        Gson gson = new Gson();
                        ServerResponse serverResponse = gson.fromJson(response, ServerResponse.class);//Parse do json segundo o modelo SeverResponse

                        if (serverResponse.isSuccess()) {
                            Toast.makeText(activity,serverResponse.getResponse().toString(), Toast.LENGTH_LONG ).show();

                        } else {
                            Toast.makeText(activity,serverResponse.getMessage().toString(), Toast.LENGTH_LONG ).show();
                        }
                    }
                });
            }
        });
    }


}
