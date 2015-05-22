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
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author Wizard
 */
public class DataMining 
{
    private static Instances transformData(RdfManager manager) throws Exception
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
            
            StringBuffer labelSb = new StringBuffer();
            for(String label : p.getLabel())
                labelSb.append(label).append(" ");
            if(labelSb.length() != 0)
                labelSb.setLength(labelSb.length() - 1);
            
            StringBuffer authorSb = new StringBuffer();
            for(Author au : p.getList())
                authorSb.append(au.getName()).append(" ");
            if(authorSb.length() != 0)
                authorSb.setLength(authorSb.length() - 1);
            
            double[] row = new double[3];
            row[0] = p.getYear();
            row[1] = ins.attribute(1).addStringValue(labelSb.toString());
            row[2] = ins.attribute(2).addStringValue(authorSb.toString());
            ins.add(new Instance(1.0, row));
        }
        
        NumericToNominal f1 = new NumericToNominal();
        f1.setInputFormat(ins);
        ins = Filter.useFilter(ins, f1);
        
        StringToWordVector f2 = new StringToWordVector();
        f2.setInputFormat(ins);
        ins =  Filter.useFilter(ins, f2);
        
        return ins;
    }
    
    public static String classify(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        ins.setClassIndex(ins.attribute("year").index());
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(ins);
        return tree.graph();
    }
    
    public static String cluster(RdfManager manager, String[] options) 
           throws Exception
    {
        Instances ins = transformData(manager);
        SimpleKMeans cls = new SimpleKMeans();
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
