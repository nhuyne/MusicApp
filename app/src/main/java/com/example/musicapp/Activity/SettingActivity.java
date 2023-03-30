package com.example.musicapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.musicapp.R;

public class SettingActivity extends AppCompatActivity {

    ImageButton iB_find, iB_library,iB_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        iB_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(getApplicationContext(), LibraryActivity.class);
                setContentView(R.layout.activity_library);
            }
        });

        iB_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), FindActivity.class);
//                startActivity(intent);
                setContentView(R.layout.activity_find);
            }
        });
        iB_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//                startActivity(intent);
                setContentView(R.layout.activity_home);
            }
        });
    }
    private void init(){
        iB_find = findViewById(R.id.iB_find);
        iB_library = findViewById(R.id.iB_library);
        iB_home = findViewById(R.id.iB_home);
    }
}