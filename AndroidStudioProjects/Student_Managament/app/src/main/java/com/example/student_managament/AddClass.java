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

import com.example.student_managament.Fragment.ClassesManager;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Model.Class;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.Database;

public class AddClass extends Fragment {

    private Button btnBack;
    private Button btnAdd ;

    private EditText classId,className,teacherID;
    private EditText quantityClass ;
    private EditText sessionClass ;

    ClassesManager classesManager;
    Database database ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_add_class, container, false);
        classId=root.findViewById(R.id.AddClassID);
        className=root.findViewById(R.id.AddClassName);
        quantityClass=root.findViewById(R.id.addQuantity);
        sessionClass=root.findViewById(R.id.addSession);
        teacherID=root.findViewById(R.id.addTeacherIDClass);
        classesManager = new ClassesManager();

        database = new Database(getActivity());
        btnBack = root.findViewById(R.id.buttonBack);
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
                AddClass.goToFragment(getFragmentManager(),R.id.framelayout, classesManager);
            }
        });


        return root;
    }
    private void DialogAdd() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogaddclass);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAdd);
        Button btnNo = dialog.findViewById(R.id.btnNoadd);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = classId.getText().toString().trim();
                String name = className.getText().toString().trim();
                int quantity= Integer.parseInt(quantityClass.getText().toString().trim());
                String session = sessionClass.getText().toString().trim();
                int teacherId = Integer.parseInt(teacherID.getText().toString().trim());
                if(id.equals("")||name.equals("")||session.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Class aClass = createClassInformation();
                    database.addClass(aClass);
                    Toast.makeText(getActivity(),"thêm thông tin thành công",Toast.LENGTH_SHORT);
                    AddClass.goToFragment(getFragmentManager(),R.id.framelayout, classesManager);
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

    private Class createClassInformation() {
        String id = classId.getText().toString().trim();
        String name = className.getText().toString().trim();
        int quantity= Integer.parseInt(quantityClass.getText().toString().trim());
        String session = sessionClass.getText().toString().trim();
        int teacherId = Integer.parseInt(teacherID.getText().toString().trim());
        Class aClass = new Class(id,name,quantity,session,teacherId);
        return aClass;
    }

    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}