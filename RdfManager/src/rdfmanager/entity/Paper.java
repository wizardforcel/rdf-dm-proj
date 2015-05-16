/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package rdfmanager.entity;

import java.util.ArrayList;

/**
 *
 * @author Wizard
 */
public class Paper 
{
    private String id = "";
    private String title = "";
    private String[] label 
            = new String[0];
    private int year;
    private String journal = "";
    private ArrayList<Author> authorList
            = new ArrayList<>();
    
    public Paper() {}
    
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String[] getLabel() { return label; }
    public void setLabel(String[] label) { this.label = label; }
    
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    
    public String getJournal() { return journal; }
    public void setJournal(String journal) { this.journal = journal; }
    
    public ArrayList<Author> getList() { return authorList; }
}
