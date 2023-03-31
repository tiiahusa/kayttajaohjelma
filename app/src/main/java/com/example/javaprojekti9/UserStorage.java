package com.example.javaprojekti9;

import java.util.ArrayList;

public class UserStorage {

    private static UserStorage storage = null; // Storage class singleton setting

    private ArrayList<User> users = new ArrayList<>(); // User-list

    private UserStorage() { // Singleton storage must be private, then you can't use it in main

    }

    public static UserStorage getInstance() { // Way to do a new storage or use the old storage if it's done yet
        if(storage == null) { // If storage is null -> Do new storage
            storage = new UserStorage();
        }
        return storage; // return storage (old or new)
    }

    public ArrayList<User> getUsers () {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(int id) {

    }
}
