/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfsystem.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import rdfsystem.entity.Author;
import rdfsystem.entity.Paper;
import weka.associations.Apriori;
import weka.associations.FPGrowth;
import weka.associations.FPGrowth.AssociationRule;
import weka.classifiers.trees.J48;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.EM;
import weka.clusterers.SimpleKMeans;
import weka.core.*;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NumericToNominal;
import weka.filters.unsupervised.attribute.StringToNominal;
import weka.filters.unsupervised.attribute.StringToWordVector;

/**
 *
 * @author Wizard
 */
public class DataMining 
{
    private static Instances transformData(RdfManager manager, boolean hasYear) 
            throws Exception
    {
        Set<String> words = getAllWords(manager);
        
        FastVector binary = new FastVector();
        binary.addElement("true");
        binary.addElement("false");
        
        FastVector attrs = new FastVector();
        if(hasYear)
        {
            Attribute yearAttr = new Attribute("year");
            attrs.addElement(yearAttr);
        }
        
        for(String word : words)
        {
            Attribute attr = new Attribute(word, binary);
            attrs.addElement(attr);
        }
        
        Instances ins = new Instances("paper", attrs, 0);
        
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            
            double[] row = new double[ins.numAttributes()];
            int start = 0;
            if(hasYear) 
            {
                row[0] = p.getYear();
                start++;
            }
            for(int i = start; i < row.length; i++)
                row[i] = ins.attribute(i).indexOfValue("false");
            
            for(String label : p.getLabel())
            {
                int index = ins.attribute("label_" + label).index();
                row[index] = ins.attribute(index).indexOfValue("true");
            }
            
            for(Author au : p.getList())
            {
                int index = ins.attribute("author_" + au.getId()).index();
                row[index] = ins.attribute(index).indexOfValue("true");
            }
            
            ins.add(new Instance(1.0, row));
        }
        
        if(hasYear)
        {
            NumericToNominal f1 = new NumericToNominal();
            f1.setInputFormat(ins);
            ins = Filter.useFilter(ins, f1);
        }
        
        return ins;
    }
    
    /*private static Instances transformData(RdfManager manager, boolean hasYear) 
            throws Exception
    {
        FastVector attrs = new FastVector();
        if(hasYear)
        {
            Attribute yearAttr = new Attribute("year");
            attrs.addElement(yearAttr);
        }
        
        Set<String> authorSet = getAuthorSet(manager);
        FastVector authors = new FastVector();
        for(String author : authorSet)
            authors.addElement(author);
        Attribute authorAttr = new Attribute("author", authors);
        attrs.addElement(authorAttr);
        
        Set<String> labelSet = getLabelSet(manager);
        FastVector labels = new FastVector();
        for(String label : labelSet)
            labels.addElement(label);
        Attribute labelAttr = new Attribute("label", labels);
        attrs.addElement(labelAttr);
        
        Instances ins = new Instances("paper", attrs, 0);
        
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            
            for(Author au : p.getList())
            {
                for(String label : p.getLabel())
                {
                    double[] row = new double[ins.numAttributes()];
                    if(hasYear)
                        row[ins.attribute("year").index()] 
                                = p.getYear();
                    row[ins.attribute("author").index()]
                            = ins.attribute("author")
                                 .indexOfValue(String.valueOf(au.getId()));
                    row[ins.attribute("label").index()]
                            = ins.attribute("label").indexOfValue(label);
                    ins.add(new Instance(1.0, row));
                }
            }
        }
        
        if(hasYear)
        {
            NumericToNominal f1 = new NumericToNominal();
            f1.setInputFormat(ins);
            ins = Filter.useFilter(ins, f1);
        }

        return ins;
    }*/
    
    public static String classify(RdfManager manager) 
           throws Exception
    {
        Instances ins = transformData(manager, true);
        ins.setClassIndex(ins.attribute("year").index());
        J48 tree = new J48();
        tree.buildClassifier(ins);
        return tree.graph();
    }
    
    public static String cluster(RdfManager manager) 
           throws Exception
    {
        Instances ins = transformData(manager, false);
        SimpleKMeans cls = new SimpleKMeans();
        String[] options = "-N 5".split(" ");
        cls.setOptions(options);
        cls.buildClusterer(ins);
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(cls);
        eval.evaluateClusterer(ins);
        return eval.clusterResultsToString();
    }
    
    public static String assoiate(RdfManager manager) 
           throws Exception
    {
        Instances ins = transformData(manager, false);
        
        FPGrowth ass = new FPGrowth();
        String[] options = "-T 0 -C 0.5 -M 0.1".split(" ");
        ass.setOptions(options);
        ass.buildAssociations(ins);
        List<AssociationRule> res = ass.getAssociationRules();
        for(AssociationRule rule : res)
            System.out.println(rule);

        return null;
    }
    
    private static Set<String> getLabelSet(RdfManager manager)
    {
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            for(String label : p.getLabel())
                set.add(label);
        }
        return set;
    }
    
    private static Set<String> getAuthorSet(RdfManager manager)
    {
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            for(Author au : p.getList())
                set.add("" + au.getId());
        }
        return set;
    }
    
    private static Set<String> getAllWords(RdfManager manager)
    {
        Set<String> set = new HashSet<>();
        for(Map.Entry<String, Paper> item : manager)
        {
            Paper p = item.getValue();
            for(Author au : p.getList())
                set.add("author_" + au.getId());
            for(String label : p.getLabel())
                set.add("label_" + label);
        }
        return set;
    }
}
