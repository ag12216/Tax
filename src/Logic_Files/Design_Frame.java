/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic_Files;

import Common_Files.EnglishNumberToWords;
import Common_Files.RowHeightCellRenderer;
import Common_Files.cfg;
import DesignUI.DesignUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

/**
 * @author Dell
 */
public class Design_Frame extends JFrame {

    //UI
    private static String companyName;
    private static JFrame topFrame;
    private static JLabel lblTitle;
    private static JLabel lblCompanyName;
    private static JLabel lblAddress;
    private static JLabel lblGSTIN;
    private static JLabel lblState;
    private static JLabel lblClientAddress;
    private static JLabel lblAmntWords;
    private static JLabel lblTaxAmtWords;
    static JFrame objInstance = null;
    private static String Buyeraddress;

    //Data
//    public static void main(String args[]) {
    Design_Frame(Map<String, String> custDetails, ArrayList<String> listOfCharge) {

//        System.out.println("Data is" + custDetails);
        objInstance = this;
        DesignUI obj = new DesignUI();
        companyName = "Ashirwad Shipping & Logistics Pvt Ltd";

        Buyeraddress = custDetails.get(cfg.custAddress);//"REGUS BUS CENTER DELHI REDFORT LEVEL 1 CAPITAL PARSVNATH TOWERS BHAIVEERSINGHMARG GOLE MKT N.DELHI";

        topFrame = obj.makeFrame(topFrame, "TAX INVOICE");
        topFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        SpringLayout sLayout = new SpringLayout();
        Container cont = new Container();//topFrame.getContentPane();
        cont.setLayout(sLayout);
        JScrollPane scroll = new JScrollPane(cont, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        topFrame.add(scroll);

        topFrame.setBackground(Color.white);
        scroll.setBackground(Color.white);

        cont.setPreferredSize(new Dimension(1000, 1500));

        lblTitle = obj.makeLabel(lblTitle, "Tax Invoice");

        lblTitle.setFont(new Font(lblTitle.getName(), Font.BOLD, 20));
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblTitle, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblTitle, 30, SpringLayout.NORTH, cont);

        ImageIcon iconLogo = new ImageIcon(Design_Frame.class.getResource("/Images/logo.png"));
        JLabel lblimg = new JLabel();
        lblimg.setIcon(iconLogo);

        lblCompanyName = obj.makeLabel(lblCompanyName, companyName);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblCompanyName, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblCompanyName, 20, SpringLayout.SOUTH, lblTitle);
        lblCompanyName.setFont(new Font(lblCompanyName.getName(), Font.BOLD, 20));

