package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;

@SessionScoped
@Named("loginController")
public class LoginController {

    private String name;
    private String phone;
    private boolean loggedIn = false; // Diese Variable speichert den Login-Status

    // Getter und Setter für name und phone
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getter für loggedIn, damit JSF darauf zugreifen kann
    public boolean isLoggedIn() {
        return loggedIn;
    }

    // Setter für loggedIn
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    // Login-Methoden
    public String login() {
        UserDAO userDAO = new UserDAO();
        if (userDAO.validateUser(name, phone)) {
            loggedIn = true;
            return "netzebergenmain.xhtml?faces-redirect=true";  // Redirect zur Zielseite
        } else {
            loggedIn = false;
            return null;  // Bleibe auf der aktuellen Seite bei fehlerhaftem Login
        }
    }



    // Logout Methode, um den Benutzer abzumelden
    public String logout() {
        loggedIn = false;
        return "logout";  // Navigiere zur Logout-Seite oder zur Login-Seite
    }
}











