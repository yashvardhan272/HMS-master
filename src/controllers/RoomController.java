package controllers;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RoomController {
    
    private final Sql sql;
    
    public RoomController() {
        this.sql = Sql.getInstance();
    }
    
    public void addRoom(Integer roomNo, String sharing, String roomType, Double price,  String status) {
        Map<String, Object> room = new HashMap<>();
        room.put("room_no", (Object) roomNo);
        room.put("sharing", (Object) sharing);
        room.put("room_type", (Object) roomType);
        room.put("price", (Object) price);
        room.put("status", (Object) status);

        try {
            sql.insertInto("rooms", room);
            JOptionPane.showMessageDialog(null, "Room added successfully");
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
        }
    }

    public DefaultTableModel getRooms() {
        try {
            ResultSet rs = sql.select("rooms");
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
            JOptionPane.showMessageDialog(null, e.toString());
            return new DefaultTableModel();
        }               
    }
    
    public DefaultTableModel searchRooms(String sharing, String roomType, String status) {
        try {
            String whereClause = "sharing = '" + sharing + "' ";
            whereClause += "and room_type = '" + roomType + "' ";
            if (status != null) whereClause += "and status = '" + status + "'";
            ResultSet rs = sql.select("rooms", whereClause);
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
            JOptionPane.showMessageDialog(null, e.toString());
            return new DefaultTableModel();
        }               
    }

    public String getRoomPrice(Integer roomNo) {
        try {
            ArrayList<String> columns = new ArrayList<>();
            columns.add("price");
            ResultSet rs = sql.select("rooms", columns, "room_no = " + roomNo);
            if (rs.next()) {
                return rs.getString("price");
            }
            else return "";
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return "";
        }
    }
}
