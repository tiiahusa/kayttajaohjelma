package com.example.javaprojekti9;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;

public class UserView extends AppCompatActivity {

    private UserStorage storage;
    private RecyclerView recyclerView;
    private ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        storage = UserStorage.getInstance();

        recyclerView = findViewById(R.id.rvUserList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        SortedMap<String, User> list = storage.getUsers();
        for(String key : list.keySet() ) {
            users.add(list.get(key));
        }
        recyclerView.setAdapter(new UserListAdapter(getApplicationContext(), users));

    }

    public void goBackToMainView (View view) {

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
