package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import  androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeActivity extends Fragment implements View.OnClickListener {
    CardView StudentManage;
    CardView ClassManage;
    CardView SubjectManage;
    CardView NoticeManage;
    CardView MarkManage;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_home, container, false);
//        StudentManage = root.findViewById(R.id.Student);
//        StudentManage.setOnClickListener(this);
//
//        ClassManage = root.findViewById(R.id.Class);
//        ClassManage.setOnClickListener(this);
//
//        SubjectManage = root.findViewById(R.id.Subject);
//        SubjectManage.setOnClickListener(this);
//
//        NoticeManage = root.findViewById(R.id.Notice);
//        NoticeManage.setOnClickListener(this);
//
//        MarkManage = root.findViewById(R.id.Score);
//        MarkManage.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment ;
        switch (view.getId()) {
            case R.id.Student:


                break;
            case R.id.Class:
//                intent = new Intent(MainActivity.this, StudentManage.class);
                break;
            case R.id.Subject:
//                intent = new Intent(MainActivity.this, StudentManage.class);
                break;
            case R.id.Notice:
//                intent = new Intent(MainActivity.this, StudentManage.class);
                break;
            case R.id.Score:
//                intent = new Intent(MainActivity.this, StudentManage.class);
                break;
            case R.id.logOut:
//                intent = new Intent(MainActivity.this, StudentManage.class);
                break;
        }
    }



}