package com.lib.manage;

import java.text.MessageFormat;

public class Student
{
	/*
	 * toString() is overriden and returns Student object data in String format
	 */
	private String name, email, mobile, pass;
	private int regno, age;

	public Student(int regno, String name, String email, int age, String pass)
	{
		this.regno = regno;
		this.name = name;
		this.email = email;
		this.age = age;
		this.pass = pass;
	}

	// getter methods
	public String getName()
	{
		return name;
	}

	public String getEmail()
	{
		return email;
	}

	public int getregID()
	{
		return regno;
	}

	public String getMobile()
	{
		return mobile;
	}

	public int getAge()
	{
		return age;
	}

	public String getPass()
	{
		return pass;
	}

	public Student verifyLogin(String user, String pass)
	{
		/* check login details */

		if (this.getEmail().equals(user) && this.getPass().equals(pass))
		{
			return this;
		}
		return null;
	}

	public Student search(String str)
	{
		if (this.getName().equalsIgnoreCase(str))
		{
			return this;
		}
		return null;
	}

	public Student search(int str)
	{
		if (this.getregID() == str)
		{
			return this;
		}
		return null;
	}

	public Student searchByEmail(String str)
	{
		if (this.getEmail().equalsIgnoreCase(str))
		{
			return this;
		}
		return null;
	}

	@Override
	public String toString()
	{
		StringBuffer output = new StringBuffer();

		output.append(System.lineSeparator() + "RegID\t:{0}");
		output.append(System.lineSeparator() + "Name\t:{1}");
		output.append(System.lineSeparator() + "Email\t:{2}");
		output.append(System.lineSeparator() + "Mobile\t:{3}");
		output.append(System.lineSeparator() + "Age\t:{4}");

		return MessageFormat.format(output.toString(), regno, name, email, mobile, age);
	}
}