        lblAddress = obj.makeLabel(lblAddress, cfg.companyAddress);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblAddress, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblAddress, 10, SpringLayout.SOUTH, lblCompanyName);
        lblAddress.setFont(new Font(lblAddress.getName(), Font.BOLD, 12));

        lblGSTIN = obj.makeLabel(lblGSTIN, "GSTIN/UIN: "+cfg.companyGSTIN);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblGSTIN, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblGSTIN, 8, SpringLayout.SOUTH, lblAddress);
        lblGSTIN.setFont(new Font(lblGSTIN.getName(), Font.BOLD, 14));

        lblState = obj.makeLabel(lblState, "State Name : Maharashtra, Code : 27 , E-Mail : "+cfg.companyEmail);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblState, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, cont);
        sLayout.putConstraint(SpringLayout.NORTH, lblState, 8, SpringLayout.SOUTH, lblGSTIN);
        lblState.setFont(new Font(lblState.getName(), Font.BOLD, 12));

        sLayout.putConstraint(SpringLayout.EAST, lblimg, -20, SpringLayout.WEST, lblState);

        //For another view+
        SpringLayout sp = new SpringLayout();
        JPanel gridPanel = new JPanel(sp);
        sLayout.putConstraint(SpringLayout.WEST, gridPanel, 8, SpringLayout.WEST, cont);
        sLayout.putConstraint(SpringLayout.EAST, gridPanel, -8, SpringLayout.EAST, cont);
        sLayout.putConstraint(SpringLayout.SOUTH, gridPanel, -100, SpringLayout.SOUTH, cont);
        sLayout.putConstraint(SpringLayout.NORTH, gridPanel, 20, SpringLayout.NORTH, lblState);

        gridPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        gridPanel.setBackground(Color.white);

        JLabel lblBuyer = new JLabel("Buyer");
        sp.putConstraint(SpringLayout.NORTH, lblBuyer, 10, SpringLayout.NORTH, gridPanel);
        sp.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblBuyer, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
        lblBuyer.setAlignmentX(JLabel.CENTER_ALIGNMENT);

        JLabel lblClientCompanyName = new JLabel(custDetails.get(cfg.custName)); //"PM INTERNATIONAL INDIA PVT LTD"
        sp.putConstraint(SpringLayout.NORTH, lblClientCompanyName, 20, SpringLayout.NORTH, lblBuyer);
        sp.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblClientCompanyName, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
        lblClientCompanyName.setFont(new Font(lblClientCompanyName.getName(), Font.BOLD, 18));

        lblClientAddress = obj.makeLabel(lblClientAddress, Buyeraddress);
        sp.putConstraint(SpringLayout.NORTH, lblClientAddress, 8, SpringLayout.SOUTH, lblClientCompanyName);
        sp.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblClientAddress, JLabel.CENTER, SpringLayout.HORIZONTAL_CENTER, gridPanel);
        lblClientAddress.setFont(new Font(lblClientAddress.getName(), Font.BOLD, 12));

        JSeparator seprator = new JSeparator();
        obj.makeSeprator(seprator, sp, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, seprator, 5, SpringLayout.SOUTH, lblClientAddress);

        String intArray[][] = {{"<html><b>INVOICE NO</b></html>",
            "<html><b>ISSUE DATE</b></html>", "<html><b>SHIPPER NAME</b></html>", "<html><b>CARGO TYPE</b></html>"},
        {custDetails.get(cfg.inVoice), custDetails.get(cfg.issueDate), custDetails.get(cfg.shipperName), custDetails.get(cfg.cargoTbl)},
        {"<html><b>PARTY INV NO</b></html>", "<html><b>VESSEL/VOY</b></html>", "<html><b>ETD</b></html>", "<html><b>POL</b></html>"},
        {custDetails.get(cfg.partyInvoiceNo), custDetails.get(cfg.vesselVoy), custDetails.get(cfg.eid), custDetails.get(cfg.pol)},
        {"<html><b>AWB NUMBER</b></html>", "<html><b>POD</b></html>", "<html><b>SB NO</b></html>", "<html><b>NO. OF CTNS/WEIGHT</b></html>"},
        {custDetails.get(cfg.awbNumber),custDetails.get(cfg.podNo), custDetails.get(cfg.sbNo), custDetails.get(cfg.noOFCTNS)},
        {"<html><b>AIRLINE NAME</b></html>", "<html><b>FREIGHT TERM</b></html>", "", ""}, {custDetails.get(cfg.name), custDetails.get(cfg.term), "", ""}
        };

        String column[] = {"Invoice No", "Dated", "Delivery Note", "Mode/Terms of Payment"};

        JTable tbl = new JTable(intArray, column) {
            @Override
            public boolean isCellEditable(int row, int column) {
               return false;
            }
        };
        sp.putConstraint(SpringLayout.WEST, tbl, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.EAST, tbl, -20, SpringLayout.EAST, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, tbl, 10, SpringLayout.NORTH, seprator);
