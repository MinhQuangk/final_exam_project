package com.example.student_managament.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.student_managament.Adapter.SubjectAdapter;
import com.example.student_managament.Adapter.Subject_ScoreAdapter;
import com.example.student_managament.AddSubject;
import com.example.student_managament.DetailSubject;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.Database;
import com.example.student_managament.R;
import com.example.student_managament.UpdateSubject;

import java.util.ArrayList;

public class ScoreManageListSubject extends Fragment {

    private RecyclerView rcvSubjectlist;
    private Subject_ScoreAdapter subjectAdapter;
    private Database database;
    private ScoreManager ScoreManager;

    private ArrayList<Subject> alSubject;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_score_manage_list_subject, container, false);
        rcvSubjectlist= root.findViewById(R.id.rclSubjectListScore);
        database = new Database(getActivity());
        alSubject = new ArrayList<>();
        alSubject.clear();
        ScoreManager = new ScoreManager();
        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int credit = cursor.getInt(2);
            String semester = cursor.getString(3);
            String department = cursor.getString(4);
            alSubject.add(new Subject(id, name, credit, semester, department));
        }
        rcvSubjectlist.setHasFixedSize(true);
        subjectAdapter = new Subject_ScoreAdapter(alSubject,ScoreManageListSubject.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvSubjectlist.setLayoutManager(linearLayoutManager);
        rcvSubjectlist.setAdapter(subjectAdapter);
        cursor.moveToFirst();
        cursor.close();
        return root;
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void listScore(String pos) {
        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (id.equals(pos)) {
                String name = cursor.getString(1);
                int credit = cursor.getInt(2);
                String semester = cursor.getString(3);
                String department = cursor.getString(4);

                Bundle args = new Bundle();
                args.putString("id", id);
                args.putString("name", name);
                args.putInt("credit", credit);
                args.putString("semester", semester);
                args.putString("department", department);

                ScoreManager.setArguments(args);
                ScoreManageListSubject.goToFragment(getFragmentManager(), R.id.framelayout,ScoreManager );
            }
        }
    }
}