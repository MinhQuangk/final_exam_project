package com.example.student_managament.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Fragment.ScoreManager;
import com.example.student_managament.Model.Score;
import com.example.student_managament.Model.Student;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.ItemClickListener;
import com.example.student_managament.R;

import java.util.ArrayList;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ViewHolder> {

    private ArrayList<Score> scoreArrayList;
    private ArrayList<Student>studentArrayList;
    private ArrayList<Subject>subjectArrayList;
    private ScoreManager context;

    public ScoreAdapter(ArrayList<Score> scoreArrayList, ArrayList<Student> studentArrayList, ArrayList<Subject> subjectArrayList, ScoreManager context) {
        this.scoreArrayList = scoreArrayList;
        this.studentArrayList = studentArrayList;
        this.subjectArrayList = subjectArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtStudentName.setText(studentArrayList.get(position).getFullName());
        holder.txtExerciseScore.setText(String.valueOf(scoreArrayList.get(position).getScore()));
        holder.txtMiddleScore.setText(String.valueOf(scoreArrayList.get(position).getScore()));
        holder.txtFinalScore.setText(String.valueOf(scoreArrayList.get(position).getScore()));
        holder.id=scoreArrayList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return scoreArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtStudentName,txtExerciseScore,txtMiddleScore,txtFinalScore ;
        public ImageButton btnDelete,btnUpdate,btnAdd;
        public int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtStudentName=itemView.findViewById(R.id.txtStudentName);
            txtExerciseScore= itemView.findViewById(R.id.ExeciseScore);
            txtMiddleScore=itemView.findViewById(R.id.middleTestScore);
            txtFinalScore=itemView.findViewById(R.id.FinalExamScore);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            btnAdd.setOnClickListener(this);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(this);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view==btnAdd){

            }else if (view==btnDelete){
//                context.delete(id);
            } else if (view==btnUpdate){
//                context.update(id);
            }
        }
    }
}
