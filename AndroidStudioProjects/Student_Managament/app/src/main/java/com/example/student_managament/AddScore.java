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

import com.example.student_managament.Fragment.ClassesManager;
import com.example.student_managament.Fragment.ScoreManager;
import com.example.student_managament.Model.Class;
import com.example.student_managament.Model.Score;
import com.example.student_managament.Other.Database;

public class AddScore extends Fragment {
    private Button btnBack;
    private Button btnAdd ;

    private EditText SubjectId,StudentID;
    RadioGroup radioGroup ;
    RadioButton radioButtonMiddle ;
    RadioButton radioButtonFinal;
    RadioButton radioButtonExercise;
    private EditText scoreList ;

    ScoreManager scoreManager;
    Database database ;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_add_score, container, false);
        SubjectId=root.findViewById(R.id.SubjectId);
        StudentID=root.findViewById(R.id.StudentID);

        radioGroup = root.findViewById(R.id.radioGroup);
        radioButtonMiddle = root.findViewById(R.id.middleTest);
        radioButtonFinal = root.findViewById(R.id.FinalTest);
        radioButtonExercise = root.findViewById(R.id.Execise);
        scoreList=root.findViewById(R.id.Score);
        scoreManager = new ScoreManager();

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
                AddScore.goToFragment(getFragmentManager(),R.id.framelayout, scoreManager);
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
                int IdStd = Integer.parseInt(StudentID.getText().toString().trim());
                String IdSbj = SubjectId.getText().toString().trim();
                double scores= Double.parseDouble(scoreList.getText().toString().trim());
                String type = "";
                if (radioButtonExercise.isChecked()){
                    type = "BT";
                } else if (radioButtonMiddle.isChecked()) {
                    type = "GK";
                } else if (radioButtonFinal.isChecked()) {
                    type = "CK";
                }
                if(IdSbj.equals("")||(SubjectId.getText()).equals("")||(scoreList.getText()).equals("")||type.equals("")){
                    Toast.makeText(getActivity(),"Vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT);
                }else{
                    Score score = createSubjectInformation();
                    database.addScore(score);
                    Toast.makeText(getActivity(),"thêm thông tin thành công",Toast.LENGTH_SHORT);
                    AddScore.goToFragment(getFragmentManager(),R.id.framelayout, scoreManager);
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

    private Score createSubjectInformation() {
        int IdStd = Integer.parseInt(StudentID.getText().toString().trim());
        String IdSbj = SubjectId.getText().toString().trim();
        double scores= Double.parseDouble(scoreList.getText().toString().trim());
        String type = "";
        if (radioButtonExercise.isChecked()){
            type = "BT";
        } else if (radioButtonMiddle.isChecked()) {
            type = "GK";
        } else if (radioButtonFinal.isChecked()) {
            type = "CK";
        }

        Score score1 = new Score(type,scores,IdStd,IdSbj);
        return score1;
    }

    public static void goToFragment(FragmentManager fragmentManager, int containerViewId, Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerViewId, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}