//       tbl.setPreferredSize(new Dimension(0,300));

        tbl.setBorder(BorderFactory.createLineBorder(Color.black));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < column.length; i++) {
            tbl.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        tbl.getTableHeader().setReorderingAllowed(false);
        tbl.setFocusable(false);
        tbl.setRowHeight(30);
        tbl.setCellSelectionEnabled(false);
        
        String column2[] = {"SI No.", "Particulars", "HSN/SAC", "Quantity", "Rate", "Per", "Amount"};
        String row2[][] = {{"1", "Delivery and Examination charges non gst", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"},
        {"1", "2", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"},
        {"1", "2", "3", "4", "5", "6", "7"}, {"1", "2", "3", "4", "5", "6", "7"}};
        
        String dynamicRow[][] = new String[listOfCharge.size()][column2.length];
        for(int i = 0;i<listOfCharge.size();i++){
            dynamicRow[i][0] = String.valueOf(i+1);
            dynamicRow[i][1] = listOfCharge.get(i);
            dynamicRow[i][2] = "3";
            dynamicRow[i][3] = "4";
            dynamicRow[i][4] = "4";
            dynamicRow[i][5] = "5";
            dynamicRow[i][6] = "6";
        }

        JTable tbl2 = new JTable(dynamicRow, column2) {
            @Override
            public boolean isCellEditable(int row, int column) {
                 if (column == 0 || column == 1){
                    return false;
                }else{
                    return true;
                }
            }
        };

        JScrollPane tblScroll = new JScrollPane(tbl2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.putConstraint(SpringLayout.WEST, tblScroll, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.EAST, tblScroll, -20, SpringLayout.EAST, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, tblScroll, 10, SpringLayout.SOUTH, tbl);


        tblScroll.setBorder(BorderFactory.createEmptyBorder());

        JTableHeader tblHeader = tbl2.getTableHeader();
        tblHeader.setBorder(BorderFactory.createLineBorder(Color.black));

        MatteBorder border = new MatteBorder(0, 1, 0, 0, Color.black);
        tbl2.setBorder(border);

//         tbl2.setBorder(BorderFactory.createLineBorder(Color.black));
        for (int i = 0; i < column2.length; i++) {
            tbl2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
//            tbl2.getColumnModel().getColumn(i).setCellEditor(obj.getCellEditor());
        }

        TableColumnModel tblCol = tbl2.getColumnModel();
        tblCol.getColumn(0).setPreferredWidth(20);
        tblCol.getColumn(1).setPreferredWidth(250);
        tblCol.getColumn(2).setPreferredWidth(100);
        tblCol.getColumn(3).setPreferredWidth(40);
        tblCol.getColumn(4).setPreferredWidth(40);
        tblCol.getColumn(5).setPreferredWidth(40);
        tblCol.getColumn(6).setPreferredWidth(150);

        RowHeightCellRenderer cellRend = new RowHeightCellRenderer();

        Dimension d = tbl2.getPreferredSize();
        tblScroll.setPreferredSize(new Dimension(d.width, dynamicRow.length * 30 + 26));

        tbl2.getTableHeader().setReorderingAllowed(false);
        tbl2.setCellSelectionEnabled(false);
        tbl2.setFocusable(false);
        tbl2.setRowHeight(30);

        JSeparator seprate = new JSeparator();
        obj.makeSeprator(seprate, sp, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, seprate, 5, SpringLayout.SOUTH, tblScroll);

        JLabel lblAmt = new JLabel("Amount Chargeable (in words) : ");
        sp.putConstraint(SpringLayout.WEST, lblAmt, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, lblAmt, 8, SpringLayout.SOUTH, seprate);

//        System.out.println("sd = "+EnglishNumberToWords.convert(112));        
        lblAmntWords = obj.makeLabel(lblAmntWords, "One thousand two hundred and fourty five");
//        sp.putConstraint(SpringLayout.WEST, lblAmntWords, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.WEST, lblAmntWords, 5, SpringLayout.EAST, lblAmt);
        sp.putConstraint(SpringLayout.NORTH, lblAmntWords, 5, SpringLayout.SOUTH, seprate);
        lblAmntWords.setFont(new Font(lblAmntWords.getName(), Font.BOLD, 16));

        JSeparator seprate2 = new JSeparator();
        obj.makeSeprator(seprate2, sp, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, seprate2, 5, SpringLayout.SOUTH, lblAmntWords);

        String column3[] = {"HSN/SAC", "Taxable", "Integrated Tax Rate", "Integrated Tax Rate(%)", "Total (Tax Amount)"};
        String row3[][] = {{"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"},
        {"1", "2", "3", "4", "5"}, {"1", "2", "3", "4", "5"}};

        JTable tbl3 = new JTable(row3, column3) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tbl3.getTableHeader().setReorderingAllowed(false);
        tbl3.setCellSelectionEnabled(false);
        tbl3.setFocusable(false);

        JScrollPane tblScroll2 = new JScrollPane(tbl3, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.putConstraint(SpringLayout.WEST, tblScroll2, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.EAST, tblScroll2, -20, SpringLayout.EAST, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, tblScroll2, 10, SpringLayout.SOUTH, seprate2);

        tblScroll2.setBorder(BorderFactory.createEmptyBorder());

        JTableHeader tblHeader2 = tbl3.getTableHeader();
        tblHeader2.setBorder(BorderFactory.createLineBorder(Color.black));

        MatteBorder border2 = new MatteBorder(0, 1, 0, 0, Color.black);
        tbl3.setBorder(border2);
        tbl3.setRowHeight(25);

        for (int i = 0; i < column3.length; i++) {
            tbl3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        TableColumnModel tblCol2 = tbl3.getColumnModel();
        tblCol2.getColumn(0).setPreferredWidth(240);
        tblCol2.getColumn(1).setPreferredWidth(50);
        tblCol2.getColumn(2).setPreferredWidth(40);
        tblCol2.getColumn(3).setPreferredWidth(40);
        tblCol2.getColumn(4).setPreferredWidth(40);

        Dimension d2 = tbl3.getPreferredSize();
        int size = row3.length * 25;
        tblScroll2.setPreferredSize(new Dimension(d2.width, size + 26)); //(ROW.length * 25)

        JSeparator taxSep = new JSeparator();
        obj.makeSeprator(taxSep, sp, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, taxSep, 5, SpringLayout.SOUTH, tblScroll2);

        JLabel lblTax = new JLabel("Tax Amount (in words) : ");
        sp.putConstraint(SpringLayout.WEST, lblTax, 20, SpringLayout.WEST, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, lblTax, 8, SpringLayout.SOUTH, taxSep);

        lblTaxAmtWords = obj.makeLabel(lblTaxAmtWords, "One thousand two hundred and fourty five");
        sp.putConstraint(SpringLayout.WEST, lblTaxAmtWords, 5, SpringLayout.EAST, lblTax);
        sp.putConstraint(SpringLayout.NORTH, lblTaxAmtWords, 5, SpringLayout.SOUTH, taxSep);
        lblTaxAmtWords.setFont(new Font(lblTaxAmtWords.getName(), Font.BOLD, 16));

        JSeparator taxSep2 = new JSeparator();
        obj.makeSeprator(taxSep2, sp, gridPanel);
        sp.putConstraint(SpringLayout.NORTH, taxSep2, 5, SpringLayout.SOUTH, lblTaxAmtWords);

        JLabel lblSign = new JLabel("Authorised Signature");
        sp.putConstraint(SpringLayout.EAST, lblSign, -20, SpringLayout.EAST, gridPanel);
//        sp.putConstraint(SpringLayout.NORTH, lblSign, 60, SpringLayout.NORTH, taxSep2);
        sp.putConstraint(SpringLayout.SOUTH, lblSign, -20, SpringLayout.SOUTH, gridPanel);
        lblSign.setFont(new Font(lblSign.getName(), Font.BOLD, 14));
        

        JButton btnPrint = new JButton("Print");
        sLayout.putConstraint(SpringLayout.NORTH, btnPrint, 10, SpringLayout.SOUTH, gridPanel);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnPrint, -60, SpringLayout.HORIZONTAL_CENTER, cont);
        btnPrint.setPreferredSize(new Dimension(100, 40));

        JButton btnCancel = new JButton("Cancel");
        sLayout.putConstraint(SpringLayout.NORTH, btnCancel, 10, SpringLayout.SOUTH, gridPanel);
        sLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, btnCancel, 60, SpringLayout.HORIZONTAL_CENTER, cont);
        btnCancel.setPreferredSize(new Dimension(100, 40));

        btnPrint.addActionListener((ActionEvent e1) -> {
            obj.printComponent(cont);
        });

        btnCancel.addActionListener((ActionEvent e1) -> {
//            System.out.println("Cancel");
            Design_Frame.objInstance = null;
            topFrame.setVisible(false);
        });

        cont.add(lblTitle);
        cont.add(lblimg);
        cont.add(lblCompanyName);
        cont.add(lblAddress);
        cont.add(lblGSTIN);
        cont.add(lblState);
        cont.add(btnPrint);
        cont.add(btnCancel);

        gridPanel.add(lblBuyer);
        gridPanel.add(lblClientCompanyName);
        gridPanel.add(lblClientAddress);
        gridPanel.add(seprator);
        gridPanel.add(tbl);
        gridPanel.add(seprate);
        gridPanel.add(tblScroll);
        gridPanel.add(lblAmntWords);
        gridPanel.add(seprate2);
        gridPanel.add(lblAmt);
        gridPanel.add(taxSep);
        gridPanel.add(taxSep2);
        gridPanel.add(lblTax);
        gridPanel.add(lblTaxAmtWords);
        gridPanel.add(tblScroll2);
        gridPanel.add(lblSign);

        cont.add(gridPanel);

//        topFrame.setMinimumSize(new Dimension(1000, 1200));
        topFrame.pack();
        topFrame.setVisible(true);

    }
}
