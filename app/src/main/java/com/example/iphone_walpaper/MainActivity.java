package com.example.iphone_walpaper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView WallpaperRV;
    TextView txt;
    ArrayList<WallpaperModel> wallpaperModelArrayList = new ArrayList<WallpaperModel> ();
    WallpaperRVAdepter wallpaperRVAdepter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        txt = findViewById (R.id.txt);
        WallpaperRV = findViewById (R.id.WallpaperRV);
        wallpaperRVAdepter = new WallpaperRVAdepter (MainActivity.this,wallpaperModelArrayList);
        WallpaperRV.setAdapter (wallpaperRVAdepter);

        WallpaperRV.setLayoutManager (new GridLayoutManager (MainActivity.this, 3));

        getWallpapers();

    }

    private void getWallpapers() {
        RequestQueue requestQueue = Volley.newRequestQueue (this);
        String Api = "https://www.mrproductionsuhd.com/scripts/v2/get_gallery_array_GET.php";


         JSONObject postData = new JSONObject();
    try {
        postData.put ("i", ""); // Replace with your actual data
    } catch (JSONException e) {
        e.printStackTrace();
    }


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest (Request.Method.POST, Api,null, new Response.Listener<JSONArray> () {
            @Override
            public void onResponse(JSONArray response) {

            Log.e ("TAG", "onResponce:abc" + response);
//                List<WallpaperModel> wallpaperModelArrayList = new ArrayList<> ();

                try {

                    for (int i = 0; i < response.length (); i++) {
                        JSONObject wallpaperObject = response.getJSONObject (i);
                        String wallpaperUrl = wallpaperObject.getString ("i");


                        wallpaperModelArrayList.add (new WallpaperModel (wallpaperUrl));
                    }

                    // Create and set the adapter for your RecyclerView.
                    WallpaperRVAdepter adapter = new WallpaperRVAdepter (MainActivity.this, wallpaperModelArrayList);


                } catch (JSONException e) {
                    Toast.makeText (MainActivity.this, "Error", Toast.LENGTH_SHORT).show ();
                    e.printStackTrace ();
                }
            }
        } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        error.printStackTrace ();
                        Log.e ("Error" , error.toString ());
                    }
                });

        requestQueue.add(jsonArrayRequest);



    }


}