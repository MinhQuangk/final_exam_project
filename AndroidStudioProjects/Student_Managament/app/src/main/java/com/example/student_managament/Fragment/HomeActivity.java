package com.example.student_managament.Fragment;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.student_managament.Fragment.ClassManager;
import com.example.student_managament.Fragment.NoticeManager;
import com.example.student_managament.Fragment.ScoreManager;
import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Fragment.TeacherManager;
import com.example.student_managament.Model.Score;
import com.example.student_managament.R;

public class HomeActivity extends Fragment implements View.OnClickListener {
    CardView StudentManage;
    CardView ClassManage;
    CardView SubjectManage;
    CardView NoticeManage;
    CardView MarkManage;

    CardView TeacherManage;
    StudentManager student;
    TeacherManager teacher ;
    SubjectManage subject;
    NoticeManager notice ;
    ScoreManager score ;
    ClassManager Class ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_home, container, false);
        student = new StudentManager();
        subject = new SubjectManage();
        teacher = new TeacherManager();
        notice = new NoticeManager();
        score = new ScoreManager();
        Class = new ClassManager();

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

        TeacherManage = root.findViewById(R.id.Teacher);
        TeacherManage.setOnClickListener(this);

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
                goToFragment(getFragmentManager(),R.id.framelayout,Class);
                break;
            case R.id.Subject:
                goToFragment(getFragmentManager(),R.id.framelayout,subject);
                break;
            case R.id.Notice:
                goToFragment(getFragmentManager(),R.id.framelayout,notice);
                break;
            case R.id.Score:
                goToFragment(getFragmentManager(),R.id.framelayout,score);
                break;
            case R.id.Teacher:
                goToFragment(getFragmentManager(),R.id.framelayout,teacher);
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