/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import rdfmanager.entity.Author;
import rdfmanager.entity.Paper;

/**
 *
 * @author Wizard
 */
public class RdfManager 
       implements Iterable<Map.Entry<String, Paper>>
{
    private HashMap<Integer, Author> authorMap
            = new HashMap<>();
    private HashMap<String, Paper> paperMap
            = new HashMap<>();
    
    public RdfManager() {}
    
    public void importData(String rdfFile) 
           throws ParserConfigurationException, SAXException, IOException
    {
        File f = new File(rdfFile);
        Document doc = DocumentBuilderFactory.newInstance()
                       .newDocumentBuilder().parse(f);
        Element root = doc.getDocumentElement();
        NodeList children = root.getElementsByTagName("rdf:Description");
        for(int i = 0; i < children.getLength(); i++)
        {
            Element desc = (Element)children.item(i);
            NodeList li = desc.getElementsByTagName("rdf:type");
            if(li.getLength() == 0)
                continue;
            Element typeEle = (Element)li.item(0);
            String type = typeEle.getAttribute("rdf:resource");
            if(type.endsWith("Person"))
                handleAuthorNode(desc);
            else if(type.endsWith("Seq"))
                handleAuthorListNode(desc);
            else if(type.endsWith("Article"))
                handlePaperNode(desc);
        }
    }

    private void handleAuthorNode(Element desc)
    {
        String link = desc.getAttribute("rdf:about");
        String[] tmp = link.split("/");
        int id = Integer.parseInt(tmp[tmp.length - 1]);
        NodeList li = desc.getElementsByTagName("foaf:name");
        if(li.getLength() == 0)
            return;
        Element nameEle = (Element) li.item(0);
        Text nameText = (Text)nameEle.getFirstChild();
        String name = nameText.getData().trim();
        
        Author au = new Author(id, name);
        authorMap.put(id, au);
    }
    
    private void handleAuthorListNode(Element desc)
    {
        String link = desc.getAttribute("rdf:about");
        String[] tmp = link.split("/");
        String paperId = tmp[tmp.length - 2];
        
        Paper paper = paperMap.get(paperId);
        if(paper == null)
        {
            paper = new Paper();
            paper.setId(paperId);
            paperMap.put(paperId, paper);
        }
        
        NodeList li = desc.getChildNodes();
        for(int i = 0; i < li.getLength(); i++)
        {
            Node n = li.item(i);
            if(!(n instanceof Element))
                continue;
            Element ele = (Element)n;
            String tagName = ele.getTagName();
            if(!tagName.matches("rdf:_\\d+"))
                continue;
            
            String authorLink = ele.getAttribute("rdf:resource");
            tmp = authorLink.split("/");
            int authorId = Integer.parseInt(tmp[tmp.length - 1]);
            Author au = authorMap.get(authorId);
            paper.getList().add(au);
        }
    }
    
    private void handlePaperNode(Element desc)
    {
        String link = desc.getAttribute("rdf:about");
        String[] tmp = link.split("/");
        String paperId = tmp[tmp.length - 1];
        
        Paper paper = paperMap.get(paperId);
        if(paper == null)
        {
            paper = new Paper();
            paper.setId(paperId);
            paperMap.put(paperId, paper);
        }
        
        NodeList li = desc.getElementsByTagName("swrc:year");
        if(li.getLength() == 0)
            return;
        Element yearEle = (Element)li.item(0);
        Text yearText = (Text)yearEle.getFirstChild();
        int year = Integer.parseInt(yearText.getData().trim());
        
        li = desc.getElementsByTagName("dc:title");
        if(li.getLength() == 0)
            return;
        Element titleEle = (Element)li.item(0);
        Text titleText = (Text)titleEle.getFirstChild();
        String title = titleText.getData().trim();
        
        li = desc.getElementsByTagName("swrc:journal");
        if(li.getLength() == 0)
            return;
        Element journalEle = (Element)li.item(0);
        Text journalText = (Text)journalEle.getFirstChild();
        String journal = journalText.getData().trim();
        
        li = desc.getElementsByTagName("rdfs:label");
        if(li.getLength() == 0)
            return;
        Element labelEle = (Element)li.item(0);
        Text labelText = (Text)labelEle.getFirstChild();
        String label = labelText.getData().trim();
        
        paper.setYear(year);
        paper.setTitle(title);
        paper.setJournal(journal);
        if(!label.equals(title))
            paper.setLabel(label.split(";"));
    }
    
    public void exportData(String rdfFile) 
           throws FileNotFoundException, IOException
    {
        StreamWriter sw = new StreamWriter(rdfFile);
        HashSet<Integer> authorSet = new HashSet<>();
        HashSet<String> auListSet = new HashSet<>();
        
        //header
        sw.writeln("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\"");
        sw.writeln("    xmlns:dc=\"http://purl.org/dc/elements/1.1/\"");
        sw.writeln("    xmlns:swrc=\"http://swrc.ontoware.org/ontology#\"");
        sw.writeln("    xmlns:bibo=\"http://purl.org/ontology/bibo/\"");
        sw.writeln("    xmlns:rdfs=\"http://www.w3.org/2000/01/rdf-schema#\"");
        sw.writeln("    xmlns:foaf=\"http://xmlns.com/foaf/0.1/\" >");
        
        for(Map.Entry<String, Paper> item : paperMap.entrySet())
        {
            Paper paper = item.getValue();
            ArrayList<Author> authorList = paper.getList();
            
            //author part
            for(Author au : authorList)
            {
                if(authorSet.contains(au.getId()))
                    continue;
                sw.writeln("  <rdf:Description rdf:about=\"http://localhost/author/" + 
                         String.valueOf(au.getId()) + "\">");
                sw.writeln("    <foaf:name>" + au.getName() + "</foaf:name>");
                sw.writeln("    <foaf:label>" + au.getName() + "</foaf:label>");
                sw.writeln("    <rdf:type rdf:resource=\"http://xmlns.com/foaf/0.1/Person\" />");
                sw.writeln("  </rdf:Description>");
                authorSet.add(au.getId());
            }
            
            //au list part
            if(!auListSet.contains(paper.getId()))
            {
                sw.writeln("  <rdf:Description rdf:about=\"http://localhost/journals/" + 
                         paper.getJournal() + "/" + paper.getId() + "/authorlist\">");
                int i = 1;
                for(Author au : authorList)
                {
                    sw.writeln("    <rdf:_" + String.valueOf(i) + 
                             " rdf:resource=\"http://localhost/author/" + 
                             String.valueOf(au.getId()) + "\" />");
                    i++;
                }
                sw.writeln("    <rdf:type rdf:resource=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#Seq\" />");
                sw.writeln("  </rdf:Description>");
                auListSet.add(paper.getId())
            }
            
            //paper part
            sw.writeln("  <rdf:Description rdf:about=\"http://localhost/journals/" + 
                     paper.getJournal() + "/" + paper.getId() + "\">");
            sw.writeln("    <swrc:year>" + paper.getYear() + "</swrc:year>");
            String labelTxt = "";
            for(String s : paper.getLabel())
                labelTxt += s;
            sw.writeln("    <rdfs:label>" + labelTxt + "</rdfs:label>");
            sw.writeln("    <rdfs:title>" + paper.getTitle() + "</rdfs:title>");
            sw.writeln("    <rdf:type rdf:resource=\"http://swrc.ontoware.org/ontology#Article\" />");
            sw.writeln("    <swrc:journal>" + paper.getJournal() + "</swrc:journal>");
            sw.writeln("    <bibo:authorList rdf:resource=\"http://localhost/journals/" + 
                       paper.getJournal() + "/" + paper.getId() + "/authorlist\" />"); 
            sw.writeln("  </rdf:Description>");
        }
        
        //footer
        sw.write("</rdf:RDF>");
        
        sw.close();
    }
    
    @Override
    public Iterator<Map.Entry<String, Paper> > iterator() {
       return paperMap.entrySet().iterator();
    }
    
    public int size()
    {
        return paperMap.size();
    }
    
    public Paper get(String key)
    {
        return paperMap.get(key);
    }
}
