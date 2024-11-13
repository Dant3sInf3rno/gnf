package iu;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private String name;
    private String phone; // Das ist die "Handynummer"

    // Getter und Setter für name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter und Setter für phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Beispiel-Login Methode
    public String login() {
        // Hier könnte die Logik für den Login stehen
        // Beispiel:
        return "geisternetzebergen.xhtml"; // Weiterleitung zu einer anderen Seite nach dem Login
    }
}




