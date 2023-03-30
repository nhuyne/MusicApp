package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.musicapp.Adapter.HomeAdapter;
import com.example.musicapp.Model.Song;
import com.example.musicapp.Model.SongData;
import com.example.musicapp.R;
import com.example.musicapp.Service.FirebaseHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ImageButton iB_find, iB_library,iB_settings;
    RecyclerView recyclerView_Anh;
    RecyclerView recyclerView_VietNam;
    RecyclerView recyclerView_Khac;

    MediaPlayer mediaPlayer;
    ArrayList<Song> songList = new ArrayList<>();
    Context context = this;

    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        //imageSlider
        ArrayList<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/0b/63/ca/0b63ca3712129db1d433175ac2c872a6.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/0b/63/ca/0b63ca3712129db1d433175ac2c872a6.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/0b/63/ca/0b63ca3712129db1d433175ac2c872a6.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/0b/63/ca/0b63ca3712129db1d433175ac2c872a6.jpg", ScaleTypes.FIT));
        slideModels.add(new SlideModel("https://i.pinimg.com/564x/0b/63/ca/0b63ca3712129db1d433175ac2c872a6.jpg", ScaleTypes.FIT));

        imageSlider.setImageList(slideModels,ScaleTypes.FIT);



        mediaPlayer = new MediaPlayer();
        mediaPlayer.setVolume(1f, 1f);

        FirebaseHelper.getData("BaiHat", new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                songList = SongData.getAllSong(dataSnapshot);

                HomeAdapter adapter_Anh = new HomeAdapter(SongData.getSongByLanguage("Anh", dataSnapshot), context);
                recyclerView_Anh.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView_Anh.setAdapter(adapter_Anh);

                HomeAdapter adapter_VietNam = new HomeAdapter(SongData.getSongByLanguage("Viet", dataSnapshot), context);
                recyclerView_VietNam.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView_VietNam.setAdapter(adapter_VietNam);

                HomeAdapter adapter_Khac = new HomeAdapter(SongData.getSongByLanguage("Khac", dataSnapshot), context);
                recyclerView_Khac.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                recyclerView_Khac.setAdapter(adapter_Khac);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        iB_library.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), LibraryActivity.class);
//                startActivity(intent);
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
        iB_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
//                startActivity(intent);
                setContentView(R.layout.activity_setting);
            }
        });
    }
    private void init()
    {
        imageSlider=findViewById(R.id.imageSlider);
        iB_find = findViewById(R.id.iB_find);
        iB_library = findViewById(R.id.iB_library);
        iB_settings = findViewById(R.id.iB_settings);
        recyclerView_Anh = findViewById(R.id.recyclerView_Anh);
        recyclerView_VietNam = findViewById(R.id.recyclerView_VietNam);
        recyclerView_Khac = findViewById(R.id.recyclerView_Khac);
    }
}