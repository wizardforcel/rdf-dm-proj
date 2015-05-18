/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfsystem.data;

import weka.associations.Apriori;
import weka.classifiers.trees.J48;
import weka.clusterers.EM;
import weka.core.*;

/**
 *
 * @author Wizard
 */
public class DataMining 
{
    private static Instances transformData(RdfManager manager)
    {
        //TODO:
        return null;
    }
    
    public static J48 classify(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(ins);
        return tree;
    }
    
    public static EM cluster(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        EM cls = new EM();
        cls.setOptions(options);
        cls.buildClusterer(ins);
        return cls;
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
