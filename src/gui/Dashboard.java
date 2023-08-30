/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Dashboard extends JFrame{

    public static void main(String[] args) {
        new Dashboard().setVisible(true);
    }
    
    public Dashboard() {
        super("HOTEL MANAGEMENT SYSTEM");
	
        setForeground(Color.CYAN);
        setLayout(null); 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        ImageIcon image = new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("images/third.jpg")).getImage().getScaledInstance(1950, 1000,Image.SCALE_DEFAULT)); 
	JLabel NewLabel = new JLabel(image);
	NewLabel.setBounds(0, 0, 1950, 1000); 
        add(NewLabel);
        
        JLabel ezStayLabel = new JLabel("Welcome to EzStay");
	ezStayLabel.setForeground(Color.WHITE);
        ezStayLabel.setFont(new Font("Tahoma", Font.PLAIN, 46));
	ezStayLabel.setBounds(600, 60, 1000, 85);
	NewLabel.add(ezStayLabel);
		
		
        JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
		
        JMenu hotelManagement = new JMenu("HOTEL MANAGEMENT");
        hotelManagement.setForeground(Color.BLUE);
	menuBar.add(hotelManagement);
		
        
		
	JMenu admin = new JMenu("ADMIN");
        admin.setForeground(Color.RED);
	menuBar.add(admin);
        
        JMenuItem addEmployee = new JMenuItem("ADD EMPLOYEE");
	admin.add(addEmployee);
        
        addEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new AddEmployee().setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        JMenuItem allEmployee = new JMenuItem("ALL EMPLOYEES");
	admin.add(allEmployee);
        
            allEmployee.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new Employee().setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        
        

        JMenuItem addRooms = new JMenuItem("ADD ROOMS");
	admin.add(addRooms);
        
        addRooms.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                try{
                    new AddRoom().setVisible(true);
                }catch(Exception e ){}
            }
	});
        
        JMenuItem reception = new JMenuItem("RECEPTION");
	hotelManagement.add(reception);
        
	reception.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                new Reception().setVisible(true);
            }
	});

        setSize(1950,1090);
	setVisible(true);
        getContentPane().setBackground(Color.WHITE);
    }
}
