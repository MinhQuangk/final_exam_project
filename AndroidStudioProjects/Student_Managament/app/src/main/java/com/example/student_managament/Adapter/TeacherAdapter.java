package com.example.student_managament.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Fragment.TeacherManager;
import com.example.student_managament.Model.Teacher;
import com.example.student_managament.R;

import java.util.ArrayList;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.ViewHolder> {
    private ArrayList<Teacher> teacherArrayList;
    private TeacherManager context;

    public TeacherAdapter(ArrayList<Teacher> teacherArrayList, TeacherManager context) {
        this.teacherArrayList = teacherArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public TeacherAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.teacher_item,parent,false);
        return new TeacherAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherAdapter.ViewHolder holder, int position) {
        holder.txtTeacherName.setText(teacherArrayList.get(position).getName());
        holder.txtTeacherAcademic.setText(teacherArrayList.get(position).getAcademic());
        holder.id=teacherArrayList.get(position).getId();
        }

    @Override
    public int getItemCount() {
        return teacherArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtTeacherName,txtTeacherAcademic;
        public ImageButton btnDelete,btnDetail,btnUpdate;
        public int id ;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTeacherName = itemView.findViewById(R.id.txtTeachertName);
            txtTeacherAcademic=itemView.findViewById(R.id.academic);
            btnDetail = itemView.findViewById(R.id.btnDetail);
            btnDetail.setOnClickListener(this);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(this);
            btnUpdate = itemView.findViewById(R.id.btnUpdate);
            btnUpdate.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view == btnDetail) {
                context.information(id);
            } else if (view==btnDelete){
                context.delete(id);
            } else if (view==btnUpdate){
                context.update(id);
            }
        }
    }
}
