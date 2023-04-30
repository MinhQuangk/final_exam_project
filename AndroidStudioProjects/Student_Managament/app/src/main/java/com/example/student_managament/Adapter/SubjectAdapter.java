package com.example.student_managament.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Fragment.SubjectManage;
import com.example.student_managament.Model.Subject;
import com.example.student_managament.Other.ItemClickListener;
import com.example.student_managament.R;

import java.util.ArrayList;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.ViewHolder> {
    private ArrayList<Subject> subjectArrayList ;
    private SubjectManage context ;

    public SubjectAdapter(ArrayList<Subject> subjectArrayList, SubjectManage context) {
        this.subjectArrayList = subjectArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.ViewHolder holder, int position) {
        holder.txtSubjectName.setText(subjectArrayList.get(position).getName_subject());
        holder.txtCredit.setText(String.valueOf(subjectArrayList.get(position).getCredit()));
        holder.txtDepartment.setText(subjectArrayList.get(position).getDepartment());
        holder.id=subjectArrayList.get(position).getId_subject();
    }

    @Override
    public int getItemCount() {
        return subjectArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtSubjectName,txtCredit,txtDepartment ;
        public ImageButton btnDelete,btnDetail,btnUpdate;
        public ItemClickListener itemClickListener ;
        public String id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSubjectName=itemView.findViewById(R.id.txtSubjectName);
            txtCredit= itemView.findViewById(R.id.txtCredit);
            txtDepartment=itemView.findViewById(R.id.txtDepartment);
            btnDetail = itemView.findViewById(R.id.btnDetailSubject);
            btnDetail.setOnClickListener(this);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnDelete.setOnClickListener(this);
            btnUpdate = itemView.findViewById(R.id.btnUpdateSubject);
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
