package com.example.githubsearchapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class profile extends AppCompatActivity {
String user;
ImageView profile;
TextView bio,follower,following;
RecyclerView mRecyclerView;
String url="https://api.github.com/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        //Log.d("user",getIntent().getStringExtra("username"));
        user=getIntent().getStringExtra("username");
        url=url+user;
        profile=(ImageView)findViewById(R.id.image);
        bio=(TextView)findViewById(R.id.bio);
        follower=(TextView)findViewById(R.id.followers);
        following=(TextView)findViewById(R.id.following);
        mRecyclerView=(RecyclerView)findViewById(R.id.repolist);
        loaddata(url);
        loadrepository(url);}
public void loaddata(String url)
        {// Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String imageurl = response.getString("avatar_url");
                            String followersc=response.getString("followers");
                            String followingc=response.getString("following");
                            String bioc=response.getString("bio");
                            follower.setText("Followers: "+followersc);
                            following.setText("Following: "+followingc);
                            bio.setText("Bio: "+bioc);
                            Log.d("url..................",imageurl);
                            Glide.with(profile.this).load(imageurl).into(profile);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(profile.this, "Unable to fetch the data", Toast.LENGTH_SHORT).show();

                    }
                });
            queue.add(jsonObjectRequest);

        }
public void loadrepository(String url)
{
url=url+"/repos?per_page=100&sort=created";
// Instantiate the RequestQueue.
    RequestQueue queue = Volley.newRequestQueue(this);


// Request a string response from the provided URL.
    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    //gson converts jason to java object or vice versa
                    GsonBuilder gsonBuilder=new GsonBuilder();
                    Gson gson=gsonBuilder.create();
                    Repository[] repoarray=gson.fromJson(response,Repository[].class);
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(profile.this));
                    mRecyclerView.setHasFixedSize(true);
                    mRecyclerView.setAdapter(new RepositoryAdaptor(profile.this,repoarray));

                }
            }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(profile.this, "Unable to fetch data", Toast.LENGTH_SHORT).show();
        }
    });

// Add the request to the RequestQueue.
    queue.add(stringRequest);
}
    }
