package gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import controllers.UserController;

public class Login extends JFrame implements ActionListener {
    
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    
    
    public Login(){

        super("Login");

        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(40,20,100,30);
        add(usernameLabel);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(40,70,100,30);
        add(passwordLabel);
 
        usernameTextField=new JTextField();
        usernameTextField.setBounds(150,20,150,30);
        add(usernameTextField);

        passwordField=new JPasswordField();
        passwordField.setBounds(150,70,150,30);
        add(passwordField);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200,200,Image.SCALE_DEFAULT);
        ImageIcon i3 =  new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(350,10,150,150);
        add(l3);


        loginButton = new JButton("Login");
        loginButton.setBounds(40,140,120,30);
        loginButton.setFont(new Font("serif",Font.BOLD,15));
        loginButton.addActionListener(this);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        add(loginButton);

        cancelButton=new JButton("Cancel");
        cancelButton.setBounds(180,140,120,30);
        cancelButton.setFont(new Font("serif",Font.BOLD,15));
        cancelButton.setBackground(Color.BLACK);
        cancelButton.setForeground(Color.WHITE);
        add(cancelButton);

        cancelButton.addActionListener(this);
        
        
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
        setSize(600,300);
        setLocation(600,350);

    }

    @Override
    public void actionPerformed(ActionEvent event){
        if(event.getSource() == loginButton){
            String username = usernameTextField.getText();
            String password = passwordField.getText();

            UserController userController = new UserController();
            if (userController.authenticate(username, password)) {
                new Dashboard().setVisible(true);
                this.setVisible(false);
            } 
        } else if(event.getSource() == cancelButton){
            System.exit(0);
        }
    }
}