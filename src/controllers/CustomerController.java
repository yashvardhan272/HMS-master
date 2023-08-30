package controllers;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class CustomerController {

    private final Sql sql;
    
    public CustomerController() {
        this.sql = Sql.getInstance();
    }
    
    public DefaultTableModel getCustomers() {
        try {
            ResultSet rs = sql.select("customers");
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
    
    public void registerCustomer(String customerName, Long mobile, String nationality, String gender,String email, String idProof, String idNumber) {
        System.out.println(customerName + mobile + nationality + gender + email + idProof + idNumber);
        Map<String, Object> customer = new HashMap<>();
        customer.put("customer_name", (Object) customerName);
        customer.put("mobile_number", (Object) mobile);
        customer.put("nationality", (Object) nationality);
        customer.put("gender", (Object) gender);
        
         customer.put("email", (Object) email);
         
       customer.put("id_proof", (Object) idProof);
       customer.put("id_number", (Object) idNumber);
             
        try {
             sql.insertInto("customers", customer);
            JOptionPane.showMessageDialog(null, "Customer created successfully");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void customerCheckIn(Long mobile, Integer roomNo, String checkIn, Double price) {
        Map<String, Object> booking = new HashMap<>();
        booking.put("customer_id", (Object) mobile);
        booking.put("check_in", (Object) checkIn);
        booking.put("room_no", (Object) roomNo );
        booking.put("price", (Object) price);
          
        try {
            sql.insertInto("bookings", booking);
            sql.update("rooms", Map.ofEntries(Map.entry("status", "Not Available")), "room_no = " + roomNo);
            JOptionPane.showMessageDialog(null, "Booking done successfully");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void customerCheckOut(String customerId, String roomNo, String checkOut) {
        try {
            ResultSet rs = sql.select("bookings", "customer_id = " + customerId + " and check_out IS NULL");
            String checkIn = rs.getString("check_in");
            LocalDate checkINDate = LocalDate.parse(checkIn);
            LocalDate checkOUTDate = LocalDate.parse(checkOut);
            Duration diff = Duration.between(checkINDate, checkOUTDate);
            long stayPeriod = diff.toDays();
            sql.execute("UPDATE bookings SET check_out = '" + checkOut + "', stay_period = 0"+ stayPeriod +", total_amount = price * stay_period WHERE customer_id = " + customerId + " and check_out IS NULL");
            sql.update("rooms", Map.ofEntries(Map.entry("status", "Available")), "room_no = " + roomNo);
            JOptionPane.showMessageDialog(null, "Check Out Successful");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public ResultSet getCheckedInCustomerIds() {
        try {
            ArrayList<String> columns = new ArrayList<>();
            columns.add("customer_id");
            return sql.select("bookings", columns, "check_out IS NULL");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return null;
        }
    }

    public String getAllocatedRoom(String customerId) {
        try {
            ResultSet rs = sql.select("bookings",  "customer_id = " + customerId + " and check_out IS NULL");
            if (rs.next()) return rs.getString("room_no");
            else return "";
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
    
    public String searchCustomer(Long mobile) {
        try {
            ResultSet rs = sql.select("customers",  "mobile_number = " + mobile);
            if (rs.next()) {
                return rs.getString("customer_name");
            }
            else {
                JOptionPane.showMessageDialog(null, "Not Found");
                return "";
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return "";
        }
    }
}
