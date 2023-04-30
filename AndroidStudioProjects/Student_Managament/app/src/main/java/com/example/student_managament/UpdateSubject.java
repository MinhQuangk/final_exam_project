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

import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.Database;

public class UpdateSubject extends Fragment {
    private EditText edtIdSjb,edtNameSjb,edtCreditSjb,edtSemesterSjb,edtDepartmentSjb;
    private Button btnBack;
    private Button btnUpdate ;
    SubjectManage subjectManager;
    Database database ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_update_subject, container, false);
        edtIdSjb=root.findViewById(R.id.UpdateID);
        edtNameSjb=root.findViewById(R.id.UpdateName);
        edtCreditSjb=root.findViewById(R.id.UpdateCredit);
        edtSemesterSjb=root.findViewById(R.id.UpdateSemester);
        edtDepartmentSjb=root.findViewById(R.id.UpdateDepartment);
        subjectManager = new SubjectManage();

        Bundle bundle = getArguments();

        String id = bundle.getString("id");
        String name=bundle.getString("name");
        int creditI = bundle.getInt("credit");
        String credit = String.valueOf(creditI);
        String semester = bundle.getString("semester");
        String department = bundle.getString("department");

        edtIdSjb.setText(id);
        edtNameSjb.setText(name);
       edtCreditSjb.setText(credit);
        edtSemesterSjb.setText(semester);
        edtDepartmentSjb.setText(department);


        database = new Database(getActivity());
        btnBack = root.findViewById(R.id.buttonBackUpdate);
        btnUpdate  = (Button) root.findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate(id);
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateSubject.goToFragment(getFragmentManager(),R.id.framelayout, subjectManager);
            }
        });
        return root;
    }
    private void DialogUpdate(String id ) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.diaglogaddsubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAddSubject);
        Button btnNo = dialog.findViewById(R.id.btnNoAddSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = edtIdSjb.getText().toString().trim();
                String name = edtNameSjb.getText().toString().trim();
                int credit = Integer.parseInt(edtCreditSjb.getText().toString().trim());
                String semester = edtSemesterSjb.getText().toString().trim();
                String department = edtDepartmentSjb.getText().toString().trim();
                if(id.equals("")||name.equals("")||semester.equals("")||department.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Subject subject = createSubjectInformation();
                    database.updateSubject(subject,id);
                    Toast.makeText(getActivity(),"cập nhật thông tin thành công",Toast.LENGTH_SHORT);
                    AddSubject.goToFragment(getFragmentManager(),R.id.framelayout, subjectManager);
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
    private Subject createSubjectInformation(){
        String id = edtIdSjb.getText().toString().trim();
        String name = edtNameSjb.getText().toString().trim();
        int credit = Integer.parseInt(edtCreditSjb.getText().toString().trim());
        String semester = edtSemesterSjb.getText().toString().trim();
        String department = edtDepartmentSjb.getText().toString().trim();
        Subject subject = new Subject(id,name,credit,semester,department);
        return subject;
    }
    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}