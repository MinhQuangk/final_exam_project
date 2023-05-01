package com.example.student_managament.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.student_managament.Fragment.ClassesManager;
import com.example.student_managament.Model.Class;
import com.example.student_managament.R;

import java.util.ArrayList;

public class ClassesAdapter extends RecyclerView.Adapter<ClassesAdapter.ViewHolder> {
    private ArrayList<Class> classArrayList;
    private ClassesManager context ;

    public ClassesAdapter(ArrayList<Class> classArrayList, ClassesManager context) {
        this.classArrayList = classArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context.getContext()).inflate(R.layout.class_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtClassID.setText(String.valueOf(classArrayList.get(position).getId()));
        holder.txtClassName.setText(classArrayList.get(position).getName());
        holder.id = classArrayList.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return classArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtClassName,txtClassID;

        public ImageButton btnDelete,btnDetail,btnUpdate;
        public String id;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtClassName = itemView.findViewById(R.id.txtClassName);
            txtClassID = itemView.findViewById(R.id.txtClassID);
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
