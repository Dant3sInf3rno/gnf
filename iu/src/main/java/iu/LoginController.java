package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;

@Named
@RequestScoped
public class LoginController implements Serializable {

    private User user = new User(); // Repräsentiert die Login-Daten eines Benutzers

    @Inject
    private UserDAO userDAO; // Datenzugriffsobjekt zur Datenbankinteraktion

    // Getter und Setter für User (Login-Daten)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Methode zur Verarbeitung des Login-Submits.
     */
    public String submitLogin() {
        try {
            // Benutzer speichern (immer erlaubt, da Registrierung nicht erforderlich ist)
            boolean success = userDAO.saveUser(user);

            FacesContext context = FacesContext.getCurrentInstance();
            if (success) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Login erfolgreich!", null));
                return "netzebergenmain.xhtml?faces-redirect=true"; // Weiterleitung nach erfolgreichem Login
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Login. Bitte versuchen Sie es erneut.", null));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ein Fehler ist aufgetreten: " + e.getMessage(), null));
            e.printStackTrace();
            return null;
        }
    }
    
    public void logout() {
        user = null; // Benutzer aus der Sitzung entfernen
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession(); // Sitzung invalidieren
    }
}













