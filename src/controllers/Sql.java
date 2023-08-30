package controllers;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;

public class Sql {

    private Connection connection;

    private static Sql instance = null;

    public static Sql getInstance() {
        if (Sql.instance == null) {
            Sql.instance = new Sql();
        }
        return Sql.instance;
    }

    private Sql() {

    }

    private void openConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ezstay", "root", "yashvardhan");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    private void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public ResultSet select(String tableName) throws SQLException {
        openConnection();
        String query = "SELECT * FROM " + tableName;
        Statement statement = connection.createStatement();
        ResultSet result =  statement.executeQuery(query);
       
        return result;
    }
    
    public ResultSet select(String tableName, ArrayList<String> columns) throws SQLException {
        openConnection();
        String query = "SELECT ";
        int i = 0;
        for (String column : columns) {
            query += column;
            if (++i < columns.size()) {
                query += ", ";
            }
        }
        query +=  " FROM " + tableName;
        Statement statement = connection.createStatement();
        ResultSet result =  statement.executeQuery(query);
        return result;
    }
    
    public ResultSet select(String tableName, String whereClause) throws SQLException {
        openConnection();
        String query = "SELECT * FROM " + tableName + " WHERE " + whereClause;
        Statement statement = connection.createStatement();
        ResultSet result =  statement.executeQuery(query);
        return result;    
    }
    
    public ResultSet select(String tableName, ArrayList<String> columns, String whereClause) throws SQLException {
        openConnection();
        String query = "SELECT ";
        int i = 0;
        for (String column : columns) {
            query += column;
            if (++i < columns.size()) {
                query += ", ";
            }
        }
        query +=  " FROM " + tableName + " WHERE " + whereClause;
        Statement statement = connection.createStatement();
        ResultSet result =  statement.executeQuery(query);
        return result;
    }
        
    public void insertInto(String table, Map<String, Object> columnValueMap) throws SQLException {
        openConnection();
        String query = "INSERT INTO " + table + " (";
        String values = " VALUES (";
        int i = 0;
        for (String column : columnValueMap.keySet()) {
            query += column;
            values += "?";
            if (++i < columnValueMap.size()) {
                query += ", ";
                values += ", ";
            }
        }
        query += ")" + values + ")";
        PreparedStatement statement = connection.prepareStatement(query);
        i = 1;
        for (Object value : columnValueMap.values()) {
            statement.setObject(i++, value);
        }
        statement.executeUpdate();
        closeConnection();
    }
    
    public void update(String tableName, Map<String, Object> columnValueMap, String whereClause) throws SQLException {
        openConnection();
        String query = "UPDATE " + tableName + " SET ";
        int i = 0;
        for (String column : columnValueMap.keySet()) {
            query += column + " = ?";
            if (++i < columnValueMap.size()) {
                query += ", ";
            }
            else query += " ";
        }
        query += " WHERE " + whereClause;
        PreparedStatement statement = connection.prepareStatement(query);
        i = 1;
        for (Object value : columnValueMap.values()) {
            statement.setObject(i++, value);
        }
        statement.executeUpdate();
        closeConnection();
    }
    
    public void delete(String tableName, String whereClause) throws SQLException {
        openConnection();
        String query = "DELETE FROM " + tableName + " WHERE " + whereClause;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
        closeConnection();
    }
    
    public void execute(String query) throws SQLException {
        openConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        closeConnection();
    }

}
