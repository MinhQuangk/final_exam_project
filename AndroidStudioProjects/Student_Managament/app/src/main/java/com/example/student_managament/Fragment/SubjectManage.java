package com.example.student_managament.Fragment;

import androidx.annotation.NonNull;
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

import com.example.student_managament.Adapter.SubjectAdapter;
import com.example.student_managament.AddStudent;
import com.example.student_managament.AddSubject;
import com.example.student_managament.DetailSubject;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.Database;
import com.example.student_managament.R;
import com.example.student_managament.UpdateSubject;

import java.util.ArrayList;

public class SubjectManage extends Fragment {
    private RecyclerView rcvSubjectlist;
    private SubjectAdapter subjectAdapter;
    private Database database;
    private SubjectManage subjectManage;
    private UpdateSubject updateSubject;
    private DetailSubject detailSubject;
    private AddSubject addSubject ;
    private Button btnChange;
    private ArrayList<Subject> alSubject;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_subject, container, false);
        rcvSubjectlist= root.findViewById(R.id.rclSubjectList);
        btnChange = (Button) root.findViewById(R.id.btnChange);
        addSubject = new AddSubject();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(getFragmentManager(),R.id.framelayout,addSubject);
            }
        });
        updateSubject = new UpdateSubject();
        detailSubject = new DetailSubject();

        database = new Database(getActivity());
        alSubject = new ArrayList<>();
        alSubject.clear();

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
        subjectAdapter = new SubjectAdapter(alSubject,SubjectManage.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvSubjectlist.setLayoutManager(linearLayoutManager);

        rcvSubjectlist.setAdapter(subjectAdapter);
        cursor.moveToFirst();
        cursor.close();
        return root;
    }
    public void information(final String pos) {
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

                detailSubject.setArguments(args);
                SubjectManage.goToFragment(getFragmentManager(), R.id.framelayout,detailSubject );
            }
        }
    }
    public void delete(final String pos) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.diaglog_delete_infor);
        dialog.setCanceledOnTouchOutside(false);
         subjectManage = new SubjectManage();
        Button btnYes = dialog.findViewById(R.id.btnYesDelete);
        Button btnNo = dialog.findViewById(R.id.btnNoDelete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteSubject(pos);
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
    public void update( String pos) {
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

                updateSubject.setArguments(args);
                SubjectManage.goToFragment(getFragmentManager(), R.id.framelayout, updateSubject);
            }
        }
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}