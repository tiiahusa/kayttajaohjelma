package com.example.javaprojekti9;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserViewHolder> {

    // this push

    private Context context;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<String> levelList = new ArrayList<>();

    public UserListAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(context).inflate(R.layout.user_view, parent, false));
    }

    @Override // Add values to labels
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        String helper = "Suoritetut tutkinnot: \n";
        holder.name.setText(users.get(position).getFirstName() + " " + users.get(position).getLastName());
        holder.email.setText(users.get(position).getEmail());
        holder.degree.setText(users.get(position).getDegreeProgram());
        holder.image.setImageResource(users.get(position).getImage());
        levelList = users.get(position).getLevels();
        for (String level: levelList) {
             helper += "- " + level + "\n";
        }
        holder.levels.setText(helper);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
