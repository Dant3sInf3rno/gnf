package iu;

import java.io.Serializable;

public class Geisternetz implements Serializable {

    private int id;  // Einzigartige ID für jedes Geisternetz
    private String description;  // Beschreibung des Geisternetzes
    private String gpsCoordinates;  // GPS-Koordinaten
    private String estimatedSize;  // Geschätzte Größe
    private String status = "Gemeldet";  // Status, standardmäßig "Gemeldet"

    // Getter und Setter für jedes Attribut
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public String getEstimatedSize() {
        return estimatedSize;
    }

    public void setEstimatedSize(String estimatedSize) {
        this.estimatedSize = estimatedSize;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

