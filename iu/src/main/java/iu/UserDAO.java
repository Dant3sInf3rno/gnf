package iu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/daten";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Methode zum Speichern eines neuen Benutzers
    public boolean saveUser(User user) {
        try {
            // MySQL-Treiber laden
            Class.forName(DRIVER);

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                // SQL-Query für das Einfügen eines neuen Benutzers
                String sql = "INSERT INTO benutzer (name, handynummer) VALUES (?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Parameter setzen
                    statement.setString(1, user.getName());
                    statement.setString(2, user.getPhoneNumber());
                    statement.executeUpdate(); // Datensatz einfügen
                    return true; // Erfolg
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern des Benutzers: " + e.getMessage());
        }
        return false; // Fehler
    }

    // Methode zum Abrufen aller Benutzer
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, name, handynummer FROM benutzer";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setPhoneNumber(resultSet.getString("handynummer"));

                users.add(user); // Liste füllen
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users; // Rückgabe der Liste der Benutzer
    }

    // Methode zum Abrufen eines Benutzers nach ID
    public User getUserById(int id) {
        User user = null;
        String sql = "SELECT id, name, handynummer FROM benutzer WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setId(resultSet.getInt("id"));
                    user.setName(resultSet.getString("name"));
                    user.setPhoneNumber(resultSet.getString("handynummer"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user; // Rückgabe des gefundenen Benutzers oder null
    }

    // Methode zum Löschen eines Benutzers nach ID
    public boolean deleteUserById(int id) {
        String sql = "DELETE FROM benutzer WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Gibt true zurück, wenn ein Datensatz gelöscht wurde
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Gibt false zurück, falls ein Fehler aufgetreten ist
    }

    // Methode zum Aktualisieren der Handynummer eines Benutzers
    public boolean updateUserPhoneNumber(int id, String newPhoneNumber) {
        String sql = "UPDATE benutzer SET handynummer = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newPhoneNumber);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0; // Gibt true zurück, wenn die Handynummer erfolgreich aktualisiert wurde
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Gibt false zurück, falls ein Fehler aufgetreten ist
    }
}







