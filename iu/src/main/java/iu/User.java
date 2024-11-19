package iu;

import java.io.Serializable;

public class User implements Serializable {

    private int id; // Einzigartige ID für jeden Benutzer
    private String name; // Name des Benutzers
    private String phoneNumber; // Handynummer des Benutzers

    // Getter und Setter für jedes Attribut
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}



