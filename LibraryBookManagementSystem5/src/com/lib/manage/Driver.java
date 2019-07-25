
/*
* Driver
*
* @version 	1.0
*
* @author 	ckn */

package com.lib.manage;

import java.util.Scanner;

public class Driver
{
	private static Scanner scanner;
	private static Student currentStudent;
	
	public static void main(String s[])
	{
		String input;
		String mainTemplate = "\n1.Login\n2.Exit\n\nEnter the choice	:";
		try
		{
			scanner = new Scanner(System.in);
			
			System.out.println(
					"******************LIBRARY BOOK MANAGEMENT SYSTEM*****************" + System.lineSeparator());

			while (true)
			{
				System.out.print(mainTemplate);
				input = scanner.nextLine();
				switch (input)
				{
				case "1":
					DataUtilUser utiluser = new DataUtilUser();
					currentStudent = utiluser.login();
					if (currentStudent != null)
					{
						DataUtilBook utilbook = new DataUtilBook();
						utilbook.cataloque();
					}
					else
					{
						System.out.println("\n\t\tInvalid User credentials!!!\n");
					}
					break;
				case "2":
					System.out.println("****************System Exits******************");
					return;

				default:
					System.out.println("Choose Valid input!\n");
				}
			}
		} 
		catch (Exception e)
		{
			System.out.println(e);
		} 
		finally
		{
			scanner.close();
		}
	}
	public static Student getCurrentUser()
	{
		return currentStudent;
	}
	
	public static Scanner getScanner()
	{
		return scanner;
	}
}
