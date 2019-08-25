/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic_Files;

import Common_Files.cfg;
import DesignUI.DesignUI;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Dell
 */
public class First_Page implements ItemListener {
    
    private static String companyName;
    private static JLabel lblTitle;
    private static JLabel lblCompanyName;
    private static JLabel lblAddress;
    private static JLabel lblGSTIN;
    private static JLabel lblState;
    
    private static String chargesArr[];
    
    private static cfg objc = new cfg();
    private static JComboBox c1;
    private static JTable chargesTbl;
    private static JTable searchBox;
    private static Object[] row;
    private static DefaultTableModel model;
    private static ImageIcon checkBox;
    private static ArrayList<String> chargesName;
    private static java.sql.Connection conn;
    private static ArrayList<String> chargeName;
    private static DefaultTableModel tempmodelSearch;
//    static JComboBox charges;

    public static void main(String args[]) {
        
        DesignUI obj = new DesignUI();
        DocumentFilter filter = new DocumentFilter();
        DefaultTableModel modelSearch = new DefaultTableModel();
        modelSearch.setColumnIdentifiers(new Object[]{"Sr No.","Charges Name"});
        
        
        
        companyName = "Ashirwad Shipping & Logistics Pvt Ltd";
        
        First_Page fObj = new First_Page();
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://localhost:1433;databaseName=Tax Invoice;user=batch_admin;password=admin";
            conn = DriverManager.getConnection(url);
            //Connection done
            String sqlcmd = "select * from cargo_charges_Tbl;";
            java.sql.Statement st = conn.createStatement();
            java.sql.ResultSet rs = st.executeQuery(sqlcmd);
            chargeName = new ArrayList<>();
            while(rs.next()){
                String str = rs.getString("Charges Name");
                chargeName.add(str);
            }
//            conn.close();
            
        } catch (Exception e) {
            System.out.println("Error = " + e.getMessage());
        }
        
        JFrame topFrame = new JFrame("Tax Invoice2");
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SpringLayout sLayout = new SpringLayout();
        JPanel cont = new JPanel(sLayout);
        
        JScrollPane scroll = new JScrollPane(cont, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        topFrame.add(scroll);
        
        cont.setPreferredSize(new Dimension(1000, 1500));
        
        lblTitle = obj.makeLabel(lblTitle, "Tax Invoice");
        
        lblTitle.setFont(new Font(lblTitle.getName(), Font.BOLD, 20));
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblTitle, 30, SpringLayout.NORTH, cont);
        
        ImageIcon iconLogo = new ImageIcon(First_Page.class.getResource("/Images/logo.png"));
        JLabel lblimg = new JLabel();
        lblimg.setIcon(iconLogo);
        sLayout.putConstraint(SpringLayout.NORTH, lblimg, 10, SpringLayout.NORTH, cont);
        
        lblCompanyName = obj.makeLabel(lblCompanyName, companyName);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCompanyName, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCompanyName, 20, SpringLayout.SOUTH, lblTitle);
        lblCompanyName.setFont(new Font(lblCompanyName.getName(), Font.BOLD, 20));
        
        lblAddress = obj.makeLabel(lblAddress, "2A/108, Apna Ghar, Saibaba Nagar, Teli Gali Andheri (East), Mumbai");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAddress, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblAddress, 10, SpringLayout.SOUTH, lblCompanyName);
        lblAddress.setFont(new Font(lblAddress.getName(), Font.BOLD, 12));
        
        lblGSTIN = obj.makeLabel(lblGSTIN, "GSTIN/UIN: 27AAJCA8966F2Z7");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblGSTIN, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblGSTIN, 8, SpringLayout.SOUTH, lblAddress);
        lblGSTIN.setFont(new Font(lblGSTIN.getName(), Font.BOLD, 14));
        
        lblState = obj.makeLabel(lblState, "State Name : Maharashtra, Code : 27 , E-Mail : info@ashirwadshipping.com");
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblState, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblState, 8, SpringLayout.SOUTH, lblGSTIN);
        lblState.setFont(new Font(lblState.getName(), Font.BOLD, 12));
        
        sLayout.putConstraint(SpringLayout.EAST, lblimg, -20, SpringLayout.WEST, lblState);
