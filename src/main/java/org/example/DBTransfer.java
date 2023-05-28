package org.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBTransfer {
    private static final List<User> users = new ArrayList<>();
    private static final String SELECT_FROM_USER = "SELECT * FROM user";
    private static final String UPDATE_FROM_USER = "INSERT INTO test.user (id, name, level, created) VALUES(?, ?, ?, ?)";

    public static void main(String[] args) throws SQLException {

        try (Statement statementMySQL = new MySQLConnectionManager().getConnection().createStatement();
             PreparedStatement preparedStatementPostgres = new PostgresConnectionManager().getConnection()
                     .prepareStatement(UPDATE_FROM_USER)) {

            System.out.println("Выборка строк из таблицы...");

            ResultSet resultSet = statementMySQL.executeQuery(SELECT_FROM_USER);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int level = resultSet.getInt("level");
                LocalDate created = resultSet.getObject("created_date", LocalDate.class);
                users.add(new User(id, name, level, created));
            }

            System.out.println("Данные выбраны, добавление в новую базу данных...");

            users.forEach(user -> {
                try {
                    preparedStatementPostgres.setInt(1, user.getId());
                    preparedStatementPostgres.setString(2, user.getName());
                    preparedStatementPostgres.setInt(3, user.getLevel());
                    preparedStatementPostgres.setDate(4, Date.valueOf(user.getCreated()));
                    preparedStatementPostgres.addBatch();
                    System.out.println("Добавлен в очередь - " + user);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException("SNAFU");
                }
            });

            System.out.println("Все юзеры добавлены. Перенос в новую базу данных...");
            preparedStatementPostgres.executeBatch();
            System.out.println("Перенос бд завершен.");
        }
    }

}
