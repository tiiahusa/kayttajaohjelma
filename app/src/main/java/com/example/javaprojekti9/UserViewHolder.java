package com.example.javaprojekti9;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder { // Create class what extends recyclerviewer

    ImageView image;
    TextView name, email, degree;


    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.imageUser); // Map codeitems to activity view items
        name = itemView.findViewById(R.id.txtName);
        email = itemView.findViewById(R.id.txtEmail);
        degree = itemView.findViewById(R.id.txtDegreeProgram);
    }
}