//Customer's Details
        JLabel lblCustName = new JLabel("Enter the Customer Name : ");
        sLayout.putConstraint(SpringLayout.WEST, lblCustName, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCustName, 30, SpringLayout.SOUTH, lblState);
        
        JTextField txtCustName = new JTextField();
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txtCustName, JTextField.CENTER, SpringLayout.VERTICAL_CENTER, lblCustName);
        sLayout.putConstraint(SpringLayout.WEST, txtCustName, 36, SpringLayout.EAST, lblCustName);
        txtCustName.setPreferredSize(new Dimension(500, 30));
        
        JLabel lblCustAddress = new JLabel("Enter the Customer Address : ");
        sLayout.putConstraint(SpringLayout.WEST, lblCustAddress, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCustAddress, 30, SpringLayout.SOUTH, txtCustName);
        
        JTextArea txtAddress = new JTextArea();
        
        JScrollPane txtScroll = new JScrollPane(txtAddress, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sLayout.putConstraint(SpringLayout.WEST, txtScroll, 20, SpringLayout.EAST, lblCustAddress);
        sLayout.putConstraint(SpringLayout.NORTH, txtScroll, 30, SpringLayout.SOUTH, lblCustName);
        
        txtScroll.setPreferredSize(new Dimension(500, 50));
        
        String intArray[][] = {{"<html><b>INVOICE NO</b></html>",
            "<html><b>ISSUE DATE</b></html>", "<html><b>SHIPPER NAME</b></html>", "<html><b>CARGO TYPE</b></html>"},
        {"220/19-20", "1-JUN-2019", "MR. SHUBHAM GUPTA", "AIR SHIPMENT"},
        {"<html><b>PARTY INV NO</b></html>", "<html><b>VESSEL/VOY</b></html>", "<html><b>ETD</b></html>", "<html><b>POL</b></html>"},
        {"EXP/00007 DT.06.05.19", "BY AIR", "22.05.19", "SAHAR CARGO"},
        {"<html><b>AWB NUMBER</b></html>", "<html><b>POD</b></html>", "<html><b>SB NO</b></html>", "<html><b>NO. OF CTNS/WEIGHT</b></html>"},
        {"071-34335140 DT 22.05.19", "LAGOS", "4346690 DT 22.05.19", "21 CTNS /302.00 KGS"},
        {"<html><b>AIRLINE NAME</b></html>", "<html><b>FREIGHT TERM</b></html>", "", ""}, {"SAHAR AIR CARGO", "PREPAID", "", ""}
        };
        
        String column[] = {"Invoice No", "Dated", "Delivery Note", "Mode/Terms of Payment"};
        
        JTable userInput = new JTable(intArray, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //0 2 4 6
                if (row == 0 || row == 2 || row == 4 || row == 6) {
                    return false;
                } else {
                    return true;
                }
                
            }
        };
        sLayout.putConstraint(SpringLayout.WEST, userInput, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.EAST, userInput, -30, SpringLayout.EAST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, userInput, 50, SpringLayout.SOUTH, lblCustAddress);
        userInput.setCellSelectionEnabled(false);
        userInput.setRowHeight(35);
        userInput.setFocusable(false);
        userInput.getTableHeader().setReorderingAllowed(false);
        MatteBorder border2 = new MatteBorder(1, 1, 1, 1, Color.black);
        userInput.setBorder(border2);
        
        JLabel lblCargoType = new JLabel("Select the Cargo Type: ");
        sLayout.putConstraint(SpringLayout.WEST, lblCargoType, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCargoType, 50, SpringLayout.SOUTH, userInput);
        
        c1 = new JComboBox(cfg.cargoType);
        sLayout.putConstraint(SpringLayout.WEST, c1, 8, SpringLayout.EAST, lblCargoType);
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, c1, 0, SpringLayout.VERTICAL_CENTER, lblCargoType);
        c1.addItemListener(fObj);
        
        JLabel lblCharges = new JLabel("Select the charges: ");
        sLayout.putConstraint(SpringLayout.WEST, lblCharges, 30, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCharges, 30, SpringLayout.SOUTH, lblCargoType);
        
        objc.makeDictionary();
        
        JLabel lblChargesName = new JLabel("Search Charges");
        sLayout.putConstraint(SpringLayout.NORTH, lblChargesName, 30, SpringLayout.SOUTH, lblCharges);
        sLayout.putConstraint(SpringLayout.WEST, lblChargesName, 30, SpringLayout.WEST, cont);
        
        JTextField txtSearch = new JTextField();
        sLayout.putConstraint(SpringLayout.VERTICAL_CENTER, txtSearch, JTextField.CENTER, SpringLayout.VERTICAL_CENTER, lblChargesName);
        sLayout.putConstraint(SpringLayout.WEST, txtSearch, 20, SpringLayout.EAST, lblChargesName);
        txtSearch.setPreferredSize(new Dimension(500, 30));
        UpperCaseDocument ucd = new UpperCaseDocument();
        txtSearch.setDocument(ucd);
        
        txtSearch.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char letter = Character.toUpperCase(e.getKeyChar());
