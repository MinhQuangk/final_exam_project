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

import com.example.student_managament.Adapter.ClassesAdapter;

import com.example.student_managament.AddClass;
import com.example.student_managament.DetailClass;
import com.example.student_managament.Model.Class;
import com.example.student_managament.Other.Database;
import com.example.student_managament.R;
import com.example.student_managament.UpdateClass;

import java.util.ArrayList;

public class ClassesManager extends Fragment {
    private RecyclerView rcvClassList;
    private ClassesAdapter classesAdapter;
    private Database database;
    private ClassesManager classesManager;
    private UpdateClass updateClass;
    private DetailClass detailClass;
    private AddClass addClass ;
    private Button btnChange;
    private ArrayList<Class> alClass;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_class, container, false);
        rcvClassList= root.findViewById(R.id.rclClassList);
        btnChange = (Button) root.findViewById(R.id.btnChange);
        addClass = new AddClass();
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFragment(getFragmentManager(),R.id.framelayout,addClass);
            }
        });
        updateClass = new UpdateClass();
        detailClass = new DetailClass();

        database = new Database(getActivity());
        alClass = new ArrayList<>();
        alClass.clear();

        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            int quantity = cursor.getInt(2);
            String session = cursor.getString(3);
            int teacherId = cursor.getInt(4);


            alClass.add(new Class(id,name,quantity,session,teacherId));
        }
        rcvClassList.setHasFixedSize(true);
        classesAdapter = new ClassesAdapter(alClass,ClassesManager.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rcvClassList.setLayoutManager(linearLayoutManager);

        rcvClassList.setAdapter(classesAdapter);
        cursor.moveToFirst();
        cursor.close();
        return root;
    }
    public void information(final String pos) {
        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (id.equals(pos)) {
                String name = cursor.getString(1);
                int quantity = cursor.getInt(3);
                String session = cursor.getString(2);
                String teacherName = cursor.getString(5);

                Bundle args = new Bundle();
                args.putString("id", id);
                args.putString("name", name);
                args.putInt("quantity", quantity);
                args.putString("session", session);
                args.putString("teacherName", teacherName);

                detailClass.setArguments(args);
                ClassesManager.goToFragment(getFragmentManager(), R.id.framelayout,detailClass );
            }
        }
    }
    public void delete(final String pos) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.diaglog_delete_infor);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesDelete);
        Button btnNo = dialog.findViewById(R.id.btnNoDelete);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                database.deleteClass(pos);
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
        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            if (id.equals(pos)) {
                String name = cursor.getString(1);
                int quantity = cursor.getInt(3);
                String session = cursor.getString(2);
                int teacherId = cursor.getInt(4);

                Bundle args = new Bundle();
                args.putString("id", id);
                args.putString("name", name);
                args.putInt("quantity", quantity);
                args.putString("session", session);
                args.putInt("teacherId", teacherId);

                updateClass.setArguments(args);
                classesManager.goToFragment(getFragmentManager(), R.id.framelayout, updateClass);
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