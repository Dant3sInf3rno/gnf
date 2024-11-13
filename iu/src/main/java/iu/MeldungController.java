package iu;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named
@RequestScoped
public class MeldungController {
    private Geisternetz meldung = new Geisternetz();  // Neue Instanz eines Geisternetzes für die Meldung

    @Inject
    private GeisternetzDAO geisternetzDAO;

    // Getter und Setter für die Meldung
    public Geisternetz getMeldung() {
        return meldung;
    }

    public void setMeldung(Geisternetz meldung) {
        this.meldung = meldung;
    }

    // Methode zum Speichern der Meldung
    public String submitMeldung() {
        geisternetzDAO.saveGeisternetz(meldung);
        return "geisternetzebergen.xhtml";  // Weiterleitung zur Seite mit der Liste aller gemeldeten Netze
    }
}