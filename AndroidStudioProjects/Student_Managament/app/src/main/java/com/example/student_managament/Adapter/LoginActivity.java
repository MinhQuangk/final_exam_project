package com.example.student_managament.Adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student_managament.MainActivity;
import com.example.student_managament.Other.LgRgDatabase;
import com.example.student_managament.R;
import com.example.student_managament.Fragment.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Gán giá trị
        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewAdmin);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                LgRgDatabase db = new LgRgDatabase(getApplicationContext(), "studentmanagement", null, 1);

                if (username.length() == 0 || password.length() == 0){
                    //Thông báo người dùng nhập đầy đủ thông tin
                    Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }else {
                    if(db.login(username, password) == 1){
                        //Hiển thị thông báo đăng nhập thành công
                        Toast.makeText(getApplicationContext(), "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("share_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username", username);
                        //Dùng để lưu trữ data dưới dạng key value
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu hoặc tài khoản sai!!!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Dùng Intent để chuyển đổi từ trang login sang trang register
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
}