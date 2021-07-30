package com.example.githubsearchapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText username;
Button searchv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=(EditText)findViewById(R.id.user);
        searchv=(Button)findViewById(R.id.search);
        searchv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=username.getText().toString();
                if(TextUtils.isEmpty(name))
                {
                    Toast.makeText(MainActivity.this, "Enter Valid username", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent i=new Intent(MainActivity.this,profile.class);
                    i.putExtra("username",name);
                    startActivity(i);
                }
            }
        });
    }
}