/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfsystem.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import rdfsystem.entity.Author;
import rdfsystem.entity.Paper;
import weka.associations.Apriori;
import weka.classifiers.trees.J48;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.core.*;

/**
 *
 * @author Wizard
 */
public class DataMining 
{
    /*private static Instances transformData(RdfManager manager)
    {
        Attribute yearAttr = new Attribute("year");
        Attribute labelAttr = new Attribute("label", (FastVector)null);
        Attribute authorAttr = new Attribute("author", (FastVector)null);
        
        FastVector attrs = new FastVector();
        attrs.addElement(yearAttr);
        attrs.addElement(labelAttr);
        attrs.addElement(authorAttr);
        
        Instances ins = new Instances("paper", attrs, 0);
        
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            for(Author au : p.getList())
            {
                String[] labels = p.getLabel();
                int len = labels.length;
                if(len > 5) len = 5;
                for(int i = 0; i < len; i++)
                {
                    String label = labels[i];
                    
                    double[] row = new double[3];
                    row[0] = p.getYear();
                    row[1] = ins.attribute(1).addStringValue(label);
                    row[2] = ins.attribute(2).addStringValue(au.getName());
                    ins.add(new Instance(1.0, row));
                }
            }
        }
        return ins;
    }*/
    
    private static Instances transformData(RdfManager manager)
    {
        return null;
    }
    
    public static String classify(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(ins);
        return tree.graph();
    }
    
    public static String cluster(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        EM cls = new EM();
        cls.setOptions(options);
        cls.buildClusterer(ins);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cls);
        eval.evaluateClusterer(ins);
        return eval.clusterResultsToString();
    }
    
    public static Apriori assoiate(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        Apriori ass = new Apriori();
        ass.setOptions(options);
        ass.buildAssociations(ins);
        return ass;
    }
    
}
