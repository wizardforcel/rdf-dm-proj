/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import rdfsystem.entity.Paper;
import rdfsystem.data.RdfManager;

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
            //manager.importData("C:\\Users\\Wizard\\Desktop\\14_1.xml");
            manager.load();
            for(Map.Entry<String, Paper> item : manager)
            {
                Paper paper = item.getValue();
                System.out.println(paper.getId() + "\n" + paper.getTitle());
            }
            
            //manager.exportData("C:\\Users\\Wizard\\Desktop\\1.xml");
            //manager.save();
            
            /*Map<String, Integer> map = manager.accountAuthor();
            for(Entry<String, Integer> item : map.entrySet() )
            {
                System.out.println(item.getKey() + " " + item.getValue().toString());
            }*/
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
    
}
