package com.lib.manage;

public enum BookEnum 
{ 
    TITLE("title"), PRICE("price"), ID("id"), STATUS("status"), AUTHOR("author"); 
  
    private String context; 
  
    public String getContext() 
    { 
        return this.context; 
    } 
  
    private BookEnum(String context) 
    { 
        this.context = context; 
    } 
} 
