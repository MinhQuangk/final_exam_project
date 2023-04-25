package com.example.student_managament;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.Toast;

import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Other.Database;

public class AddSubject extends Fragment {
    private Button btnBack;
    private Button btnAdd ;
    private EditText edtIdSjb,edtNameSjb,edtCreditSjb,edtSemesterSjb,edtDepartmentSjb;

    SubjectManage subjectManager;
    Database database ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_add_subject, container, false);
        edtIdSjb=root.findViewById(R.id.AddIDSubject);
        edtNameSjb=root.findViewById(R.id.AddNameSubject);
        edtCreditSjb=root.findViewById(R.id.AddCredit);
        edtSemesterSjb=root.findViewById(R.id.AddSemester);
        edtDepartmentSjb=root.findViewById(R.id.AddDepartment);

        database = new Database(getActivity());
        btnBack = root.findViewById(R.id.buttonBackAdd);
        btnAdd  = (Button) root.findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent.goToFragment(getFragmentManager(),R.id.framelayout, subjectManager);
            }
        });


        return root;
    }
    private void DialogAdd() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogaddstudent);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAddStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoAddStudent);

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
                    gender="Nữ";
                }
                if(name.equals("")||phone.equals("")||email.equals("")||address.equals("")||gender.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Student student = createStudentInformation();
                    database.addStudent(student);
                    Toast.makeText(getActivity(),"thêm thông tin thành công",Toast.LENGTH_SHORT);
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
    private Student createStudentInformation(){
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