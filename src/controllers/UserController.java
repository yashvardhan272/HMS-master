package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class UserController {

    private final Sql sql;
    
    public UserController() {
        this.sql = Sql.getInstance();
    }
    
    public void registerUser(String firstName, String lastName, String email, String password, Long mobile, String dob, String address) {
        Map<String, Object> user = new HashMap<>();
        user.put("first_name", (Object) firstName);
        user.put("last_name", (Object) lastName);
        user.put("email", (Object) email);
        user.put("password", (Object) password);
        user.put("mobile", (Object) mobile);
        user.put("dob", (Object) dob);
        user.put("address", (Object) address);

        try {
            sql.insertInto("users", user);
            JOptionPane.showMessageDialog(null, "User created successfully");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void setPassword (String email, String password) {
        try {
            String whereClause =  "email = '" + email + "'";
            sql.update("users", Map.ofEntries(Map.entry("password", (Object) password)), whereClause);
            JOptionPane.showMessageDialog(null, "Password set successfully");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public boolean authenticate(String username, String password) {
        try {
            ResultSet rs = sql.select("users", "username = '" + username + "' and password = '" + password + "'");
            if (!rs.next()) {
                JOptionPane.showMessageDialog(null, "Invalid Credentials");
                return false;
            }
            else return true;
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}
