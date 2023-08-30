package controllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EmployeeController {
    
    private final Sql sql;
    
    public EmployeeController() {
        this.sql = Sql.getInstance();
    }
    
    public boolean addEmployee(String name, String email, String dob, String designation, String gender, Double salary, Long mobile, String aadhaar) {
//        System.out.println(name + " " + email + " " + dob + " " + designation + " " + gender + " " + salary + " " + mobile + " " + aadhaar);
        Map<String, Object> employee = new HashMap<>();
        employee.put("name", (Object) name);
        employee.put("email", (Object) email);
        employee.put("dob", (Object) dob);
        employee.put("gender", (Object) gender);
        employee.put("salary", (Object) salary);
        employee.put("mobile", (Object) mobile);
        employee.put("aadhaar", (Object) aadhaar);
        employee.put("designation", designation);

        try {
            sql.insertInto("employees", employee);
            JOptionPane.showMessageDialog(null, "Employee Added successfully");
            return true;
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
    
    public DefaultTableModel getEmployees() {
        try {
            ResultSet rs = sql.select("employees");
            ResultSetMetaData metaData = rs.getMetaData();

            // names of columns
            Vector<String> columnNames = new Vector<>();
            int columnCount = metaData.getColumnCount();
            for (int column = 1; column <= columnCount; column++) {
                columnNames.add(metaData.getColumnName(column));
            }

            // data of the table
            Vector<Vector<Object>> rooms = new Vector<Vector<Object>>();
            while (rs.next()) {
                Vector<Object> room = new Vector<Object>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    room.add(rs.getObject(columnIndex));
                }
                rooms.add(room);
            }
            return new DefaultTableModel(rooms, columnNames);
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return new DefaultTableModel();
        }                   
    }
    
}
