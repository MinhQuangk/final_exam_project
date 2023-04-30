package com.example.student_managament;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailTeacher extends Fragment {
    TextView edtId,edtName,edtGender,edtAcademic,edtEmail,edtAddress,edtBirthday;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_detail_teacher, container, false);


        edtName= root.findViewById(R.id.tvNameTeacher);
        edtGender = root.findViewById(R.id.tvGender);
        edtAcademic = root.findViewById(R.id.tvAcademic);
        edtEmail = root.findViewById(R.id.tvEmail);
        edtAddress = root.findViewById(R.id.tvAddress);
        edtBirthday = root.findViewById(R.id.tvBirthday);


        //lấy dữ liệu
        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String gender = bundle.getString("gender");
        String academic = bundle.getString("academic");
        String email = bundle.getString("email");
        String address = bundle.getString("address");
        String birthday = bundle.getString("birthday");
        //gán giá trị lên text view

        edtName.setText(name);
        edtGender.setText(gender);
        edtAcademic.setText(academic);
        edtEmail.setText(email);
        edtAddress.setText(address);
        edtBirthday.setText(birthday);
        return root;
        }
    }