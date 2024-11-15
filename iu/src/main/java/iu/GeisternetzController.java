package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzController {
    private Geisternetz geisternetz = new Geisternetz();
    
    private Integer selectedGeisternetzId;  // Für die ID-Auswahl im Dropdown-Menü
    private String newStatus;  // Für die Status-Auswahl im Dropdown-Menü

    @Inject
    private GeisternetzDAO geisternetzDAO;

    // Methode, um das aktuelle Geisternetz abzurufen
    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }

    // Methode zum Speichern des Geisternetzes
    public String save() {
        geisternetzDAO.saveGeisternetz(geisternetz);
        return "geisternetzebergen.xhtml?faces-redirect=true";  // Weiterleitung nach dem Speichern
    }

    // Methode zum Abrufen aller Geisternetze
    public List<Geisternetz> getAllGeisternetze() {
        return geisternetzDAO.getAllGeisternetze();
    }

    // Methode zum Löschen eines Geisternetzes
    public String deleteGeisternetz(int id, String status) {
        if (geisternetzDAO.deleteGeisternetzByIdAndStatus(id, status)) {
            return "geisternetzebergen.xhtml?faces-redirect=true";  // Seite neu laden nach erfolgreichem Löschen
        } else {
            return null;  // Keine Weiterleitung, falls Löschen fehlschlägt
        }
    }

    // Methode zur Statusaktualisierung des ausgewählten Geisternetzes
    public String updateStatus() {
        System.out.println("Updating status for ID: " + selectedGeisternetzId + " to new status: " + newStatus);
        if (selectedGeisternetzId != null && newStatus != null && !newStatus.isEmpty()) {
            if (geisternetzDAO.updateGeisternetzStatus(selectedGeisternetzId, newStatus)) {
                return "geisternetzebergen.xhtml?faces-redirect=true";  // Seite neu laden nach Statusänderung
            }
        }
        return null;  // Keine Weiterleitung, falls Aktualisierung fehlschlägt
    }

    // Getter und Setter für selectedGeisternetzId und newStatus
    public Integer getSelectedGeisternetzId() {
        return selectedGeisternetzId;
    }

    public void setSelectedGeisternetzId(Integer selectedGeisternetzId) {
        this.selectedGeisternetzId = selectedGeisternetzId;
    }

    public String getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(String newStatus) {
        this.newStatus = newStatus;
    }
}



