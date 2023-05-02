package com.example.student_managament.Fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.student_managament.Adapter.ScoreAdapter;
import com.example.student_managament.Adapter.StudentAdapter;
import com.example.student_managament.AddScore;
import com.example.student_managament.AddStudent;
import com.example.student_managament.DetailStudent;
import com.example.student_managament.Model.Score;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.Database;
import com.example.student_managament.R;
import com.example.student_managament.UpdateScore;
import com.example.student_managament.UpdateStudent;

import java.util.ArrayList;

public class ScoreManager extends Fragment {

    private RecyclerView rcvScoreList;
    ScoreManager scoreManager;
    private ScoreAdapter scoreAdapter;
    private Database database;
    // fragment
    private AddScore addStudent;
    private UpdateScore updateScore;

    private ArrayList<Score> alScore;
    private ArrayList<Student> alStudent;
    private ArrayList<Subject> alSubject;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        addStudent = new AddScore();
        updateScore = new UpdateScore();
        View root = inflater.inflate(R.layout.activity_score, container, false);
        rcvScoreList = root.findViewById(R.id.rcvListScore);
        database = new Database(getActivity());
        alScore = new ArrayList<>();
        alScore.clear();
        alStudent = new ArrayList<>();
        alStudent.clear();
        alSubject = new ArrayList<>();
        alSubject.clear();

        Cursor cursor = database.getDataScoreOfStuden();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String gender = cursor.getString(2);
            String phone = cursor.getString(3);
            String email = cursor.getString(4);
            String address = cursor.getString(5);

            alStudent.add(new Student(id, name, gender, phone, email, address));
        }
        rcvScoreList.setHasFixedSize(true);
        scoreAdapter = new ScoreAdapter(alScore,alStudent,alSubject, ScoreManager.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvScoreList.setLayoutManager(linearLayoutManager);
        // Gắn adapter vào RecyclerView
        rcvScoreList.setAdapter(scoreAdapter);
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
    public void delete(final int pos) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.diaglog_delete_infor);
        dialog.setCanceledOnTouchOutside(false);
        Button btnYes = dialog.findViewById(R.id.btnYesDelete);
        Button btnNo = dialog.findViewById(R.id.btnNoDelete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteStudent(pos);
                Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void update(final int pos) {
        Cursor cursor = database.getDataStudent();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String phone = cursor.getString(3);
                String email = cursor.getString(4);
                String address = cursor.getString(5);

                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putString("name", name);
                args.putString("gender", gender);
                args.putString("phone", phone);
                args.putString("email", email);
                args.putString("address", address);

                updateScore.setArguments(args);
                StudentManager.goToFragment(getFragmentManager(), R.id.framelayout,updateScore );
            }
        }
    }
}