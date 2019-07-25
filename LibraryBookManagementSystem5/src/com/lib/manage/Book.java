package com.lib.manage;

import java.text.MessageFormat;

public class Book
{
	/*
	 * toString() is overriden and returns Book object data in String format
	 */
	private String title;
	private int price;
	private int id, status;
	private String author;

	Book(String title, int price, int id, String author, int status)
	{
		this.title = title;
		this.price = price;
		this.id = id;
		this.price = price;
		this.status = status;
		this.author = author;
	}


	public String getTitle()
	{
		return title;
	}

	public int getPrice()
	{
		return price;
	}

	public int getId()
	{
		return id;
	}

	public String getAuthor()
	{
		return author;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Book getBooksByTitle(String searchStr)
	{
		if (this.title.equalsIgnoreCase(searchStr))
		{
			return this;
		}
		return null;
	}

	public Book getBooksByAuthor(String searchStr)
	{
		if (this.author.equalsIgnoreCase(searchStr))
		{
			return this;
		}
		return null;
	}

	public Book getBooksById(int n)
	{
		if (this.id == n)
		{
			return this;
		}
		return null;
	}
	
	public boolean isMatchedBook(String y)
	{
		if (this.title.equalsIgnoreCase(y))
		{
			return true;
		}
		return false;
	}

	@Override
	public String toString()
	{
		StringBuffer output = new StringBuffer();

		output.append(System.lineSeparator() + "Title\t:{0}");
		output.append(System.lineSeparator() + "Author\t:{1}");
		output.append(System.lineSeparator() + "Price\t:{2}");
		output.append(System.lineSeparator() + "Status\t:{3}");
		output.append(System.lineSeparator() + "Book ID\t:{4}");

		return MessageFormat.format(output.toString(), title, author, price, status, id);
	}

}
