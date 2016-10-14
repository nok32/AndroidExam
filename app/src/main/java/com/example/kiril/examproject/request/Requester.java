package com.example.kiril.examproject.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.kiril.examproject.contracts.IFood;

import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

/**
 * Created by Kiril on 03/10/2016.
 */

public class Requester{
    private static final String SEARCH_PATH  = "search?key=0785d7277cc0ae65c20ee04624415328&q=";
    private static final String GET_FOOD_PATH = "get?key=0785d7277cc0ae65c20ee04624415328&rId=";
    private static final String KEY_RECIPES = "recipes";

    private ProgressDialog mPDialog;

    private Context mContext;
    private RequestQueue mRequestQueue;
    private String mBaseUrl ="http://food2fork.com/api/";
    private String mUrlSearchParams;
    private String mUrlProductParams;
    private IFood mFoodsGetter;

    public Requester(Context ctx, IFood foodsGetter){
        this.set_Context(ctx);
        this.mRequestQueue = Volley.newRequestQueue(this.get_Context());
        this.set_UrlSearchParams(this.mBaseUrl + SEARCH_PATH);
        this.set_UrlGetProductParams(this.mBaseUrl + GET_FOOD_PATH);
        this.mFoodsGetter = foodsGetter;
        this.mPDialog = new ProgressDialog(this.get_Context());
    }

    public Context get_Context() {
        return this.mContext;
    }

    public void set_Context(Context mContext) {
        this.mContext = mContext;
    }

    public String get_UrlSearchParams() {
        return this.mUrlSearchParams;
    }

    public void set_UrlSearchParams(String searchParams) {
        this.mUrlSearchParams = searchParams;
    }

    public String get_UrlProductParams() {
        return mUrlProductParams;
    }

    public void set_UrlGetProductParams(String getFoodParams) {
        this.mUrlProductParams = getFoodParams;
    }

    public void searchRequest(String params){
        mPDialog.setMessage("Loading...");
        mPDialog.show();

        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET,
                this.get_UrlSearchParams() + params ,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            mFoodsGetter.getFoods(response.getJSONArray(KEY_RECIPES));
                            Log.d(TAG, response.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        mPDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                mPDialog.hide();
            }
        });

        mRequestQueue.add(req);
    }

    public void getFoodByIdRequest(String id){
        mPDialog.setMessage("Loading...");
        mPDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                this.get_UrlProductParams() + id,
                null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        mPDialog.hide();
                        try {
                            mFoodsGetter.getFoodById(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
                mPDialog.hide();
            }
        });

        mRequestQueue.add(jsonObjReq);
    }
}