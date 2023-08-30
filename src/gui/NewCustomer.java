/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import controllers.CustomerController;
import java.awt.*;
import java.awt.EventQueue;

import java.awt.Font;
import java.awt.Image;
import java.sql.*;	
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewCustomer extends JFrame {
	private JPanel contentPane;
	private JTextField idNumberTextField,nameTextField,mobileTextField,t4,emailTextField,countryTextField;
        JComboBox comboBox;
        JRadioButton r1,r2;
//        Choice c1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewCustomer frame = new NewCustomer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public NewCustomer() throws SQLException {
		
                setBounds(530, 200, 850, 550);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
                
                ImageIcon i1  = new ImageIcon(ClassLoader.getSystemResource("images/fifth.png"));
                Image i3 = i1.getImage().getScaledInstance(300, 400,Image.SCALE_DEFAULT);
                ImageIcon i2 = new ImageIcon(i3);
                JLabel l1 = new JLabel(i2);
                l1.setBounds(480,10,300,500);
                add(l1);
		
		JLabel lblName = new JLabel("NEW CUSTOMER FORM");
		lblName.setFont(new Font("Yu Mincho", Font.PLAIN, 20));
		lblName.setBounds(118, 11, 260, 53);
		contentPane.add(lblName);
                
                JLabel lblId = new JLabel("Id Proof:");
		lblId.setBounds(35, 76, 200, 14);
		contentPane.add(lblId);
                
                comboBox = new JComboBox(new String[] {"Passport", "Aadhar Card", "Voter Id", "Driving License"});
		comboBox.setBounds(271, 73, 150, 20);
		contentPane.add(comboBox);
                
                JLabel l2 = new JLabel("Id Number :");
		l2.setBounds(35, 111, 200, 14);
		contentPane.add(l2);
                
                idNumberTextField = new JTextField();
		idNumberTextField.setBounds(271, 111, 150, 20);
		contentPane.add(idNumberTextField);
		idNumberTextField.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name :");
		lblName_1.setBounds(35, 151, 200, 14);
		contentPane.add(lblName_1);
		
		nameTextField = new JTextField();
		nameTextField.setBounds(271, 151, 150, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);

                
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setBounds(35, 191, 200, 14);
		contentPane.add(lblGender);
                
                r1 = new JRadioButton("Male");
                r1.setFont(new Font("Raleway", Font.BOLD, 14));
                r1.setBackground(Color.WHITE);
                r1.setBounds(271, 191, 80, 12);
                add(r1);
                
                r2 = new JRadioButton("Female");
                r2.setFont(new Font("Raleway", Font.BOLD, 14));
                r2.setBackground(Color.WHITE);
                r2.setBounds(350, 191, 100, 12);
		add(r2);
                
		JLabel lblCountry = new JLabel("Mobile :");
		lblCountry.setBounds(35, 231, 200, 14);
		contentPane.add(lblCountry);
		
//		JLabel lblReserveRoomNumber = new JLabel("Allocated Room Number :");
//		lblReserveRoomNumber.setBounds(35, 274, 200, 14);
//		contentPane.add(lblReserveRoomNumber);
                
//                c1.setBounds(271, 274, 150, 20);
//		contentPane.add(c1);
		
		JLabel lblCheckInStatus = new JLabel("Email :");
		lblCheckInStatus.setBounds(35, 274, 200, 14);
		contentPane.add(lblCheckInStatus);
		
		JLabel lblDeposite = new JLabel("Country :");
		lblDeposite.setBounds(35, 316, 200, 14);
		contentPane.add(lblDeposite);
		
		
		
		
		
		mobileTextField = new JTextField();
		mobileTextField.setBounds(271, 231, 150, 20);
		contentPane.add(mobileTextField);
		mobileTextField.setColumns(10);
		
		
		emailTextField = new JTextField();
		emailTextField.setBounds(271, 274, 150, 20);
		contentPane.add(emailTextField);
		emailTextField.setColumns(10);
		
		countryTextField = new JTextField();
		countryTextField.setBounds(271, 316, 150, 20);
		contentPane.add(countryTextField);
		countryTextField.setColumns(10);

		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            String gender = null;
                            
                            if(r1.isSelected()){ 
                                gender = "Male";
                            }
                            else if(r2.isSelected()){ 
                                gender = "Female";
                            }
                            
//                            String s6 = c1.getSelectedItem();
                            String idProof = (String)comboBox.getSelectedItem(); 
                            String idNumber =  idNumberTextField.getText();
                            String name =  nameTextField.getText();
                            Long  mobile =  Long.valueOf(mobileTextField.getText());
                            String email =  emailTextField.getText();
                            String country =  countryTextField.getText();
//                            String country = t4.getText();

                            CustomerController customerController = new CustomerController();
                            customerController.registerCustomer(name, mobile, country, gender, email, idProof, idNumber);


                            new Reception().setVisible(true);
                            setVisible(false);
                        }
		});
		btnNewButton.setBounds(100, 400, 120, 30);
                btnNewButton.setBackground(Color.BLACK);
                btnNewButton.setForeground(Color.WHITE);
		contentPane.add(btnNewButton);
		
		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                            new Reception().setVisible(true);
                            setVisible(false);
			}
		}); 
		btnExit.setBounds(260, 400, 120, 30);
                btnExit.setBackground(Color.BLACK);
                btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);
                
                getContentPane().setBackground(Color.WHITE);
	}
}