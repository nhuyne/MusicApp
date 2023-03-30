package com.example.musicapp.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.musicapp.Model.UserData;
import com.example.musicapp.R;
import com.example.musicapp.Service.FirebaseHelper;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    Button button_submit;
    EditText editText_taikhoan;
    EditText editText_matkhau1;
    EditText editText_matkhau2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();


        FirebaseHelper.getData("NguoiDung", new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                button_submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String taikhoan = editText_taikhoan.getText().toString();
                        String matkhau1 = editText_matkhau1.getText().toString();
                        String matkhau2 = editText_matkhau2.getText().toString();
                        if(UserData.CheckExisted(dataSnapshot, taikhoan) && matkhau1.equals(matkhau2))
                        {
                            Map<String, Object> childValues = new HashMap<>();
                            childValues.put("Id", UserData.getMaxId(dataSnapshot) + 1);
                            childValues.put("TaiKhoan", taikhoan);
                            childValues.put("MatKhau", matkhau1);

                            FirebaseHelper.addData("NguoiDung", String.valueOf(UserData.getMaxId(dataSnapshot) + 1), childValues, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void init()
    {
        button_submit = findViewById(R.id.button_submit);
        editText_taikhoan = findViewById(R.id.editText_taikhoan);
        editText_matkhau1 = findViewById(R.id.editText_matkhau1);
        editText_matkhau2 = findViewById(R.id.editText_matkhau2);
    }
}