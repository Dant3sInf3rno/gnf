package iu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/daten";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Methode zur Verbindung zur Datenbank
    private Connection getConnection() throws SQLException, ClassNotFoundException {
        // MySQL-Treiber laden
        Class.forName(DRIVER);
        // Verbindung zur Datenbank aufbauen
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Methode zum Speichern eines Benutzers
    public boolean saveUser(User user) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO benutzer (name, handynummer) VALUES (?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getHandynummer());
                statement.executeUpdate();  // Datensatz einfügen
                return true;  // Erfolg
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern des Benutzers: " + e.getMessage());
        }
        return false;  // Fehler
    }

    // Methode zur Überprüfung der Benutzeranmeldung
    public boolean validateUser(String name, String phone) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM benutzer WHERE name = ? AND handynummer = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, phone);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();  // Gibt true zurück, wenn der Benutzer existiert
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Fehler bei der Validierung des Benutzers: " + e.getMessage());
        }
        return false;
    }

    // Methode zum Abrufen eines Benutzers anhand seiner ID
    public User getUserById(int id) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM benutzer WHERE id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setHandynummer(resultSet.getString("handynummer"));
                    return user;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Fehler beim Abrufen des Benutzers: " + e.getMessage());
        }
        return null;
    }
}




