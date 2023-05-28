package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class MySQLConnectionMangerTest {
    MySQLConnectionManager connectionManager = new MySQLConnectionManager();


    @Test
    void getConnection_shouldReturnConnectionToMySQLDB() {
        Connection connection = connectionManager.getConnection();
        Assertions.assertNotNull(connection);
        try {
            Assertions.assertFalse(connection.isClosed());
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

}
