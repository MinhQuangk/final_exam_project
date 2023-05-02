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

import com.example.student_managament.Fragment.ScoreManager;
import com.example.student_managament.Model.Score;
import com.example.student_managament.Other.Database;

public class UpdateScore extends Fragment {
    private Button btnBack;
    private Button btnUpdate ;

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
        View root = inflater.inflate(R.layout.activity_update_score, container, false);
        SubjectId=root.findViewById(R.id.SubjectId);
        StudentID=root.findViewById(R.id.StudentID);

        radioGroup = root.findViewById(R.id.radioGroup);
        radioButtonMiddle = root.findViewById(R.id.middleTest);
        radioButtonFinal = root.findViewById(R.id.FinalTest);
        radioButtonExercise = root.findViewById(R.id.Execise);
        scoreList=root.findViewById(R.id.Score);
        scoreManager = new ScoreManager();

        Bundle bundle = getArguments();

        String idStd = String.valueOf(bundle.getInt("idStd"));
        String IdSbj = bundle.getString("IdSbj");
        String score = String.valueOf(bundle.getDouble("score"));
        String type = bundle.getString("type");
        if(type.equals("BT")){
            radioButtonExercise.setChecked(true);
            radioButtonMiddle.setChecked(false);
            radioButtonFinal.setChecked(false);
        } else if (type.equals("GK")){
            radioButtonExercise.setChecked(false);
            radioButtonMiddle.setChecked(true);
            radioButtonFinal.setChecked(false);
        } else {
            radioButtonExercise.setChecked(false);
            radioButtonMiddle.setChecked(false);
            radioButtonFinal.setChecked(true);
        }

        SubjectId.setText(idStd);
        StudentID.setText(IdSbj);
        scoreList.setText(score);


        database = new Database(getActivity());
        btnBack = root.findViewById(R.id.buttonBack);
        btnUpdate  = (Button) root.findViewById(R.id.buttonUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUpdate();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpdateScore.goToFragment(getFragmentManager(),R.id.framelayout, scoreManager);
            }
        });


        return root;
    }
    private void DialogUpdate() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_update_infor);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.btnYesUpdate);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdate);

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
                    UpdateScore.goToFragment(getFragmentManager(),R.id.framelayout, scoreManager);
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