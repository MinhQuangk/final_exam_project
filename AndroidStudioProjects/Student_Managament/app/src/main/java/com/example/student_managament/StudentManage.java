package com.example.student_managament;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.student_managament.Adapter.StudentAdapter;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Other.Database;

import java.util.ArrayList;

public class StudentManage extends Fragment {

    private RecyclerView rcvStudenList;

    private StudentAdapter studentAdapter;
    private Database database ;
    private Button btnChange ;

    // fragment
    private AddStudent addStudent ;
    private DetailStudent detailStudent ;
 ;

    private ArrayList<Student> alStudent;
    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            detailStudent = new DetailStudent();
            addStudent = new AddStudent();

            View root = inflater.inflate(R.layout.activity_student_manage,container,false);
            rcvStudenList = root.findViewById(R.id.rclStudentList);
            btnChange = (Button) root.findViewById(R.id.btnChange);

             database = new Database(getActivity());

            alStudent = new ArrayList<>();
            alStudent.clear();

            Cursor cursor = database.getDataStudent();
            while (cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String phone = cursor.getString(3);
                String email = cursor.getString(4);
                String address = cursor.getString(5);

                alStudent.add(new Student(id,name,gender,phone,email,address));
            }


            rcvStudenList.setHasFixedSize(true);
            studentAdapter = new StudentAdapter(alStudent,StudentManage.this);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
            rcvStudenList.setLayoutManager(linearLayoutManager);
        // Gắn adapter vào RecyclerView
            rcvStudenList.setAdapter(studentAdapter);
            cursor.moveToFirst();
            cursor.close();


            btnChange.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    StudentManage.goToFragment(getFragmentManager(),R.id.framelayout,addStudent);
                }
            });

            return root;
        }
        public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        }
        public void information(final int pos){
         Cursor cursor = database.getDataStudent();
         while (cursor.moveToNext()){
             int id = cursor.getInt(0);
             if(id==pos){
                 String name = cursor.getString(1);
                 String gender = cursor.getString(2);
                 String phone = cursor.getString(3);
                 String email = cursor.getString(4);
                 String address = cursor.getString(5);

                 Bundle args = new Bundle();
                 args.putInt("id",id);
                 args.putString("name",name);
                 args.putString("gender",gender);
                 args.putString("phone",phone);
                 args.putString("email",email);
                 args.putString("address",address);

                 detailStudent.setArguments(args);
                StudentManage.goToFragment(getFragmentManager(),R.id.framelayout,detailStudent);
             }
         }
        }
}