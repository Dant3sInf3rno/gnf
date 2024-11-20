package iu;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private User user = new User(); // Repräsentiert die Login-Daten eines Benutzers

    @Inject
    private UserDAO userDAO; // Datenzugriffsobjekt zur Datenbankinteraktion

    // Getter und Setter für User
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Methode zur Verarbeitung des Login-Submits (gleichzeitig Registrierung).
     */
    public String submitLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            // Benutzer speichern oder prüfen, ob er bereits existiert
            User existingUser = userDAO.getUserByName(user.getName());
            if (existingUser == null) {
                boolean success = userDAO.saveUser(user); // Benutzer speichern
                if (success) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registrierung und Login erfolgreich!", null));
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Speichern des Benutzers.", null));
                    return null;
                }
            } else {
                // Benutzer existiert bereits; eventuell einloggen
                this.user = existingUser;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login erfolgreich! Willkommen zurück, " + user.getName() + "!", null));
            }

            // Weiterleitung nach erfolgreichem Login
            return "netzebergenmain.xhtml?faces-redirect=true";
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ein Fehler ist aufgetreten: " + e.getMessage(), null));
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Methode zum Ausloggen des Benutzers.
     */
    public void logout() {
        user = null; // Benutzer aus der Session entfernen
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // Sitzung invalidieren
    }
}













