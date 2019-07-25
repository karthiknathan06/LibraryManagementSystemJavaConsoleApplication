package com.lib.manage;

import java.text.MessageFormat;
import java.util.Date;

public class Lender
{
	/*
	 * Class with attributes of user details with their lended book details with
	 * date
	 * toString() is overriden and returns object data in String format
	 */
	private String book_name, book_author, user_name;
	private int reg_no, book_id;
	private String date = new Date().toString();

	public Lender(Book sd, Student st)
	{
		this.book_name 		= sd.getTitle();
		this.book_author 	= sd.getAuthor();
		this.user_name 		= st.getName();
		this.reg_no 		= st.getregID();
		this.book_id		= sd.getId();
	}
	
	public Lender(String book_name, String book_author, String user_name, int reg_no, int book_id, String date)
	{
		this.book_name = book_name;
		this.book_author = book_author;
		this.user_name = user_name;
		this.reg_no = reg_no;
		this.book_id = book_id;
		this.date = date;
	}



	public String getBook_name()
	{
		return book_name;
	}

	public String getBook_author()
	{
		return book_author;
	}

	public String getUser_name()
	{
		return user_name;
	}

	public String getDate()
	{
		return date;
	}

	public int getReg_no()
	{
		return reg_no;
	}

	public int getBook_id()
	{
		return book_id;
	}
	
	public boolean isMatchingStudentName(String studentName)
	{
		if (this.user_name.equalsIgnoreCase(studentName))
		{
			return true;
		}
		return false;
	}
	
	public boolean isMatchingBookName(String bookName)
	{
		if(this.book_name.equalsIgnoreCase(bookName))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public String toString()
	{

		StringBuffer output = new StringBuffer();

		output.append(System.lineSeparator() + "Book Name\t:{0}");
		output.append(System.lineSeparator() + "Book Author\t:{1}");
		output.append(System.lineSeparator() + "User Name\t:{2}");
		output.append(System.lineSeparator() + "Reg No\t\t:{3}");
		output.append(System.lineSeparator() + "Date\t\t:{4}");
		output.append(System.lineSeparator() + "Book Id\t\t:{5}");

		return MessageFormat.format(output.toString(), book_name, book_author, user_name, reg_no, date, book_id);
	}

}
