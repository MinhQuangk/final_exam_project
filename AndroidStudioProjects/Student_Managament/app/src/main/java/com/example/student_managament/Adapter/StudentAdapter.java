package com.example.student_managament.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Model.Student;
import com.example.student_managament.Other.ItemClickListener;
import com.example.student_managament.R;
import com.example.student_managament.StudentManage;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
   //Dữ liệu cần hiện thị
    private ArrayList<Student>  studentArrayList ;
    private StudentManage context;


    public StudentAdapter(ArrayList<Student> studentArrayList, StudentManage context) {
        this.studentArrayList = studentArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    //tạo ra đối tượng ViewHolder trong nó chứa View hiện thị dữ liệu
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_item,parent,false);
        
        return new ViewHolder(view);
    }

    @Override
    //chuyển dữ liệu phần tử vào View Holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.TxtStudentID.setText(String.valueOf(studentArrayList.get(position).getStudentID()));
        holder.txtStudentName.setText(studentArrayList.get(position).getFullName());
        holder.id = studentArrayList.get(position).getStudentID();
    }

    @Override
    // cho bt số phần tử của dữ liệu
    public int getItemCount() {
        return studentArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtStudentName,TxtStudentID;

        public ImageButton btnDelete,btnDetail,btnUpdate;
        public ItemClickListener itemClickListener ;
        public int id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            TxtStudentID = itemView.findViewById(R.id.txtStudentID);
            txtStudentName = itemView.findViewById(R.id.txtStudentName);
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
            }else {
                this.itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }

    }
}
