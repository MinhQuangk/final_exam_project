package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;


import com.example.student_managament.Fragment.ClassesManager;
import com.example.student_managament.Fragment.HomeActivity;
import com.example.student_managament.Fragment.NoticeManager;
import com.example.student_managament.Fragment.ScoreManager;
import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Fragment.TeacherManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity  {

    Toolbar toolbar;
    BottomNavigationView bottomNavigationView;
    HomeActivity home;
    StudentManager student;
    SubjectManage subject;
    TeacherManager teacher;
    NoticeManager notice ;
    ClassesManager Class ;
    ScoreManager Score ;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        home = new HomeActivity();

        student = new StudentManager();
        subject = new SubjectManage();
        teacher = new TeacherManager();
        Class = new ClassesManager();
        notice =new NoticeManager();
        Score = new ScoreManager();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById((R.id.toolbar));
        nav = findViewById(R.id.navigationView);
        drawerLayout = findViewById(R.id.drawer);
        bottomNavigationView = findViewById(R.id.bootambar);
        toolbar.setTitle("Student Management");
        setFragment(home);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeP) {
                    setFragment(home);
                } else if (item.getItemId() == R.id.teacher) {
                    setFragment(teacher);
                } else if (item.getItemId() == R.id.student) {
                    setFragment(student);
                } else if (item.getItemId() == R.id.subject) {
                    setFragment(subject);
                } else if (item.getItemId() == R.id.score) {
                    setFragment(Score);
                }else {
                    return false;
                }
                return true;
            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeP) {
                    setFragment(home);
                } else if (item.getItemId() == R.id.teacher) {
                    setFragment(teacher);
                } else if (item.getItemId() == R.id.student) {
                    setFragment(student);
                } else if (item.getItemId() == R.id.subject) {
                    setFragment(subject);
                } else if (item.getItemId() == R.id.score) {
                    setFragment(Score);
                }else {
                    return false;
                }
                return false;
            }
        });
    }

    public void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }

}