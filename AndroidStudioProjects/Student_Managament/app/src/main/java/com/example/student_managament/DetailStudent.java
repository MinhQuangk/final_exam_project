package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class DetailStudent extends Fragment {
    TextView edtId,edtName,edtGender,edtPhone,edtEmail,edtAddress;

    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_detail_student, container, false);


        edtName= root.findViewById(R.id.tvNameStudent);
        edtGender = root.findViewById(R.id.tvGenderStudent);
        edtPhone = root.findViewById(R.id.tvPhoneStudent);
        edtEmail = root.findViewById(R.id.tvEmailStudent);
        edtAddress = root.findViewById(R.id.tvAddressStudent);

        //lấy dữ liệu
        Bundle bundle = getArguments();

        String name = bundle.getString("name");
        String gender = bundle.getString("gender");
        String phone = bundle.getString("phone");
        String email = bundle.getString("email");
        String address = bundle.getString("address");

        //gán giá trị lên text view

        edtName.setText(name);
        edtGender.setText(gender);
        edtPhone.setText(phone);
        edtEmail.setText(email);
        edtAddress.setText(address);
        return root;
    }
}