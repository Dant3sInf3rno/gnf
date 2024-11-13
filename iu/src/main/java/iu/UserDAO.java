package iu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/daten";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "root";

    // Verbindung zur Datenbank herstellen
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
    }

    // Methode zum Speichern des Benutzers
    public boolean saveUser(User user) {
        String sql = "INSERT INTO benutzer (name, handynummer) VALUES (?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            
            statement.setString(1, user.getName());
            statement.setString(2, user.getHandynummer());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

