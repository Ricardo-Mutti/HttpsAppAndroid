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

    Button buttonHttpsGetRequest;
    Button buttonHttpsPostRequest;
    Button buttonHttpGetRequest;
    Button buttonHttpPostRequest;
    Activity activity = this;
    String httpsUrl = Constants.kServiceHttpsURL;
    String httpUrl = Constants.kServiceHttpURL;
    private WebServiceAPI wsAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wsAPI = new WebServiceAPI(activity);

        buttonHttpsGetRequest = (Button) findViewById(R.id.sendHttpsGetRequest);
        buttonHttpsPostRequest = (Button) findViewById(R.id.sendHttpsPostRequest);

        buttonHttpGetRequest = (Button) findViewById(R.id.sendHttpGetRequest);
        buttonHttpPostRequest = (Button) findViewById(R.id.sendHttpPostRequest);



        final JSONObject request = new JSONObject();
        try {
            request.put("attribute1", "Prateada1");
            request.put("attribute2", "Prateada2");
        } catch (Exception e) {
            Log.d("Error JSON", "Post error");
        }


//*********************** HTTPS ******************************

        //GET HTTPS REQUEST
        buttonHttpsGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.getRequest(httpsUrl, new Response.Listener<String>() {
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

        //POST HTTPS REQUEST

        buttonHttpsPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.postRequest(request.toString(),httpsUrl, new Response.Listener<String>() {
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

//*********************** HTTP ******************************

        //GET HTTP REQUEST
        buttonHttpGetRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.getRequest(httpUrl, new Response.Listener<String>() {
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

        //POST HTTP REQUEST

        buttonHttpPostRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wsAPI.postRequest(request.toString(),httpUrl, new Response.Listener<String>() {
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
