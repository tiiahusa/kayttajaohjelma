package com.example.javaprojekti9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.util.ArrayList;
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
    private EditText tbFirstName; // Used later on OnCreate
    private EditText tbLastName; // These have to add here, because we need them
    private EditText tbEmail; // couple different "voids"
    private String degreeProgram;
    private RadioGroup rgDegrees, rgImages;
    private ArrayList<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tbFirstName = findViewById(R.id.tbFirstName); //Linked Activity main textboxes to code
        tbLastName = findViewById(R.id.tbLastName);
        tbEmail = findViewById(R.id.tbEmail);

        storage = com.example.javaprojekti9.UserStorage.getInstance(); // Create storage if not created yet
        rgDegrees = findViewById(R.id.rgDegreePrograms); // Linked Radiobutton Group to code
        rgImages = findViewById(R.id.rgImages);

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
        // Do new user, get values to the activity main textboxes
        user = new User(tbFirstName.getText().toString(), tbLastName.getText().toString(), tbEmail.getText().toString(), degreeProgram);
        storage.addUser(user);

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

        tbFirstName.setText("");
        tbLastName.setText("");
        tbEmail.setText("");

        rgDegrees.clearCheck();
        rgImages.clearCheck();
        degreeProgram = null;

    }

    public void listUsers(View view) {

        users = storage.getUsers();
        for (User u:users
             ) {
            System.out.println(u.getDegreeProgram() + " opiskelija " + u.getFirstName() + " " + u.getLastName() + " e-mail: " + u.getEmail());
        }
    }

    public void listUsersView (View view) {

        Intent intent = new Intent(this, UserView.class);
        startActivity(intent);
    }



}