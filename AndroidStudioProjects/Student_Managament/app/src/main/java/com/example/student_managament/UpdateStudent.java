package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Other.Database;

public class UpdateStudent extends Fragment {
    private Button btnBack;
    private Button btnAdd ;
    private EditText edtNameStd;
    private EditText edtAddressStd;
    private EditText edtPhoneStd ;
    private EditText edtEmailStd ;

    StudentManager studentManager;
    Database database ;

    RadioGroup radioGroup ;
    RadioButton radioButtonMale ;
    RadioButton radioButtonFamele;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_update_student, container, false);
        edtNameStd= root.findViewById(R.id.UpdateName);
        edtPhoneStd= root.findViewById(R.id.updatePhoneNumber);
        edtEmailStd= root.findViewById(R.id.UpdateEmail);
        edtAddressStd= root.findViewById(R.id.UpdateAdress);
        btnBack = root.findViewById(R.id.buttonBackUpdate);
        btnAdd  = (Button) root.findViewById(R.id.buttonUpdate);
        radioGroup = root.findViewById(R.id.radioGroup);
        radioButtonFamele = root.findViewById(R.id.updatefemale);
        radioButtonMale = root.findViewById(R.id.updatemale);
        database = new Database(getActivity());

        studentManager = new StudentManager();


        Bundle bundle = getArguments();

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String gender = bundle.getString("gender");
        String phone = bundle.getString("phone");
        String email = bundle.getString("email");
        String address = bundle.getString("address");

        edtNameStd.setText(name);
        edtPhoneStd.setText(phone);
        edtEmailStd.setText(email);
        edtAddressStd.setText(address);
        if(gender.equals("Nam")){
            radioButtonMale.setChecked(true);
            radioButtonFamele.setChecked(false);
        }else {
            radioButtonMale.setChecked(false);
            radioButtonFamele.setChecked(true);
        }
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent.goToFragment(getFragmentManager(),R.id.framelayout, studentManager);
            }
        });
        return root;
    }
    private void DialogUpdate(int id ) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogupdatestudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdateStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameStd.getText().toString().trim();
                String phone = edtPhoneStd.getText().toString().trim();
                String email = edtEmailStd.getText().toString().trim();
                String address = edtAddressStd.getText().toString().trim();
                String gender = "";
                if(radioButtonMale.isChecked()){
                    gender="Nam";
                }else if(radioButtonFamele.isChecked()){
                    gender="Nu";
                }
                if(name.equals("")||phone.equals("")||email.equals("")||address.equals("")||gender.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Student student = updateStudentInformation();
                    database.updateStudent(student,id);
                    Toast.makeText(getActivity(),"cập nhật thông tin thành công",Toast.LENGTH_SHORT);
                    AddStudent.goToFragment(getFragmentManager(),R.id.framelayout, studentManager);
                    dialog.dismiss();
                }
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
    private Student updateStudentInformation(){
        String name = edtNameStd.getText().toString().trim();
        String phone = edtPhoneStd.getText().toString().trim();
        String email = edtEmailStd.getText().toString().trim();
        String address = edtAddressStd.getText().toString().trim();
        String gender = "";
        if(radioButtonMale.isChecked()){
            gender="Nam";
        }else if(radioButtonFamele.isChecked()){
            gender="Nữ";
        }
        Student student = new Student(name,gender,phone,email,address);
        return student;
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

}