//                System.out.println("" + txtSearch.getText().toUpperCase() + letter);
                if (txtSearch.getText().length() >= 1) {
                    try {
                        String sqlcmd = "select * from cargo_charges_Tbl where [Charges Name] LIKE '" + txtSearch.getText().toUpperCase() + letter + "%';";
                        // System.out.println(""+sqlcmd);
                        java.sql.Statement st = conn.createStatement();
                        java.sql.ResultSet rs = st.executeQuery(sqlcmd);
                        chargesName = new ArrayList<>();
                        
                        tempmodelSearch = new DefaultTableModel();
                        tempmodelSearch.setColumnIdentifiers(new Object[]{"Sr No.","Charges Name"});
                        while (rs.next()) {
                            String str = rs.getString("Charges Name");
                            int i = rs.getInt("Index");
                            chargesName.add(str);
                            Object chargeRow[] = new Object[2];
                            chargeRow[0] = i;
                            chargeRow[1] = str;
                            tempmodelSearch.addRow(chargeRow);
                           
                        }
                       
                        if(!chargesName.isEmpty()){
                            searchBox.setModel(tempmodelSearch);
                            searchBox.repaint();
                        }
                        else{
                            searchBox.setModel(modelSearch);
                            searchBox.repaint();
                        }
//                        System.out.println("Output" + chargesName);
//            conn.close();
                    } catch (SQLException ex) {
                        Logger.getLogger(First_Page.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
        });
        
        chargesArr = objc.cargoDic.get(c1.getSelectedItem());
        
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"Select", "Sr No", "Name"});
        
        row = new Object[chargesArr.length];
        for (int i = 0; i < chargesArr.length; i++) {
            row[0] = false;
            row[1] = i + 1;
            row[2] = chargesArr[i];
            model.addRow(row);
        }

        for(int i = 0;i<chargeName.size();i++){
            Object chargeRow[] = new Object[2];
            chargeRow[0] = i+1;
            chargeRow[1] = chargeName.get(i);
            modelSearch.addRow(chargeRow);
        }
