package iu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeisternetzDAO {

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/daten";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    // Methode zum Speichern eines neuen Geisternetzes
    public boolean saveGeisternetz(Geisternetz geisternetz) {
        try {
            // MySQL-Treiber laden
            Class.forName(DRIVER);
            
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                // SQL-Query für das Einfügen eines neuen Geisternetzes
                String sql = "INSERT INTO geisternetz (description, gps_coordinates, estimated_size, status) VALUES (?, ?, ?, ?)";
                try (PreparedStatement statement = connection.prepareStatement(sql)) {
                    // Parameter setzen
                    statement.setString(1, geisternetz.getDescription());
                    statement.setString(2, geisternetz.getGpsCoordinates());
                    statement.setString(3, geisternetz.getEstimatedSize());
                    statement.setString(4, geisternetz.getStatus());
                    statement.executeUpdate();  // Datensatz einfügen
                    return true;  // Erfolg
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL-Treiber nicht gefunden: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Fehler beim Speichern des Geisternetzes: " + e.getMessage());
        }
        return false;  // Fehler
    }

    // Methode zum Abrufen aller Geisternetz-Einträge
    public List<Geisternetz> getAllGeisternetze() {
        List<Geisternetz> geisternetze = new ArrayList<>();
        String sql = "SELECT id, description, gps_coordinates, estimated_size, status FROM geisternetz";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Geisternetz geisternetz = new Geisternetz();
                geisternetz.setId(resultSet.getInt("id"));
                geisternetz.setDescription(resultSet.getString("description"));
                geisternetz.setGpsCoordinates(resultSet.getString("gps_coordinates"));
                geisternetz.setEstimatedSize(resultSet.getString("estimated_size"));
                geisternetz.setStatus(resultSet.getString("status"));

                geisternetze.add(geisternetz);  // Liste füllen
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return geisternetze;  // Rückgabe der Liste der Geisternetze
    }

    // Methode zum Abrufen eines Geisternetzes nach id und status
    public Geisternetz getGeisternetzByIdAndStatus(int id, String status) {
        Geisternetz geisternetz = null;
        String sql = "SELECT id, description, gps_coordinates, estimated_size, status FROM geisternetz WHERE id = ? AND status = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, status);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    geisternetz = new Geisternetz();
                    geisternetz.setId(resultSet.getInt("id"));
                    geisternetz.setDescription(resultSet.getString("description"));
                    geisternetz.setGpsCoordinates(resultSet.getString("gps_coordinates"));
                    geisternetz.setEstimatedSize(resultSet.getString("estimated_size"));
                    geisternetz.setStatus(resultSet.getString("status"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return geisternetz;  // Rückgabe des gefundenen Geisternetzes oder null
    }
    
    public boolean deleteGeisternetzByIdAndStatus(int id, String status) {
        String sql = "DELETE FROM geisternetz WHERE id = ? AND status = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setString(2, status);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Gibt true zurück, wenn ein Datensatz gelöscht wurde
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Gibt false zurück, falls ein Fehler aufgetreten ist
    }
    
    public boolean updateGeisternetzStatus(int id, String newStatus) {
        String sql = "UPDATE geisternetz SET status = ? WHERE id = ?";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, newStatus);
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;  // Gibt true zurück, wenn der Status erfolgreich aktualisiert wurde
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Gibt false zurück, falls ein Fehler aufgetreten ist
    }

}



