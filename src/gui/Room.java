/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.RoomController;
import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTable;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class Room extends JFrame {
	private JPanel contentPane;
	private JTable table;
	private JLabel lblAvailability;
	private JLabel lblCleanStatus;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblRoomNumber;
	private JLabel lblId;

        
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room frame = new Room();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

        
	public Room() {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 1100, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("images/eight.jpg"));
                Image i3 = i1.getImage().getScaledInstance(600, 600,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(500,0,600,600);
                add(l1);
                
		
		table = new JTable();
		table.setBounds(0, 40, 500, 400);
		contentPane.add(table);
		
		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            RoomController roomController = new RoomController();
                            table.setModel(roomController.getRooms());
			}
		});
		btnLoadData.setBounds(100, 470, 120, 30);
                btnLoadData.setBackground(Color.BLACK);
                btnLoadData.setForeground(Color.WHITE);
		contentPane.add(btnLoadData);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnNewButton.setBounds(290, 470, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		lblAvailability = new JLabel("Room Type");
		lblAvailability.setBounds(119, 15, 69, 14);
		contentPane.add(lblAvailability);
		
		lblCleanStatus = new JLabel("Sharing");
		lblCleanStatus.setBounds(216, 15, 76, 14);
		contentPane.add(lblCleanStatus);
		
		lblNewLabel = new JLabel("Price");
		lblNewLabel.setBounds(330, 15, 46, 14);
		contentPane.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Status");
		lblNewLabel_1.setBounds(417, 15, 76, 14);
		contentPane.add(lblNewLabel_1);

		
		lblId = new JLabel("Room Number");
		lblId.setBounds(12, 15, 90, 14);
		contentPane.add(lblId);
                
                getContentPane().setBackground(Color.WHITE);
	}

}