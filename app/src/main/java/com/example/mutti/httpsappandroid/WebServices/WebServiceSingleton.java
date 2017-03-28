package com.example.mutti.httpsappandroid.WebServices;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Mutti on 18/08/16.
 */
public class WebServiceSingleton {


    private static WebServiceSingleton mInstance;
    private RequestQueue mrequestQueue;
    private static Activity activity;

    private WebServiceSingleton(Activity activity) {
        this.activity = activity;
        mrequestQueue = getRequestQueue();
    }

    public static synchronized WebServiceSingleton getInstance(Activity activity) {
        if (mInstance == null) {
            mInstance = new WebServiceSingleton(activity);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mrequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mrequestQueue = Volley.newRequestQueue(activity.getApplicationContext());
        }
        return mrequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}