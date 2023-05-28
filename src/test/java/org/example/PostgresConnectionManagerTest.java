package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class PostgresConnectionManagerTest {

    PostgresConnectionManager connectionManager = new PostgresConnectionManager();

    @Test
    public void getConnection_shouldReturnValidConnectionToPostgresDB(){
        Connection connection = connectionManager.getConnection();
        Assertions.assertNotNull(connection);
        try {
            Assertions.assertFalse(connection.isClosed());
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }
}
