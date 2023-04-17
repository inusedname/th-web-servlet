/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.local;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import java.sql.*;

/**
 *
 * @author QuangNV
 */
public class MySQLManager {

    public static final String tblName = "schema_todo";

    private static MySQLManager _instance = null;
    private Connection connection = null;

    @Nullable
    public ResultSet query(@Nonnull String query, Object... varargs) {
        try(PreparedStatement statement = connection.prepareStatement(query)) {
            passingParamsToPrepareStatement(statement, varargs);
            ResultSet resultSet = statement.executeQuery();
            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public Integer insert(@Nonnull String query, Object... varargs) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            passingParamsToPrepareStatement(statement, varargs);
            statement.executeUpdate();
            try (ResultSet resultSet = connection.createStatement().executeQuery("SELECT LAST_INSERT_ID();")) {
                return resultSet.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Nullable
    public void update(@Nonnull String query, Object... varargs) {
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            passingParamsToPrepareStatement(statement, varargs);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static MySQLManager getInstance() {
        if (_instance == null) {
            _instance = new MySQLManager();
            _instance.connect();
        }
        return _instance;
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private MySQLManager() {
    }

    private void passingParamsToPrepareStatement(PreparedStatement statement, Object... varargs) {
        try {
            for (int i = 0; i < varargs.length; i++) {
                Class objectClass = varargs[i].getClass();
                if (objectClass.equals(String.class)) {
                    statement.setString(i + 1, (String) varargs[i]);
                } else if (objectClass.equals(Integer.class)) {
                    statement.setInt(i + 1, (Integer) varargs[i]);
                } else if (objectClass.equals(Long.class)) {
                    statement.setLong(i + 1, (Long) varargs[i]);
                } else if (objectClass.equals(Double.class)) {
                    statement.setDouble(i + 1, (Double) varargs[i]);
                } else if (objectClass.equals(Float.class)) {
                    statement.setFloat(i + 1, (Float) varargs[i]);
                } else if (objectClass.equals(Boolean.class)) {
                    statement.setObject(i + 1, (Integer) varargs[i], Types.TINYINT);
                } else {
                    throw new Exception(objectClass.toString() + "is not supported yet");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                String url = "jdbc:mysql://localhost:3306/" + tblName;
                String username = "root";
                String password = "123456";
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
