package com.example.javaprojekti9;

import android.content.Context;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

    public void saveUser(Context context) {
        // try write user to file
        try {
            // create object output stream name users.data
            ObjectOutputStream userWriter = new ObjectOutputStream(context.openFileOutput("users.data", Context.MODE_PRIVATE));
            // Write users-list there
            userWriter.writeObject(users);
            // close file
            userWriter.close();
            Toast.makeText(context, "Käyttäjä tallennettu", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // If writing to file not success
            Toast.makeText(context, "Käyttäjän tallentaminen ei onnistunut" + e, Toast.LENGTH_LONG).show();
        }
    }

    public void loadUsers(Context context) {
        // try write user to file
        try {
            // create object input stream name users.data
            ObjectInputStream userReader = new ObjectInputStream(context.openFileInput("users.data"));
            // Read file to Arraylist name users
            users = (ArrayList<User>) userReader.readObject();
            // close file
            userReader.close();
            // Notification to view
            /*/if(users.isEmpty()) {
                Toast.makeText(context, "Tiedostossa ei ollut yhtään käyttäjää", Toast.LENGTH_LONG).show();
            } else
            Toast.makeText(context, "Käyttäjät ladattu", Toast.LENGTH_LONG).show();/*/
        } catch (FileNotFoundException e) { // If file not found
            Toast.makeText(context, "Käyttäjien lataaminen ei onnistunut!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (IOException e) { // IO problem
            Toast.makeText(context, "Käyttäjien lataaminen ei onnistunut!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch (ClassNotFoundException e) { // Class problem
            Toast.makeText(context, "Käyttäjien lataaminen ei onnistunut!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

    }

    public void removeUser(int id) {

    }
}
