/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import rdfsystem.MainFrame;
import rdfsystem.data.RdfManager;
import rdfsystem.entity.Paper;

/**
 *
 * @author Wizard
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        try
        {
            String style = UIManager.getSystemLookAndFeelClassName();
            UIManager.setLookAndFeel(style);
            
            new MainFrame().setVisible(true);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            ex.printStackTrace();
        }
    }
    
}
