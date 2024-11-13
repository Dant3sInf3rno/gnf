package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import java.util.List;

@Named
@RequestScoped
public class GeisternetzController {
    private Geisternetz geisternetz = new Geisternetz();

    @Inject
    private GeisternetzDAO geisternetzDAO;

    // Methode, um das aktuelle Geisternetz abzurufen
    public Geisternetz getGeisternetz() {
        return geisternetz;
    }

    public void setGeisternetz(Geisternetz geisternetz) {
        this.geisternetz = geisternetz;
    }

    // Methode zum Speichern eines Geisternetzes
    public String save() {
        geisternetzDAO.saveGeisternetz(geisternetz);
        return "meldung_erfolgreich";
    }

    // Methode, um alle Geisternetze abzurufen
    public List<Geisternetz> getGeisternetzList() {
        return geisternetzDAO.findAllGeisternetze();
    }
    
    // Methode zum Abrufen aller Geisternetz-Meldungen
    public List<Geisternetz> getAllGeisternetze() {
        return geisternetzDAO.findAllGeisternetze();
    }
}


