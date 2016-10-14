package com.example.kiril.examproject.contracts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Kiril on 04/10/2016.
 */

public interface IFood {
    void foodClick(int position);
    void getFoods(JSONArray foods);
    void getFoodById(JSONObject foodAsJson) throws JSONException;
}
