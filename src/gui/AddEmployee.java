package gui;


import controllers.EmployeeController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddEmployee extends JFrame{ //Third Frame

    
	JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
        JComboBox c1;

        public AddEmployee(){
            getContentPane().setForeground(Color.BLUE);
            getContentPane().setBackground(Color.WHITE);
            setTitle("ADD EMPLOYEE DETAILS");
		 
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            setSize(778,486);
            getContentPane().setLayout(null);
			
            JLabel passportNo = new JLabel("NAME");
            passportNo.setFont(new Font("Tahoma", Font.PLAIN, 17));
            passportNo.setBounds(60, 30, 150, 27);
            add(passportNo);
            
            textField = new JTextField();
            textField.setBounds(200, 30, 150, 27);
            add(textField);
			
            JButton nextButton = new JButton("SAVE");
            nextButton.setBounds(200, 420, 150, 30);
            nextButton.setBackground(Color.BLACK);
            nextButton.setForeground(Color.WHITE);
            add(nextButton);
			
            JLabel ageLabel = new JLabel("D.O.B");
            ageLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            ageLabel.setBounds(60, 80, 150, 27);
            add(ageLabel);
			
            textField_1 = new JTextField();
            textField_1.setBounds(200, 80, 150, 27);
            add(textField_1);
            
            JLabel genderLabel = new JLabel("GENDER");
            genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            genderLabel.setBounds(60, 120, 150, 27);
            add(genderLabel);
		
            JRadioButton maileRadioButton = new JRadioButton("MALE");
            maileRadioButton.setBackground(Color.WHITE);
            maileRadioButton.setBounds(200, 120, 70, 27);
            add(maileRadioButton);
	
            JRadioButton femaleRadioButton = new JRadioButton("FEMALE");
            femaleRadioButton.setBackground(Color.WHITE);
            femaleRadioButton.setBounds(280, 120, 70, 27);
            add(femaleRadioButton);
            
            
            JLabel addressButtion = new JLabel("JOB");
            addressButtion.setFont(new Font("Tahoma", Font.PLAIN, 17));
            addressButtion.setBounds(60, 170, 150, 27);
            add(addressButtion);
			
            String course[] = {"Front Desk Clerks","Porters","Housekeeping","Kitchen Staff","Room Service","Waiter/Waitress","Manager","Accountant","Chef"};
            c1 = new JComboBox(course);
            c1.setBackground(Color.WHITE);
            c1.setBounds(200,170,150,30);
            add(c1);
            		
            JLabel nationalityLabel = new JLabel("SALARY");
            nationalityLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            nationalityLabel.setBounds(60, 220, 150, 27);
            add(nationalityLabel);
			
            textField_3 = new JTextField();
            textField_3.setBounds(200, 220, 150, 27);
            add(textField_3);
	
            JLabel nameLabel = new JLabel("PHONE");
            nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            nameLabel.setBounds(60, 270, 150, 27);
            add(nameLabel);
	
            textField_4 = new JTextField();
            textField_4.setBounds(200, 270, 150, 27);
            add(textField_4);
	
            JLabel aadhaarLabel = new JLabel("AADHAR");
            aadhaarLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            aadhaarLabel.setBounds(60, 320, 150, 27);
            add(aadhaarLabel);
			
            textField_5 = new JTextField();
            textField_5.setBounds(200, 320, 150, 27);
            add(textField_5);
	
            
            JLabel emailLabel = new JLabel("EMAIL");
            emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
            emailLabel.setBounds(60, 370, 150, 27);
            add(emailLabel);
			
            textField_6 = new JTextField();
            textField_6.setBounds(200, 370, 150, 27);
            add(textField_6);
	
            setVisible(true);
	
            JLabel addEmployeeLabel = new JLabel("ADD EMPLOYEE DETAILS");
            addEmployeeLabel.setForeground(Color.BLUE);
            addEmployeeLabel.setFont(new Font("Tahoma", Font.PLAIN, 31));
            addEmployeeLabel.setBounds(450, 24, 442, 35);
            add(addEmployeeLabel);
			
     
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/tenth.jpg"));
            Image i3 = i1.getImage().getScaledInstance(500, 500,Image.SCALE_DEFAULT);
            ImageIcon i2 = new ImageIcon(i3);
            JLabel image = new JLabel(i2);
            image.setBounds(410,80,480,410);
            add(image);

            
            nextButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae){
                    String name = textField.getText();
                    String email = textField_6.getText();
                    String dob = textField_1.getText();
                    String gender = null;
                    if(maileRadioButton.isSelected()){
                        gender = "Male";
                    
                    }else if(femaleRadioButton.isSelected()){
                        gender = "Female";
                    }
                    Double salary = Double.valueOf(textField_3.getText());
                    Long phone = Long.valueOf(textField_4.getText());
                    String aadhaar = textField_5.getText();
                    String designation = (String) c1.getSelectedItem();
                    EmployeeController employeeController = new EmployeeController();
                        if (employeeController.addEmployee(name, email, dob, designation, gender, salary, phone, aadhaar)) {
                            setVisible(false);
                        }
		}
            });
			
            setSize(900,600);
            setVisible(true);
            setLocation(530,200);
			
	}
}