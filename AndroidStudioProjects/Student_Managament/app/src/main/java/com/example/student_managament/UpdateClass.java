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
import com.example.student_managament.Model.Class;
import com.example.student_managament.Other.Database;

public class UpdateClass extends Fragment {
    private Button btnBack;
    private Button btnUpdate ;

    private EditText classId,className,teacherID;
    private EditText quantityClass ;
    private EditText sessionClass ;

    ClassesManager classesManager;
    Database database ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_update_class, container, false);
        classId=root.findViewById(R.id.UpdateClassId);
        className=root.findViewById(R.id.UpdateClassName);
        quantityClass=root.findViewById(R.id.UpdateQuantity);
        sessionClass=root.findViewById(R.id.UpdateSession);
        teacherID=root.findViewById(R.id.UpdateTeacherIDClass);
        classesManager = new ClassesManager();

        Bundle bundle = getArguments();

        String id = bundle.getString("id");
        String name=bundle.getString("name");
        int quantity1 = bundle.getInt("quantity");
        String quantity= String.valueOf(quantity1);
        String session = bundle.getString("session");
        int teacherId1 = bundle.getInt("teacherId");
        String teacherId=String.valueOf(teacherId1);



        classId.setText(id);
        className.setText(name);
        quantityClass.setText(quantity);
        sessionClass.setText(session);
        teacherID.setText(teacherId);

        database = new Database(getActivity());
        btnBack = root.findViewById(R.id.buttonBack);
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
                UpdateClass.goToFragment(getFragmentManager(),R.id.framelayout, classesManager);
            }
        });


        return root;
    }
    private void DialogUpdate(String id) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogaddclass);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesAdd);
        Button btnNo = dialog.findViewById(R.id.btnNoAdd);

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
                    database.updateClass(aClass,id);
                    Toast.makeText(getActivity(),"cập nhật thông tin thành công",Toast.LENGTH_SHORT);
                    UpdateClass.goToFragment(getFragmentManager(),R.id.framelayout, classesManager);
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