package com.example.student_managament.Fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.student_managament.Adapter.LoginActivity;
import com.example.student_managament.Other.LgRgDatabase;
import com.example.student_managament.R;

public class RegisterActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Gán giá trị
        edUsername = findViewById(R.id.editTextResUsername);
        edEmail = findViewById(R.id.editTextResEmail);
        edPassword = findViewById(R.id.editTextResPassword);
        edConfirm = findViewById(R.id.editTextResConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingAdmin);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String email = edEmail.getText().toString();
                String confirm = edConfirm.getText().toString();
                LgRgDatabase db = new LgRgDatabase(getApplicationContext(), "studentmanagement", null, 1);

                if (username.length() == 0 || password.length() == 0 || email.length() == 0 || confirm.length() == 0) {
                    //Thông báo người dùng nhập đầy đủ thông tin
                    Toast.makeText(getApplicationContext(), "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.compareTo(confirm) == 0) {
                        if (isValid(password)) {
                            db.register(username, email, password);
                            Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        } else {
                            Toast.makeText(getApplicationContext(), "Mật khẩu phải có ít nhất 8 ký tự (gồm chữ, số và ký tự đặc biệt)", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Mật khẩu không giống nhau", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 0) {
            return false;
        } else {

            //Kiểm tra password có chữ ko
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }

            //Kiểm tra password có số ko
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }

            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c < 46 || c == 64) {
                    f3 = 1;
                }
            }
            if (f1 == 1 && f2 == 1 && f3 == 1) return true;
            return false;
        }
    }
}