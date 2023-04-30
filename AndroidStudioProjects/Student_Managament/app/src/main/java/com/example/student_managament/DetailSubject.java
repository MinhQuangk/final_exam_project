package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Other.Database;

public class DetailSubject extends Fragment {
    private TextView IdSj,NameSj,Credit,Semester,Department;
    private SubjectManage subjectManager;
    private Database database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_detail_subject, container, false);
        IdSj = root.findViewById(R.id.tvIdSubject);
        NameSj=root.findViewById(R.id.tvNameSubject);
        Credit = root.findViewById(R.id.tvCredit);
        Semester= root.findViewById(R.id.tvsemester);
        Department = root.findViewById(R.id.tvDepartment);
        subjectManager = new SubjectManage();

        Bundle bundle = getArguments();

        String id = bundle.getString("id");
        String name=bundle.getString("name");
        int creditI = bundle.getInt("credit");
        String credit = String.valueOf(creditI);
        String semester = bundle.getString("semester");
        String department = bundle.getString("department");

        IdSj.setText(id);
        NameSj.setText(name);
        Credit.setText(credit);
        Semester.setText(semester);
        Department.setText(department);


        database = new Database(getActivity());
        return root;
    }
}