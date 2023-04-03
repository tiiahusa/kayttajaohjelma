package com.example.javaprojekti9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private User user; // Create new user that we could use in program later
    private com.example.javaprojekti9.UserStorage storage; // Create new storage that we could use in program later
    private EditText tbFirstName, tbLastName, tbEmail;
    private CheckBox[] cboxes; // Create list of cbs
    private Integer[] cboxeIds = {R.id.cbKandi, R.id.cbDippa, R.id.cbTohtori, R.id.cbUima}; //Create list of cb's ids
    private String degreeProgram;
    private RadioGroup rgDegrees, rgImages;
    private SortedMap<String, User> users = new TreeMap<>();
    private ArrayList<String> levels = new ArrayList<>();
    private Context context; // Introduce context

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = com.example.javaprojekti9.UserStorage.getInstance(); // Create storage if not created yet

        tbFirstName = findViewById(R.id.tbFirstName); //Linked Activity main textboxes to code
        tbLastName = findViewById(R.id.tbLastName);
        tbEmail = findViewById(R.id.tbEmail);

        rgDegrees = findViewById(R.id.rgDegreePrograms); // Linked Radiobutton Group to code
        rgImages = findViewById(R.id.rgImages);
        // Create checkbox list
        cboxes = new CheckBox[cboxeIds.length];

        context = MainActivity.this;
        // Load users from file
        UserStorage.getInstance().loadUsers(context);



    }

    public void addUser(View view) { // View view have always add here
        // Get DegreeProgram value
        switch (rgDegrees.getCheckedRadioButtonId()) {
            case R.id.rbAutomaatio:
                degreeProgram = "Automaatiotekniikka";
                break;
            case R.id.rbSahkotekniikka:
                degreeProgram = "Sähkötekniikka";
                break;
            case R.id.rbTietotekniikka:
                degreeProgram = "Tietotekniikka";
                break;
            case R.id.rbTuotantotalous:
                degreeProgram = "Tuotantotalous";
                break;
        }
        // Check checkboxes and add degreelevels on levels -list
        for(int i = 0; i < cboxes.length; i++) {
            cboxes[i] = (CheckBox) findViewById(cboxeIds[i]);
            if (cboxes[i].isChecked()) {
                levels.add(cboxes[i].getText().toString());
            }
        }

        // Do new user, get values to the activity main textboxes
        user = new User(tbFirstName.getText().toString(), tbLastName.getText().toString(), tbEmail.getText().toString(), degreeProgram, levels);

        switch (rgImages.getCheckedRadioButtonId()) {
            case R.id.rbKissa:
                user.setImage(0);
                break;
            case R.id.rbKoira:
                user.setImage(1);
                break;
            case R.id.rbKameli:
                user.setImage(2);
                break;
        }
        // Add user to userlist
        storage.addUser(user);
         // Save user to file
        UserStorage.getInstance().saveUser(context);
        // Clear changes
        clearChanges();

        System.out.println(context.getFilesDir().toString());
        Toast.makeText(context, context.getFilesDir().toString(), Toast.LENGTH_LONG).show();

    }

    public void clearChanges() {

        tbFirstName.setText("");
        tbLastName.setText("");
        tbEmail.setText("");
        levels.clear();

        rgDegrees.clearCheck();
        rgImages.clearCheck();
        degreeProgram = null;

        // Set Checkboxes to false
        for(int i = 0; i < cboxes.length; i++) {
            cboxes[i] = (CheckBox) findViewById(cboxeIds[i]);
            if (cboxes[i].isChecked()) {
                cboxes[i].setChecked(false);
            }
        }

    }

    public void listUsers(View view) {

        users = storage.getUsers();
        /*/for (User u:users
             ) {
            System.out.println(u.getDegreeProgram() + " opiskelija " + u.getFirstName() + " " + u.getLastName() + " e-mail: " + u.getEmail());
        }/*/
    }

    public void listUsersView (View view) {

        Intent intent = new Intent(this, UserView.class);
        startActivity(intent);
    }





}