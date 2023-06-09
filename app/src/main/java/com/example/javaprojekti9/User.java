package com.example.javaprojekti9;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String degreeProgram;
    private int image;
    private String degreeLevels;
    protected static final long serialVersionUID = 24234298L;

    public User (String firstName, String lastName, String email, String degreeProgram, ArrayList<String> levels) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.degreeProgram = degreeProgram;
        degreeLevels = "Suoritetut tutkinnot: \n";
        for (String level: levels) {
            degreeLevels += "- " + level + "\n";
        }
    }

    public void setImage(int i) {
        switch (i) {
            case 0:
                this.image = R.drawable.kissa;
                break;
            case 1:
                this.image = R.drawable.koira;
                break;
            case 2:
                this.image = R.drawable.kameli;
                break;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public int getImage() {
        return image;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getLevels() {return degreeLevels;}

    public String getDegreeProgram() {
        return degreeProgram;
    }
}
