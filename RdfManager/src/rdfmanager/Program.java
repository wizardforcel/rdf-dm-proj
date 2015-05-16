/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import rdfmanager.entity.Paper;
import rdfmanager.util.RdfManager;

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
            RdfManager manager = new RdfManager();
            manager.importData("C:\\Users\\Wizard\\Desktop\\14_1.xml");
            /*for(Map.Entry<String, Paper> item : manager)
            {
                Paper paper = item.getValue();
                System.out.println(paper.getId() + "\n" + paper.getTitle());
            }*/
            manager.exportData("C:\\Users\\Wizard\\Desktop\\1.xml");
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
