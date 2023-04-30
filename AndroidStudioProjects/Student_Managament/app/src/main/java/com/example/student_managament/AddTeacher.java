package com.example.student_managament;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Fragment.TeacherManager;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Teacher;
import com.example.student_managament.Other.Database;

public class AddTeacher extends Fragment {

    private Button btnBack;
    private Button btnAdd ;
    private EditText edtNameTc;
    private EditText edtAddressTc;
    private EditText edtAcademicTc ;
    private EditText edtBirthdayTc ;
    private EditText edtEmailTc ;

    TeacherManager teacherManager;
    Database database ;

    RadioGroup radioGroup ;
    RadioButton radioButtonMale ;
    RadioButton radioButtonFamele;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_add_teacher, container, false);
        edtNameTc= root.findViewById(R.id.AddName);

        edtAcademicTc= root.findViewById(R.id.addAccademic);

        edtBirthdayTc= root.findViewById(R.id.addBirthday);

        edtEmailTc= root.findViewById(R.id.addEmail);

        edtAddressTc= root.findViewById(R.id.addAdress);

        btnBack = root.findViewById(R.id.buttonBack);

        btnAdd  = (Button) root.findViewById(R.id.buttonAdd);

        radioGroup = root.findViewById(R.id.radioGroup);

        radioButtonFamele = root.findViewById(R.id.female);

        radioButtonMale = root.findViewById(R.id.male);

        database = new Database(getActivity());

        teacherManager = new TeacherManager();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddStudent.goToFragment(getFragmentManager(),R.id.framelayout, teacherManager);
            }
        });


        return root;
        }
    private void DialogAdd() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogaddteacher);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAdd);
        Button btnNo = dialog.findViewById(R.id.btnNoAdd);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameTc.getText().toString().trim();
                String academic = edtAcademicTc.getText().toString().trim();
                String birthday= edtBirthdayTc.getText().toString().trim();
                String email = edtEmailTc.getText().toString().trim();
                String address = edtAddressTc.getText().toString().trim();
                String gender = "";
                if(radioButtonMale.isChecked()){
                    gender="Nam";
                }else if(radioButtonFamele.isChecked()){
                    gender="Nữ";
                }
                if(name.equals("")||academic.equals("")||email.equals("")||address.equals("")||gender.equals("")||birthday.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Teacher teacher = createTeacherInformation();
                    database.addTeacher(teacher);
                    Toast.makeText(getActivity(),"thêm thông tin thành công",Toast.LENGTH_SHORT);
                    AddTeacher.goToFragment(getFragmentManager(),R.id.framelayout, teacherManager);
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
    private Teacher createTeacherInformation(){
        String name = edtNameTc.getText().toString().trim();
        String academic = edtAcademicTc.getText().toString().trim();
        String email = edtEmailTc.getText().toString().trim();
        String birthday = edtBirthdayTc.getText().toString().trim();
        String address = edtAddressTc.getText().toString().trim();
        String gender = "";
        if(radioButtonMale.isChecked()){
            gender="Nam";
        }else if(radioButtonFamele.isChecked()){
            gender="Nữ";
        }
        Teacher teacher = new Teacher(name,gender,birthday,academic,email,address);
        return teacher;
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    }