package iu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GeisternetzDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/daten";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    // Methode zum Speichern eines Geisternetzes
    public void saveGeisternetz(Geisternetz geisternetz) {
        String sql = "INSERT INTO geisternetz (description, gps_coordinates, estimated_size, status) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, geisternetz.getDescription());
            statement.setString(2, geisternetz.getGpsCoordinates());
            statement.setString(3, geisternetz.getEstimatedSize());
            statement.setString(4, geisternetz.getStatus());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Methode zum Abrufen aller Geisternetze
    public List<Geisternetz> findAllGeisternetze() {
        List<Geisternetz> netze = new ArrayList<>();
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
                netze.add(geisternetz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return netze;
    }
}

