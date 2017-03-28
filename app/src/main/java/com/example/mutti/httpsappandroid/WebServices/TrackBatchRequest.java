package com.example.mutti.httpsappandroid.WebServices;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.mutti.httpsappandroid.Constants;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mutti on 18/08/16.
 */
public class TrackBatchRequest extends StringRequest {

    Activity activity;
    String json;

    public TrackBatchRequest(int method, final Activity activity, String url, String json, Response.Listener<String> listener) {
        super(method, url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("WS", volleyError.toString());
                Toast.makeText(activity, volleyError.toString(), Toast.LENGTH_LONG ).show();
            }
        });
        setRetryPolicy(new DefaultRetryPolicy(10000, 10, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        this.activity = activity;
        this.json = json;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        return encodeParameters(getParamsEncoding());
    }

    private byte[] encodeParameters(String paramsEncoding) {
        try {
            if(json!=null) {
                return json.getBytes(paramsEncoding);
            }
            else return  null;
        } catch (UnsupportedEncodingException uee) {
            throw new RuntimeException("Encoding not supported: " + paramsEncoding, uee);
        }
    }

    @Override
    public String getBodyContentType() {
        return "application/json";
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
//        SharedPreferences settings = activity.getSharedPreferences(Constants.kSP_MY_PREFS_NAME, 0);
//        String token = settings.getString(Constants.kSP_Token, null);
        Map<String, String> headers = new HashMap<>();
//        headers.put(Constants.My_Token, Constants.bearer + token);
        headers.put(Constants.My_Token, Constants.bearer + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfaWQiOiI1N2JiOGE5YzE2NTAxODdkMDViZjE2MGEiLCJlbWFpbCI6InRlc3RlMUBnbWFpbC5jb20iLCJ1c2VyTmFtZSI6InRlc3RlMSIsInBhc3N3b3JkIjoiJDJhJDA0JDAxcEIyWDRJUFBpUDVhWGM5REJFMk90eEdnNnNCd0RIQTd5N0tXazVnb0hQQTBBS1ZTN3RXIiwiX192IjowLCJpYXQiOjE0NzE5MDg1NDR9.pYajr7dxDXPHLP4Lcqzsh0Uu_rbQy2amRo8VjaC_LJU");
        return headers;
    }

}

