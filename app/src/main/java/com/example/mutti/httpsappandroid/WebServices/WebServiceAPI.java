package com.example.mutti.httpsappandroid.WebServices;

import android.app.Activity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.example.mutti.httpsappandroid.Constants;

/**
 * Created by Mutti on 18/08/16.
 */
public class WebServiceAPI {

    Activity activity;

    public WebServiceAPI(Activity activity) {
        this.activity = activity;
    }

    public void postRequest(String json, String url, Response.Listener successListener) {

        Request request = new TrackBatchRequest(Request.Method.POST, activity, url, json, successListener);

        WebServiceSingleton.getInstance(activity).addToRequestQueue(request);

        Log.d("Request:", request.toString());
    }

    public void getRequest(String url, Response.Listener successListener) {

        Request request = new TrackBatchRequest(Request.Method.GET, activity, url, null, successListener);

        WebServiceSingleton.getInstance(activity).addToRequestQueue(request);

        Log.d("Request:", request.toString());
    }

}
