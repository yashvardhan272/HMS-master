/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controllers.CustomerController;
import controllers.RoomController;
import java.awt.BorderLayout;
import java.awt.*;

import java.sql.*;	
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CheckIn extends JFrame {
	private JPanel contentPane;
	private JTextField txt_ID;
        private JTextField mobileTextField;
	private JTextField txt_Room;
	private JTextField nameTextField;
	private JTextField roomTextField;
	private JTextField priceTextField;
	private JTextField txt_Payment;
        private CustomerController customerController;

        Choice c2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIn frame = new CheckIn();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void close(){
		this.dispose();
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public CheckIn() {
		//conn = Javaconnect.getDBConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 200, 950, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUpdateCheckStatus = new JLabel("Check In");
		lblUpdateCheckStatus.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblUpdateCheckStatus.setBounds(124, 11, 222, 25);
		contentPane.add(lblUpdateCheckStatus);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("images/nine.jpg"));
                JLabel l1 = new JLabel(i1);
                l1.setBounds(400,70,476,270);
                add(l1);
		
		JLabel lblNewLabel = new JLabel("Mobile:");
		lblNewLabel.setBounds(25, 88, 46, 14);
		contentPane.add(lblNewLabel);
                
                mobileTextField = new JTextField();
           
                mobileTextField.setBounds(248, 85, 140, 20);
		contentPane.add(mobileTextField);
		
		JLabel lblNewLabel_1 = new JLabel("Name: ");
		lblNewLabel_1.setBounds(25, 129, 107, 14);
		contentPane.add(lblNewLabel_1);
                
                
//                txt_ID = new JTextField();
//                txt_ID.setBounds(248, 126, 140, 20);
//		contentPane.add(txt_ID);
		
		JLabel lblNewLabel_2 = new JLabel("Room No : ");
		lblNewLabel_2.setBounds(25, 174, 97, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Price :");
		lblNewLabel_3.setBounds(25, 216, 107, 14);
		contentPane.add(lblNewLabel_3);

		
		nameTextField = new JTextField();
                nameTextField.setEnabled(false);
		nameTextField.setBounds(248, 126, 140, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		roomTextField = new JTextField();
                roomTextField.setEnabled(false);
		roomTextField.setBounds(248, 171, 140, 20);
		contentPane.add(roomTextField);
		roomTextField.setColumns(10);
		
		priceTextField=  new JTextField();
                priceTextField.setEnabled(false);
		priceTextField.setBounds(248, 216, 140, 20);
		contentPane.add(priceTextField);
		priceTextField.setColumns(10);
		
//		txt_Payment = new JTextField();
//		txt_Payment.setBounds(248, 299, 140, 20);
//		contentPane.add(txt_Payment);
//		txt_Payment.setColumns(10);
		
//		JButton btnUpdate = new JButton("Check");
//		btnUpdate.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent ae) { 
//                            try{
//                                
//                                
////                                String name = txt_Status.getText(); //name    
//				String room_no = txt_ID.getText(); //room_number;    
//                                String s4 = roomTextField.getText(); //status;    
//                                String s5 = priceTextField.getText(); //deposit    
//				
//                                
//                                new Reception().setVisible(true);
//                                setVisible(false);
//                            }catch(Exception ee){
//                                System.out.println(ee);
//                            }				
//				
//				
//				
//			}
//		});
//		btnUpdate.setBounds(168, 378, 89, 23);
//                btnUpdate.setBackground(Color.BLACK);
//                btnUpdate.setForeground(Color.WHITE);
//		contentPane.add(btnUpdate);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reception().setVisible(true);
                                setVisible(false);
			}
		});
		btnExit.setBounds(281, 378, 89, 23);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                customerController = new CustomerController();
		
		JButton btnAdd = new JButton("Check");
		btnAdd.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Long mobile = -1L;
                        Integer roomNo = -1;
                        if (nameTextField.getText().isEmpty()) {
                            try {
                                 mobile = Long.valueOf(mobileTextField.getText());
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
                            }
                            String name = customerController.searchCustomer(mobile);
                            if (!name.isEmpty()) {
                                nameTextField.setText(name);
                                nameTextField.setEnabled(true);
                                nameTextField.setEditable(false);
                                roomTextField.setEnabled(true);
                            }
                        }
                        else if (priceTextField.getText().isEmpty()) {
                            
                            try {
                                roomNo = Integer.valueOf(roomTextField.getText());
                            } catch (NumberFormatException nfe) {
                                JOptionPane.showMessageDialog(null, "Invalid Mobile Number");
                            }
                            RoomController roomController = new RoomController();
                            String price = roomController.getRoomPrice(roomNo);
                            if (!price.isEmpty()) {
                                priceTextField.setText(price);
                                priceTextField.setEnabled(true);
                                priceTextField.setEditable(false);
                            }
                        }
                        else {

                            customerController.customerCheckIn(Long.valueOf(mobileTextField.getText()), Integer.valueOf(roomTextField.getText()), new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()), Double.valueOf(priceTextField.getText()));
                        }
                    }
		});
		btnAdd.setBounds(56, 378, 89, 23);
                btnAdd.setBackground(Color.BLACK);
                btnAdd.setForeground(Color.WHITE);
		contentPane.add(btnAdd);
                
                getContentPane().setBackground(Color.WHITE);
	}

}