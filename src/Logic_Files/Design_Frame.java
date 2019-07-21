/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic_Files;
import DesignUI.DesignUI;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
/**
 *
 * @author Dell
 */
public class Design_Frame {
    //UI
    private static String companyName;
    private static JFrame topFrame;
    private static JLabel lblTitle;
    private static JLabel lblCompanyName;
    private static JLabel lblAddress;
    private static JLabel lblGSTIN;
    private static JLabel lblState;
    private static JLabel lblClientAddress;
    
    private static JLabel lblShipAddress;
    private static JLabel lblInvoice;
    private static JLabel lblInvoiceNO;
    
    private static JLabel lblGSTINNO;
    private static JLabel lblOrderIDValue;
    
    private static String Buyeraddress;
    
    //Data
   
  
    public static void main(String args[]){
       
       DesignUI obj = new DesignUI();
       companyName = "Ashirwad Shipping & Logistics Pvt Ltd";
       
       Buyeraddress = "REGUS BUS CENTER DELHI REDFORT LEVEL 1 CAPITAL PARSVNATH TOWERS BHAIVEERSINGHMARG GOLE MKT N.DELHI";
       
       topFrame = obj.makeFrame(topFrame, "TAX INVOICE");
       topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
      
       SpringLayout sLayout = new SpringLayout();
       Container cont = topFrame.getContentPane();

       cont.setLayout(sLayout);
       
       lblTitle = obj.makeLabel(lblTitle, "Tax Invoice");
       
       lblTitle.setFont(new Font(lblTitle.getName(), Font.BOLD, 20));
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
       sLayout.putConstraint(SpringLayout.NORTH, lblTitle, 30, SpringLayout.NORTH, cont);
   
       lblCompanyName = obj.makeLabel(lblCompanyName, companyName);
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCompanyName, JLabel.CENTER,SpringLayout.HORIZONTAL_CENTER, cont);
       sLayout.putConstraint(SpringLayout.NORTH, lblCompanyName, 20,SpringLayout.SOUTH, lblTitle);
       lblCompanyName.setFont(new Font(lblCompanyName.getName(), Font.BOLD, 20));
       
       lblAddress = obj.makeLabel(lblAddress, "2A/108, Apna Ghar, Saibaba Nagar, Teli Gali Andheri (East), Mumbai");
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAddress, JLabel.CENTER,SpringLayout.HORIZONTAL_CENTER, cont);
       sLayout.putConstraint(SpringLayout.NORTH, lblAddress, 10,SpringLayout.SOUTH, lblCompanyName);
       lblAddress.setFont(new Font(lblAddress.getName(), Font.BOLD, 12));
       
       lblGSTIN = obj.makeLabel(lblGSTIN, "GSTIN/UIN: 27AAJCA8966F2Z7");
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblGSTIN, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
       sLayout.putConstraint(SpringLayout.NORTH, lblGSTIN, 8, SpringLayout.SOUTH, lblAddress);
       lblGSTIN.setFont(new Font(lblGSTIN.getName(), Font.BOLD, 14));
       
       lblState = obj.makeLabel(lblState, "State Name : Maharashtra, Code : 27 , E-Mail : info@ashirwadshipping.com");
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblState, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
       sLayout.putConstraint(SpringLayout.NORTH, lblState, 8, SpringLayout.SOUTH, lblGSTIN);
       lblState.setFont(new Font(lblState.getName(), Font.BOLD, 12));
      
       //For another view+
       JPanel gridPanel = new JPanel();
       sLayout.putConstraint(SpringLayout.WEST, gridPanel, 8, SpringLayout.WEST, cont);
       sLayout.putConstraint(SpringLayout.EAST, gridPanel, -8, SpringLayout.EAST, cont);
       sLayout.putConstraint(SpringLayout.SOUTH, gridPanel, -10, SpringLayout.SOUTH, cont);
       sLayout.putConstraint(SpringLayout.NORTH, gridPanel, 20, SpringLayout.NORTH, lblState);
      
       gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
       
       JLabel lblBuyer = new JLabel("Buyer");
       sLayout.putConstraint(SpringLayout.NORTH, lblBuyer, 10, SpringLayout.NORTH, gridPanel);
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblBuyer, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
       lblBuyer.setAlignmentX(JLabel.CENTER_ALIGNMENT);
       
       JLabel lblClientCompanyName = new JLabel("PM INTERNATIONAL INDIA PVT LTD");
       sLayout.putConstraint(SpringLayout.NORTH, lblClientCompanyName, 20, SpringLayout.NORTH, lblBuyer);
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblClientCompanyName, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
       lblClientCompanyName.setFont(new Font(lblClientCompanyName.getName(), Font.BOLD, 18));
       
       lblClientAddress = obj.makeLabel(lblClientAddress, Buyeraddress);
       sLayout.putConstraint(SpringLayout.NORTH, lblClientAddress, 8, SpringLayout.SOUTH, lblClientCompanyName);
       sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblClientAddress, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
       lblClientAddress.setFont(new Font(lblClientAddress.getName(), Font.BOLD, 12));
       
       JSeparator seprator = new JSeparator();
       sLayout.putConstraint(SpringLayout.WEST, seprator, 0, SpringLayout.WEST, gridPanel);
       sLayout.putConstraint(SpringLayout.EAST, seprator, 0, SpringLayout.EAST, gridPanel);
       sLayout.putConstraint(SpringLayout.NORTH, seprator, 5, SpringLayout.SOUTH, lblClientAddress);
       seprator.setPreferredSize(new Dimension(seprator.getWidth(),1));
       seprator.setBackground(Color.black);
       
       String intArray[][] = {{"1","2","",""},{"4","5","",""},{"4","5","",""},{"4","5","",""}};
       
      
       String column[] = {"Invoice No","Dated","Delivery Note","Mode/Terms of Payment"};
       
       JTable tbl = new JTable(intArray,column);
       tbl.getTableHeader().setReorderingAllowed(false);
       tbl.setCellSelectionEnabled(false);
       tbl.setFocusable(false);
      
       JScrollPane scrollPane = new JScrollPane(tbl);
       sLayout.putConstraint(SpringLayout.WEST, scrollPane, 20, SpringLayout.WEST, gridPanel);
       sLayout.putConstraint(SpringLayout.EAST, scrollPane, -20, SpringLayout.EAST, gridPanel);
       sLayout.putConstraint(SpringLayout.NORTH, scrollPane, 10, SpringLayout.NORTH, seprator);
       scrollPane.setPreferredSize(new Dimension(0,300));

       cont.add(lblTitle);
       cont.add(lblCompanyName);
       cont.add(lblAddress);
       cont.add(lblGSTIN);
       cont.add(lblState);

       cont.add(seprator);
       cont.add(lblClientCompanyName);
       cont.add(lblBuyer);
       cont.add(lblClientAddress);
       cont.add(scrollPane);
       
       cont.add(gridPanel);
       
       
       
       topFrame.setMinimumSize(new Dimension(1000,800));
       topFrame.pack();
       topFrame.setVisible(true);
        
    }
}
