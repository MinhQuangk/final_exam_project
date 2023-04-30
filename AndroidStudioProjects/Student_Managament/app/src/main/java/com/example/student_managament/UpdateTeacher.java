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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.student_managament.Fragment.StudentManager;
import com.example.student_managament.Fragment.TeacherManager;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Teacher;
import com.example.student_managament.Other.Database;

public class UpdateTeacher extends Fragment {
    private Button btnBack;
    private Button btnAdd ;
    private EditText edtNameTc;
    private EditText edtAddressTc;
    private EditText edtAcademic ;
    private EditText edtEmailTc ;
    private EditText edtBirthdayTc;

    TeacherManager teacherManager;
    Database database ;

    RadioGroup radioGroup ;
    RadioButton radioButtonMale ;
    RadioButton radioButtonFamele;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_update_teacher, container, false);
        edtNameTc = root.findViewById(R.id.UpdateName);
        edtAcademic = root.findViewById(R.id.UpdateAcademic);
        edtEmailTc = root.findViewById(R.id.UpdateEmail);
        edtAddressTc = root.findViewById(R.id.UpdateAdress);
        edtBirthdayTc = root.findViewById(R.id.UpdateBirthday);

        btnBack = root.findViewById(R.id.buttonBack);
        btnAdd = (Button) root.findViewById(R.id.buttonUpdate);
        radioGroup = root.findViewById(R.id.radioGroup);
        radioButtonFamele = root.findViewById(R.id.male);
        radioButtonMale = root.findViewById(R.id.female);
        database = new Database(getActivity());

        teacherManager = new TeacherManager();

        Bundle bundle = getArguments();

        int id = bundle.getInt("id");
        String name = bundle.getString("name");
        String gender = bundle.getString("gender");
        String academic = bundle.getString("academic");
        String email = bundle.getString("email");
        String address = bundle.getString("address");
        String birthday = bundle.getString("birthday");
        edtNameTc.setText(name);
        edtAcademic.setText(academic);
        edtEmailTc.setText(email);
        edtAddressTc.setText(address);
        edtBirthdayTc.setText(birthday);
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
                AddStudent.goToFragment(getFragmentManager(),R.id.framelayout, teacherManager);
            }
        });
        return root;
        }
    private void DialogUpdate(int id ) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_update_infor);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdateStudent);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateStudent);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtNameTc.getText().toString().trim();
                String academic = edtAcademic.getText().toString().trim();
                String email = edtAddressTc.getText().toString().trim();
                String address = edtAddressTc.getText().toString().trim();
                String birthday = edtBirthdayTc.getText().toString().trim();
                String gender = "";
                if(radioButtonMale.isChecked()){
                    gender="Nam";
                }else if(radioButtonFamele.isChecked()){
                    gender="Nu";
                }
                if(name.equals("")||academic.equals("")||email.equals("")||address.equals("")||gender.equals("")||birthday.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Teacher teacher = updateTeacherInformation();
                    database.updateTeacher(teacher,id);
                    Toast.makeText(getActivity(),"cập nhật thông tin thành công",Toast.LENGTH_SHORT);
                    TeacherManager.goToFragment(getFragmentManager(),R.id.framelayout, teacherManager);
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
    private Teacher updateTeacherInformation(){
        String name = edtNameTc.getText().toString().trim();
        String academic = edtAcademic.getText().toString().trim();
        String email = edtEmailTc.getText().toString().trim();
        String address = edtAddressTc.getText().toString().trim();
        String birthday = edtBirthdayTc.getText().toString().trim();
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