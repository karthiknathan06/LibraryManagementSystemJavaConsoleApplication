package com.lib.manage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.ParseException;

public class DataUtilUser																		/*Read json data from file and objects are stored in arraylist*/
{
	private static ArrayList<Student> studentArray = new ArrayList<>();
	private BufferedReader buffer;
	
	public void getStudentData() throws FileNotFoundException, IOException
	{
		JSONArray jsonArray = new JSONArray();
		String temp;
		buffer = new BufferedReader(new FileReader(Constants.STUDENT_DATA));
		while ((temp = buffer.readLine()) != null)
		{
			JSONObject jsonObject = new JSONObject(temp);
			jsonArray.put(jsonObject);
		}
		for (int itr = 0; itr < jsonArray.length(); itr++)
		{
			JSONObject jsonObject = jsonArray.getJSONObject(itr);
			Student student = new Student(jsonObject.getInt("regno"), jsonObject.getString("name"), jsonObject.getString("email"), jsonObject.getInt("age"), jsonObject.getString("pass"));
			studentArray.add(student);
		}
	}

	public ArrayList<Student> getStudentArray() throws FileNotFoundException, IOException, ParseException
	{
		if (studentArray.size() == 0)
		{
			getStudentData();
		}
		return studentArray;
	}
	
	public Student login() throws FileNotFoundException, IOException, ParseException			/*Login of user*/
	{
		String user, pass;
		System.out.print("\n\t\t\tLOGIN\n\t\tEnter the user name	:");
		user = Driver.getScanner().nextLine();
		System.out.print("\t\tEnter the password	:");
		pass = Driver.getScanner().nextLine();

		for (Student sd : getStudentArray())
		{
			Student st = sd.verifyLogin(user, pass);
			if (st != null)
			{
				return st;
			}
		}
		return null;
	}
}
