package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.student_managament.Fragment.ClassesManager;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Other.Database;

public class DetailClass extends Fragment {
    private TextView idClass,nameClass,sessionClass,quantityClass,teacherIdClass;
    private ClassesManager classesManager;
    private Database database;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_detail_class, container, false);
        idClass = root.findViewById(R.id.tvClasId);
        nameClass=root.findViewById(R.id.tvClassName);
        sessionClass = root.findViewById(R.id.tvSession);
        quantityClass= root.findViewById(R.id.tvQuantity);
        teacherIdClass = root.findViewById(R.id.tvTeacher);

        classesManager = new ClassesManager();

        Bundle bundle = getArguments();

        String id = bundle.getString("id");
        String name=bundle.getString("name");
        int quantity1 = bundle.getInt("quantity");
        String quantity= String.valueOf(quantity1);
        String session = bundle.getString("session");
        int teacherId1 = bundle.getInt("teacherId");
        String teacherId=String.valueOf(teacherId1);

        idClass.setText(id);
        nameClass.setText(name);
        quantityClass.setText(quantity);
        sessionClass.setText(session);
        teacherIdClass.setText(teacherId);

        database = new Database(getActivity());
        return root;
    }
}