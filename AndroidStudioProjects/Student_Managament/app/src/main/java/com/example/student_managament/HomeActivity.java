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

import com.example.student_managament.Model.Subject;

public class HomeActivity extends Fragment implements View.OnClickListener {
    CardView StudentManage;
    CardView ClassManage;
    CardView SubjectManage;
    CardView NoticeManage;
    CardView MarkManage;


    StudentManage student;
    SubjectActivity subject;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_home, container, false);
        student = new StudentManage();
        subject = new SubjectActivity();
        StudentManage = root.findViewById(R.id.Student);
        StudentManage.setOnClickListener(this);

        ClassManage = root.findViewById(R.id.Class);
        ClassManage.setOnClickListener(this);

        SubjectManage = root.findViewById(R.id.Subject);
        SubjectManage.setOnClickListener(this);

        NoticeManage = root.findViewById(R.id.Notice);
        NoticeManage.setOnClickListener(this);

        MarkManage = root.findViewById(R.id.Score);
        MarkManage.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View view) {
        Fragment fragment ;
        switch (view.getId()) {
            case R.id.Student:
                goToFragment(getFragmentManager(),R.id.framelayout,student);
                break;
            case R.id.Class:

                break;
            case R.id.Subject:
                goToFragment(getFragmentManager(),R.id.framelayout,subject);
                break;
            case R.id.Notice:
                break;
            case R.id.Score:
                break;
            case R.id.logOut:
                break;
        }
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


}