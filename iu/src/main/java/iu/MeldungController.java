package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.io.Serializable;

@Named
@RequestScoped
public class MeldungController implements Serializable {

    private Geisternetz meldung = new Geisternetz();  // Repräsentiert die Meldedaten eines neuen Geisternetzes

    @Inject
    private GeisternetzDAO geisternetzDAO;  // Datenzugriffsobjekt zur Datenbankinteraktion

    // Getter und Setter für Meldung (Geisternetz)
    public Geisternetz getMeldung() {
        return meldung;
    }

    public void setMeldung(Geisternetz meldung) {
        this.meldung = meldung;
    }

    public String submitMeldung() {
        try {
            boolean success = geisternetzDAO.saveGeisternetz(meldung);

            FacesContext context = FacesContext.getCurrentInstance();
            if (success) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Meldung erfolgreich gespeichert!", null));
                return "geisternetzebergen.xhtml?faces-redirect=true";
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler beim Speichern der Meldung. Bitte versuchen Sie es erneut.", null));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Ein Fehler ist aufgetreten: " + e.getMessage(), null));
            e.printStackTrace();
            return null;
        }
    }
}
