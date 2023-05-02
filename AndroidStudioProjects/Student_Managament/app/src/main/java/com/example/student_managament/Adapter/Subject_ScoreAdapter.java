package com.example.student_managament.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Fragment.ScoreManageListSubject;
import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.ItemClickListener;
import com.example.student_managament.R;

import java.util.ArrayList;

public class Subject_ScoreAdapter extends RecyclerView.Adapter<Subject_ScoreAdapter.ViewHolder> {
    private ArrayList<Subject> subjectArrayList ;
    private ScoreManageListSubject context ;

    public Subject_ScoreAdapter(ArrayList<Subject> subjectArrayList, ScoreManageListSubject context) {
        this.subjectArrayList = subjectArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_score_subject,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtSubjectName.setText(subjectArrayList.get(position).getName_subject());
        holder.id=subjectArrayList.get(position).getId_subject();
    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtSubjectName ;
        public ImageButton btnDetail;
        public String id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubjectName=itemView.findViewById(R.id.SubjectNameSc);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == btnDetail) {

            }
        }
    }
}
