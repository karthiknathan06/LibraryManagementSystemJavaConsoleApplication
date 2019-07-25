package com.lib.manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class DataUtilBook
{
	FileWriter file;
	List<Book> bookArray; 	/* Arraylist containing objects of Book */
	List<Lender> lendArray; /* Arraylist containing objects of Lender */
	Student student;
	JSONArray jsonArray;
	JSONObject jsonObject;

	public void getBookData()
	{
		jsonArray = new JSONArray();
		try(BufferedReader buffer = new BufferedReader(new FileReader(Constants.BOOK_DATA)))
		{
			for (String bookString = buffer.readLine(); bookString != null; bookString = buffer.readLine())
			{
				jsonObject = new JSONObject(bookString);
				jsonArray.put(jsonObject);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for (int bookItr = 0; bookItr < jsonArray.length(); bookItr++)
		{
			jsonObject = jsonArray.getJSONObject(bookItr);
			Book book = new Book(jsonObject.getString(BookEnum.TITLE.getContext()),
					jsonObject.getInt(BookEnum.PRICE.getContext()), jsonObject.getInt(BookEnum.ID.getContext()),
					jsonObject.getString(BookEnum.AUTHOR.getContext()),
					jsonObject.getInt(BookEnum.STATUS.getContext()));
			bookArray.add(book);
		}
	}

	public void getLendData()
	{
		jsonArray = new JSONArray();
		try(BufferedReader buffer = new BufferedReader(new FileReader(Constants.LENDED_BOOKS)))
		{
			for (String lendString = buffer.readLine(); lendString != null; lendString = buffer.readLine())
			{
				JSONObject jsonObject = new JSONObject(lendString);
				jsonArray.put(jsonObject);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		for (int lendItr = 0; lendItr < jsonArray.length(); lendItr++)
		{
			jsonObject = jsonArray.getJSONObject(lendItr);
			Lender book = new Lender(jsonObject.getString(LendEnum.NAME.getContext()),
					jsonObject.getString(LendEnum.AUTHOR.getContext()),
					jsonObject.getString(LendEnum.USER.getContext()), jsonObject.getInt(LendEnum.REGNO.getContext()),
					jsonObject.getInt(LendEnum.BOOKID.getContext()), jsonObject.getString(LendEnum.DATE.getContext()));
			lendArray.add(book);
		}
	}

	public void cataloque() throws IOException, ParseException
	{
		/* cataloque for navigating different options */

		student = Driver.getCurrentUser();

		String book_template = "\n1.Search by Title\n2.Search by Author\n3.Search by Book ID\n4.Lend Books\n5.View Lended Books\n6.Return Book\n"
				+ "7.Go Back!\n\nEnter the choice	:";
		String choice, searchString;
		int searchId;

		while (true)
		{
			System.out.print(book_template);
			choice = Driver.getScanner().nextLine();
			switch (choice)
			{
			case "1":
				System.out.print("Enter the Book Name	:");
				searchString = Driver.getScanner().nextLine();
				searchByTitle(searchString);
				break;

			case "2":
				System.out.print("Enter the Author Name	:");
				searchString = Driver.getScanner().nextLine();
				searchByAuthor(searchString);
				break;

			case "3":
				System.out.print("Enter the Book ID	    :");
				searchId = Driver.getScanner().nextInt();
				Driver.getScanner().nextLine();
				searchById(searchId);
				break;

			case "4":
				System.out.println("Enter the Book Name		:");
				searchString = Driver.getScanner().nextLine();
				lendBook(searchString);
				break;

			case "5":
				printLendedBooks();
				break;

			case "6":
				returnBook();
				break;

			case "7":
				System.out.println("Exit");
				if (getBookArray() != null)
				{
					updateFileBook();
				}
				if (getLenderArray() != null)
				{
					updateFileLend();
				}
				lendArray.clear();
				return;
			default:
				System.out.println("Enter the valid choice!!");
			}
		}
	}

	public List<Book> getBookArray() throws FileNotFoundException, IOException, ParseException
	{
		if (bookArray == null)
		{
			bookArray = new ArrayList<Book>();
			getBookData();
		}
		return bookArray;
	}

	public List<Lender> getLenderArray() throws FileNotFoundException, IOException, ParseException
	{
		if (lendArray == null)
		{
			lendArray = new ArrayList<Lender>();
			getLendData();
		}
		return lendArray;
	}

	public void searchByTitle(String title) throws FileNotFoundException, IOException, ParseException
	{
		for (Book bookObj : getBookArray())
		{
			if (bookObj.getBooksByTitle(title) != null)
			{
				System.out.println(bookObj);
				return;
			}
		}
		System.out.println("\nSorry this book doesnot exists!!\n");
	}

	public void searchById(int id) throws FileNotFoundException, IOException, ParseException
	{
		for (Book bookObj : getBookArray())
		{
			if (bookObj.getBooksById(id) != null)
			{
				System.out.println(bookObj);
				return;
			}
		}
		System.out.println("\nSorry this book doesnot exists!!!\n");
	}

	public void searchByAuthor(String n) throws FileNotFoundException, IOException, ParseException
	{
		for (Book bookObj : getBookArray())
		{
			if (bookObj.getBooksByAuthor(n) != null)
			{
				System.out.println(bookObj);
				return;
			}
		}
		System.out.println("\nSorry this book doesnot exists!!!\n");
	}

	public void lendBook(String searchString) throws FileNotFoundException, IOException, ParseException
	{
		for (Book bookObj : getBookArray())
		{
			if (bookObj.isMatchedBook(searchString) && bookObj.getStatus() == 1)
			{
				toLend(bookObj);
				return;
			}
		}
		System.out.println("This book is not for lending");
	}

	public void toLend(Book bookObj) throws FileNotFoundException, IOException, ParseException
	{
		/* Adds the Current user data and book lend data to lend Array list */

		bookObj.setStatus(0);
		Lender lender = new Lender(bookObj, student);
		if (getLenderArray() != null)
		{
			lendArray.add(lender);
		}
		System.out.println("Book has been accounted in your ID\n");
	}

	public void printLendedBooks() throws FileNotFoundException, IOException, ParseException
	{
		/* display the lended books in your account */

		if (getLenderArray().size() != 0)
		{
			for (Lender lender : getLenderArray())
			{
				if (lender.isMatchingStudentName(student.getName()))
				{
					System.out.println(lender);
				}
			}
		} else
		{
			System.out.println("No books are currently in your account");
		}
	}

	public void returnBook() throws FileNotFoundException, IOException, ParseException
	{
		/* Book may be returned and data from lending Array list is deleted */

		String searchString;
		System.out.println("Enter the Book Name	:");
		searchString = Driver.getScanner().nextLine();
		for (Lender lender : getLenderArray())
		{
			if (lender.isMatchingBookName(searchString))
			{
				lendArray.remove(lender);
				changeStatus(lender.getBook_name());
				System.out.println("\nBook Name		:" + lender.getBook_name() + " is returned!!\n");
				return;
			}
		}
		System.out.println("This book is not in your lended list!");
	}

	public void changeStatus(String bookname) throws FileNotFoundException, IOException, ParseException
	{
		/* Changes the status of book in Book table */

		for (Book book : getBookArray())
		{
			if (book.getTitle().equals(bookname))
			{
				book.setStatus(1);
				return;
			}
		}
	}

	public void updateFileBook() throws IOException, JSONException, ParseException
	{
		file = new FileWriter(Constants.BOOK_DATA);
		Book book;
		
		for (Iterator<Book> iterateBooks = bookArray.iterator(); iterateBooks.hasNext();)
		{
			book = iterateBooks.next();
			jsonObject = new JSONObject();
			jsonObject.put(BookEnum.TITLE.getContext(), book.getTitle());
			jsonObject.put(BookEnum.PRICE.getContext(), book.getPrice());
			jsonObject.put(BookEnum.ID.getContext(), book.getId());
			jsonObject.put(BookEnum.STATUS.getContext(), book.getStatus());
			jsonObject.put(BookEnum.AUTHOR.getContext(), book.getAuthor());
			file.write(jsonObject.toString() + System.lineSeparator());
		}
		file.close();
	}

	public void updateFileLend() throws IOException, JSONException, ParseException
	{
		file = new FileWriter(Constants.LENDED_BOOKS);
		Lender lend;
		
		for (Iterator<Lender> iterateLend = lendArray.iterator(); iterateLend.hasNext();)
		{
			lend = iterateLend.next();
			jsonObject = new JSONObject();
			jsonObject.put(LendEnum.NAME.getContext(), lend.getBook_name());
			jsonObject.put(LendEnum.AUTHOR.getContext(), lend.getBook_author());
			jsonObject.put(LendEnum.USER.getContext(), lend.getUser_name());
			jsonObject.put(LendEnum.REGNO.getContext(), lend.getReg_no());
			jsonObject.put(LendEnum.BOOKID.getContext(), lend.getBook_id());
			jsonObject.put(LendEnum.DATE.getContext(), lend.getDate());
			file.write(jsonObject.toString() + System.lineSeparator());
		}
		file.close();
	}

}
