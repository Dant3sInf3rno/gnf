package iu;

public class User {

    private int id;  // Hinzugefügt: id für den Benutzer
    private String name;
    private String handynummer;

    // Getter und Setter für id, name und handynummer
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

    public String getHandynummer() {
        return handynummer;
    }

    public void setHandynummer(String handynummer) {
        this.handynummer = handynummer;
    }
}

