/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DesignUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.*;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Dell
 */
public class DesignUI {

    public Component make_Frame_Panel(JFrame frame, JPanel panel, String title) {
        frame = new JFrame(title);
        panel = new JPanel();
        frame.setSize(1000, 800);
        panel.setLayout(new FlowLayout());
        frame.setVisible(true);
        return frame.add(panel);
    }

    public Component makeLabel(JPanel panel, JLabel label, String title) {
        label = new JLabel(title);
        return panel.add(label);
    }

    public JFrame makeFrame(JFrame frame, String title) {
        frame = new JFrame(title);
        return frame;
    }

    public JPanel makePanel(JPanel panel) {
        panel = new JPanel();
        return panel;
    }

    public JLabel makeLabel(JLabel label, String title) {
        label = new JLabel(title);
        return label;
    }

    public void makeSeprator(JSeparator sep, SpringLayout sp, JPanel panel) {
        sp.putConstraint(SpringLayout.WEST, sep, 0, SpringLayout.WEST, panel);
        sp.putConstraint(SpringLayout.EAST, sep, 0, SpringLayout.EAST, panel);
        sep.setPreferredSize(new Dimension(sep.getWidth(), 1));
        sep.setBackground(Color.black);
    }

    //MARK PRINT
    public void printComponent(Component comp) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable(new ComponentPrintable(comp));

        if (!pj.printDialog()) {
            return;
        }
        try {
            pj.print();
        } catch (PrinterException ex) {
            System.out.println(ex);
        }
    }

    public class ComponentPrintable implements Printable {

        private Component comp;

        private ComponentPrintable(Component comp) {
            this.comp = comp;
        }

        @Override
        public int print(Graphics g, PageFormat pf, int pageNumber)
                throws PrinterException {
            // TODO Auto-generated method stub
            if (pageNumber > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            // Get the preferred size ofthe component...
            Dimension compSize = comp.getPreferredSize();
            // Make sure we size to the preferred size
            comp.setSize(compSize);
            // Get the the print size
            Dimension printSize = new Dimension();
            printSize.setSize(pf.getImageableWidth(), pf.getImageableHeight());

            // Calculate the scale factor
            double scaleFactor = getScaleFactorToFit(compSize, printSize);
            // Don't want to scale up, only want to scale down
            if (scaleFactor > 1d) {
                scaleFactor = 1d;
            }

            // Calcaulte the scaled size...
            double scaleWidth = compSize.width * scaleFactor;
            double scaleHeight = compSize.height * scaleFactor;

            // Create a clone of the graphics context.  This allows us to manipulate
            // the graphics context without begin worried about what effects
            // it might have once we're finished
            Graphics2D g2 = (Graphics2D) g.create();
            // Calculate the x/y position of the component, this will center
            // the result on the page if it can
            double x = ((pf.getImageableWidth() - scaleWidth) / 2d) + pf.getImageableX();
            double y = ((pf.getImageableHeight() - scaleHeight) / 2d) + pf.getImageableY();
            // Create a new AffineTransformation
            AffineTransform at = new AffineTransform();
            // Translate the offset to out "center" of page
            at.translate(x, y);
            // Set the scaling
            at.scale(scaleFactor, scaleFactor);
            // Apply the transformation
            g2.transform(at);
            // Print the component
            comp.printAll(g2);
            // Dispose of the graphics context, freeing up memory and discarding
            // our changes
            g2.dispose();

            comp.revalidate();
            return Printable.PAGE_EXISTS;
        }
    }

    public static double getScaleFactorToFit(Dimension original, Dimension toFit) {

        double dScale = 1d;

        if (original != null && toFit != null) {

            double dScaleWidth = getScaleFactor(original.width, toFit.width);
            double dScaleHeight = getScaleFactor(original.height, toFit.height);

            dScale = Math.min(dScaleHeight, dScaleWidth);

        }

        return dScale;

    }

    public static double getScaleFactor(int iMasterSize, int iTargetSize) {

        double dScale = 1;
        if (iMasterSize > iTargetSize) {

            dScale = (double) iTargetSize / (double) iMasterSize;

        } else {

            dScale = (double) iTargetSize / (double) iMasterSize;

        }

        return dScale;

    }
//END PRINT
    
    public TableCellEditor getCellEditor() {
        JTextField f = new JTextField();
        f.setBorder(BorderFactory.createLineBorder(Color.black));
        return new DefaultCellEditor(f);
    }
}
