/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager;

import java.util.*;
import java.util.Map.Entry;
import rdfmanager.entity.Paper;
import rdfmanager.util.RdfManager;
import test.by.bsu.rfe.clustering.text.algorithm.TestKMeansClustering;

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
            manager.importData("C:\\Users\\QMX-6core\\14_1.xml");
             String[][] termsDocsArray=new String[2999][];
            System.out.println(manager.size());
            List<String> allTerms = new ArrayList<String>(); //to hold all terms

            int count=0;
            for(Map.Entry<String, Paper> item : manager) {
                if(count>2998) break;
                Paper paper = item.getValue();
               // System.out.println(Arrays.toString(paper.getLabel()));
                String[] label = paper.getLabel();

                termsDocsArray[count]=label;
              //  System.out.println(Arrays.toString( termsDocsArray[count]));
                  count++;
            }
            TestKMeansClustering test=new TestKMeansClustering(termsDocsArray);
            test.testKMeans();
          //  System.out.println(Arrays.toString(termsDocsArray[0]));
            manager.exportData("C:\\Users\\QMX-6core\\14_3.xml");
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
    
}