//        System.out.println(""+modelSearch);
        
        searchBox = new JTable(){
         @Override
            public boolean isCellEditable(int row, int column) {
               return false; 
            }
        };
        
        searchBox.setModel(modelSearch);

        JScrollPane searchScroll = new JScrollPane();
        sLayout.putConstraint(SpringLayout.WEST, searchScroll, 20, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.EAST, searchScroll, -20, SpringLayout.EAST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, searchScroll, 20, SpringLayout.SOUTH, lblChargesName);
        searchScroll.setPreferredSize(new Dimension(0, 300));
        searchBox.setCellSelectionEnabled(false);
        searchScroll.setBorder(BorderFactory.createEmptyBorder());
        searchScroll.setViewportView(searchBox);
        searchBox.setRowHeight(30);
        
        MatteBorder sborder = new MatteBorder(0, 1, 0, 0, Color.black);
        searchBox.setBorder(sborder);
        searchBox.getTableHeader().setReorderingAllowed(false);
        searchBox.setFocusable(false);
        
        chargesTbl = new JTable() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 0;
            }
            
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Boolean.class;
                    case 1:
                        return String.class;
                    default:
                        return String.class;
                }
            }
            
        };
        
        model.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                for (int i = 0; i < chargesTbl.getModel().getRowCount(); i++) {
                    if ((Boolean) chargesTbl.getModel().getValueAt(i, 0)) {
//                        System.out.println("" + chargesTbl.getSelectedRow());
//                        System.out.println("" + chargesTbl.getModel());
                        break;
                    }
                }
            }
            
        });
        
        JScrollPane scrollTbl = new JScrollPane();
        
        sLayout.putConstraint(SpringLayout.WEST, scrollTbl, 20, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.EAST, scrollTbl, -20, SpringLayout.EAST, cont);
        sLayout.putConstraint(SpringLayout.NORTH, scrollTbl, 20, SpringLayout.SOUTH, searchScroll);
        scrollTbl.setPreferredSize(new Dimension(0, 300));//(chargesArr.length + 1) * 30)
        chargesTbl.setModel(model);
        chargesTbl.setCellSelectionEnabled(false);
        scrollTbl.setBorder(BorderFactory.createEmptyBorder());
        scrollTbl.setViewportView(chargesTbl);
        
        MatteBorder border = new MatteBorder(0, 1, 0, 0, Color.black);
        chargesTbl.setBorder(border);
        
        TableColumnModel tblCol = chargesTbl.getColumnModel();
        tblCol.getColumn(0).setPreferredWidth(30);
        tblCol.getColumn(1).setPreferredWidth(30);
        tblCol.getColumn(2).setPreferredWidth(800);
        chargesTbl.getTableHeader().setReorderingAllowed(false);
        chargesTbl.setFocusable(false);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < model.getColumnCount(); i++) {
            if (i == 1) {
                chargesTbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }
            
        }
        
        chargesTbl.setRowHeight(30);
        cont.add(lblTitle);
        cont.add(lblimg);
        cont.add(lblCompanyName);
        cont.add(lblAddress);
        cont.add(lblGSTIN);
        cont.add(lblState);
        cont.add(c1);
        cont.add(lblCustName);
        cont.add(txtCustName);
        cont.add(lblCargoType);
        cont.add(lblCharges);
        cont.add(lblCustAddress);
        cont.add(txtScroll);
        cont.add(lblChargesName);
        cont.add(txtSearch);
        cont.add(scrollTbl);
        cont.add(userInput);
        cont.add(searchScroll);
        
        topFrame.pack();
        topFrame.setVisible(true);
        
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == c1) {
//            charges.removeAllItems();

            chargesArr = objc.cargoDic.get(e.getItem());
//            for(String val : chargesArr){
//                   charges.addItem(val);
//            }

            model = new DefaultTableModel();
            model.setColumnIdentifiers(new Object[]{"Select", "Sr No", "Name"});
            
            row = new Object[chargesArr.length];
            for (int i = 0; i < chargesArr.length; i++) {
                row[0] = false;
                row[1] = i + 1;
                row[2] = chargesArr[i];
                model.addRow(row);
            }
            chargesTbl.setModel(model);
            
        }
        
    }
}

class UpperCaseDocument extends PlainDocument {

    private boolean upperCase = true;
    
    public void setUpperCase(boolean flag) {
        upperCase = flag;
    }
    
    public void insertString(int offset, String str, AttributeSet attSet)
            throws BadLocationException {
        if (upperCase) {
            str = str.toUpperCase();
        }
        super.insertString(offset, str, attSet);
    }
    
}


class charges{
    int index;
    String names;
    charges(int index,String names){
        this.index = index;
        this.names = names;
    }
}