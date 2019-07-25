package com.lib.manage;

public enum LendEnum
{
	NAME("book_name"), AUTHOR("book_author"), USER("user_name"), REGNO("reg_no"), BOOKID("book_id"), DATE("date"); 
	  
    private String context; 
  
    public String getContext() 
    { 
        return this.context; 
    } 
  
    private LendEnum(String context) 
    { 
        this.context = context; 
    } 
}
