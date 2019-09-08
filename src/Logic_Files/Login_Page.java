/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic_Files;

import Common_Files.cfg;
import DesignUI.DesignUI;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Dell
 */
public class Login_Page extends JFrame {

    private static JFrame mainFrame;
    private static java.sql.Connection conn;
    public static String passwordStr = "";
    
    public static void main(String args[]) {
        DesignUI obj = new DesignUI();
        mainFrame = obj.makeFrame(mainFrame, "Login");

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Tax Invoice;user=batch_admin;password=admin";
            conn = DriverManager.getConnection(url);
            //Connection done
            String sqlcmd = "select * from Login_tbl;";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sqlcmd);
            
            while (rs.next()) {
                passwordStr = rs.getString("Password");
//                System.out.println("Password from db" + passwordStr);
            }
//            conn.close();

        } catch (Exception e) {
            System.out.println("Error = " + e.getMessage());
        }
        SpringLayout sLayout = new SpringLayout();
        Container cont = new Container();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cont.setLayout(sLayout);
        
//        ImageIcon iconLogo = new ImageIcon(Login_Page.class.getResource("/Images/username.png"));
//        JLabel userImg = new JLabel();
//        userImg.setPreferredSize(new Dimension(30,30));
//        userImg.setIcon(iconLogo);
//        sLayout.putConstraint(SpringLayout.WEST, userImg, 20, SpringLayout.WEST, cont);
//        sLayout.putConstraint(SpringLayout.NORTH, userImg, 30, SpringLayout.NORTH, cont);

        JLabel user = new JLabel("Username");
        sLayout.putConstraint(SpringLayout.WEST, user, 20, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, user, 30, SpringLayout.NORTH, cont);

        JTextField txtUserName = new JTextField();
        sLayout.putConstraint(SpringLayout.WEST, txtUserName, 20, SpringLayout.EAST, user);
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txtUserName, JTextField.CENTER, SpringLayout.VERTICAL_CENTER, user);
        txtUserName.setPreferredSize(new Dimension(300, 30));

        JLabel password = new JLabel("Password");
        sLayout.putConstraint(SpringLayout.WEST, password, 20, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, password, 50, SpringLayout.NORTH, user);

        JPasswordField txtPassword = new JPasswordField();
        sLayout.putConstraint(SpringLayout.WEST, txtPassword, 20, SpringLayout.EAST, password);
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txtPassword, JTextField.CENTER, SpringLayout.VERTICAL_CENTER, password);
        txtPassword.setPreferredSize(new Dimension(300, 30));

        JButton btnLogin = new JButton("Login");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnLogin, JButton.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, btnLogin, 50, SpringLayout.NORTH, password);
        btnLogin.setPreferredSize(new Dimension(100, 30));

        btnLogin.addActionListener((ActionEvent e1) -> {
             String pass = new String(txtPassword.getPassword());
             String finalPassword = cfg.encryptPassword(pass);
             String userName = txtUserName.getText().replace(" ", "");
             if(userName.equals("")){
                JOptionPane.showMessageDialog(mainFrame, "Please enter the username");
             }
             else if(passwordStr.equals(finalPassword)){
//                JOptionPane.showMessageDialog(mainFrame, "Succes");
                    First_Page objClass = new First_Page();
                    objClass.initComponents();
                 try {
                     conn.close();
                 } catch (SQLException ex) {
                     Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
                 }
            }
            else{
                JOptionPane.showMessageDialog(mainFrame, "Enter a valid Password");
            }
        });
        
        JButton btnForgot = new JButton("Forgot Password");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnForgot, JButton.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, btnForgot, 50, SpringLayout.NORTH, btnLogin);

        btnForgot.addActionListener((ActionEvent e1) -> {
            forgotPassword objForgot = new forgotPassword();
            objForgot.initComponents();             
        });
       
       
        cont.add(user);
//        cont.add(userImg);
        cont.add(txtUserName);
        cont.add(password);
        cont.add(txtPassword);
        cont.add(btnLogin);
        cont.add(btnForgot);
        cont.setPreferredSize(new Dimension(450, 220));
        mainFrame.add(cont);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }

}
