/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.RoomController;
import java.awt.BorderLayout;
import java.awt.*;

import java.sql.*;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SearchRoom extends JFrame {
	private JPanel contentPane;
	private JTextField txt_Type;
	private JTable table;
        Choice c1, c2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchRoom frame = new SearchRoom();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close()
	{
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException
	 */
	public SearchRoom() throws SQLException {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(530, 200, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchForRoom = new JLabel("Search For Room");
		lblSearchForRoom.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSearchForRoom.setBounds(250, 11, 186, 31);
		contentPane.add(lblSearchForRoom);
		
		JLabel lblRoomAvailable = new JLabel("Sharing:");
		lblRoomAvailable.setBounds(50, 73, 96, 14);
		contentPane.add(lblRoomAvailable);
		
		JLabel lblRoomType = new JLabel("Room Number");
		lblRoomType.setBounds(23, 162, 96, 14);
		contentPane.add(lblRoomType);
		
		JLabel lblRoomAvailable_1 = new JLabel("Room Type");
		lblRoomAvailable_1.setBounds(175, 162, 120, 14);
		contentPane.add(lblRoomAvailable_1);
		
		JLabel lblPrice_1 = new JLabel("Price");
		lblPrice_1.setBounds(458, 162, 46, 14);
		contentPane.add(lblPrice_1);
                
                JLabel l1 = new JLabel("Status");
		l1.setBounds(580, 162, 96, 14);
		contentPane.add(l1);
		
		JCheckBox checkRoom = new JCheckBox("Only display Available");
		checkRoom.setBounds(465, 69, 205, 23);
                checkRoom.setBackground(Color.WHITE);
		contentPane.add(checkRoom);
		
		
                c1 = new Choice();
                c1.add("Single");
                c1.add("Double");
                c1.setBounds(115, 70, 70, 20);
		contentPane.add(c1);
                
                
                JLabel roomTypeLabel = new JLabel("Room Type:");
		roomTypeLabel.setBounds(250, 73, 70, 14);
                contentPane.add(roomTypeLabel);
                
                c2 = new Choice();
                c2.add("AC");
                c2.add("Non AC");
                c2.setBounds(335, 70, 70, 20);
		contentPane.add(c2);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            String sharing = c1.getSelectedItem();
                            String roomType = c2.getSelectedItem();
                            String status = null;
                            if (checkRoom.isSelected()) status = "Available";
                                RoomController roomController = new RoomController();
				table.setModel(roomController.searchRooms(sharing, roomType, status));
                        }
		});
		btnSearch.setBounds(200, 400, 120, 30);
                btnSearch.setBackground(Color.BLACK);
                btnSearch.setForeground(Color.WHITE);
		contentPane.add(btnSearch);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(380, 400, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
		
		table = new JTable();
		table.setBounds(0, 187, 700, 300);
		contentPane.add(table);
		
		JLabel lblCleanStatus = new JLabel("Sharing");
		lblCleanStatus.setBounds(306, 162, 96, 14);
		contentPane.add(lblCleanStatus);
                
                getContentPane().setBackground(Color.WHITE);
	}
}