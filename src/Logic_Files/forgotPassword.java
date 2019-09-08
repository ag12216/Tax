/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic_Files;

import Common_Files.cfg;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 *
 * @author Dell
 */
public class forgotPassword {

    private static java.sql.Connection conn;

    void initComponents() {

        JFrame mainFrame = new JFrame("Forgot Password");
        SpringLayout sLayout = new SpringLayout();
        Container cont = new Container();

        JLabel lbl = new JLabel("Enter the password");
        sLayout.putConstraint(SpringLayout.WEST, lbl, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lbl, 50, SpringLayout.NORTH, cont);

        JPasswordField txtPassword = new JPasswordField();
        sLayout.putConstraint(SpringLayout.WEST, txtPassword, 20, SpringLayout.EAST, lbl);
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txtPassword, JTextField.CENTER, SpringLayout.VERTICAL_CENTER, lbl);
        txtPassword.setPreferredSize(new Dimension(200, 30));

        JButton btnSubmit = new JButton("Submit");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnSubmit, JButton.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, btnSubmit, 30, SpringLayout.NORTH, lbl);
        btnSubmit.setPreferredSize(new Dimension(100, 30));

        btnSubmit.addActionListener((ActionEvent e1) -> {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String url = "jdbc:sqlserver://localhost:1433;databaseName=Tax Invoice;user=batch_admin;password=admin";
                conn = DriverManager.getConnection(url);
                //Connection done
                String sqlCmd = "update Login_tbl set Password = '" + cfg.encryptPassword("admin") + "' where Username = 'admin';";
                java.sql.Statement stm = conn.createStatement();
                stm.execute(sqlCmd);
                Login_Page.passwordStr = cfg.encryptPassword("admin");
                JOptionPane.showMessageDialog(mainFrame, "Your password is changed successfully");
                conn.close();

            } catch (SQLException ex) {
                Logger.getLogger(Login_Page.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(forgotPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cont.setPreferredSize(new Dimension(400, 150));
        mainFrame.add(cont);
        cont.add(lbl);
        cont.add(txtPassword);
        cont.add(btnSubmit);
        cont.setLayout(sLayout);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

    }
}
