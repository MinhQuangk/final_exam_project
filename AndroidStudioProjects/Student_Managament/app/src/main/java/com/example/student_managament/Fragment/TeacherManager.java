package com.example.student_managament.Fragment;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Adapter.TeacherAdapter;
import com.example.student_managament.AddTeacher;
import com.example.student_managament.DetailTeacher;
import com.example.student_managament.Model.Teacher;
import com.example.student_managament.Other.Database;
import com.example.student_managament.R;
import com.example.student_managament.UpdateTeacher;

import java.util.ArrayList;

public class TeacherManager extends Fragment {
    private RecyclerView rcvTeacherList;
    private TeacherAdapter teacherAdapter;
    private Database database;
    private Button btnChange;

    private AddTeacher addTeacher;
    private DetailTeacher detailTeacher;
    private UpdateTeacher updateTeacher;
    private ArrayList<Teacher> alTeacher;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_teacher, container, false);

        rcvTeacherList = root.findViewById(R.id.rclTeacherList);
        btnChange = root.findViewById(R.id.btnChange);
        addTeacher = new AddTeacher();
        detailTeacher = new DetailTeacher();

        updateTeacher = new UpdateTeacher();
        database = new Database(getActivity());

        alTeacher = new ArrayList<>();
        alTeacher.clear();
        Cursor cursor = database.getDatateacher();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String birthday = cursor.getString(2);
            String gender = cursor.getString(3);
            String academic = cursor.getString(4);
            String email = cursor.getString(5);
            String address = cursor.getString(6);

            alTeacher.add(new Teacher(id,name,gender,birthday,academic,email,address));
        }
        rcvTeacherList.setHasFixedSize(true);
        teacherAdapter = new TeacherAdapter(alTeacher,TeacherManager.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvTeacherList.setLayoutManager(linearLayoutManager);
        rcvTeacherList.setAdapter(teacherAdapter);
        cursor.moveToFirst();
        cursor.close();

        btnChange.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                TeacherManager.goToFragment(getFragmentManager(), R.id.framelayout, addTeacher);
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
    public void information(final int pos) {
        Cursor cursor = database.getDatateacher();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                String name = cursor.getString(1);
                String birthday = cursor.getString(2);
                String gender = cursor.getString(3);
                String academic = cursor.getString(4);
                String email = cursor.getString(5);
                String address = cursor.getString(6);

                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putString("name", name);
                args.putString("gender", gender);
                args.putString("birthday", birthday);
                args.putString("email", email);
                args.putString("academic", academic);
                args.putString("address", address);

                detailTeacher.setArguments(args);
                TeacherManager.goToFragment(getFragmentManager(), R.id.framelayout, detailTeacher);
            }
        }
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
                database.deleteTeacher(pos);
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
        Cursor cursor = database.getDatateacher();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                String name = cursor.getString(1);
                String birthday = cursor.getString(2);
                String gender = cursor.getString(3);
                String academic = cursor.getString(4);
                String email = cursor.getString(5);
                String address = cursor.getString(6);

                Bundle args = new Bundle();
                args.putInt("id", id);
                args.putString("name", name);
                args.putString("gender", gender);
                args.putString("birthday", birthday);
                args.putString("email", email);
                args.putString("academic", academic);
                args.putString("address", address);

                updateTeacher.setArguments(args);
                TeacherManager.goToFragment(getFragmentManager(), R.id.framelayout,updateTeacher );
            }
        }
    }
}
