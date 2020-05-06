package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointment {
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/appointmentapiproject?useTimezone=true&serverTimezone=UTC", "root", "");
			// For testing
			System.out.println("Successfully connected");
			
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	} 
	
	// reading an items -------------------------
		public String readItems()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output ="<table border='1'><tr><th>Patirnt ID</th><th>Patient Name</th><th>Patient Age</th>" 
							+ "<th>Type Of Case</th><th>Doctor Name</th><th>Update</th><th>Remove</th></tr>";  

				String query = "SELECT * FROM appointmnets";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String p_id = Integer.toString(rs.getInt("p_id"));
					String p_name = rs.getString("p_name");
					String p_age = Integer.toString(rs.getInt("p_age"));
					String type_of_case = rs.getString("type_of_case");
					String d_name = rs.getString("d_name"); 

					// Add into the html table
					output += "<tr><td><input id='hidItemIDUpdate'name='hidItemIDUpdate' type='hidden' value='" + p_id + "'>" + p_id + "</td>";
					output += "<td>" + p_name + "</td>";
					output += "<td>" + p_age + "</td>";
					output += "<td>" + type_of_case + "</td>";
					output += "<td>" + d_name + "</td>";
					// buttons
					output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-p_id='"+ p_id + "'>" + "</td></tr>";
				}
				con.close();
				// Complete the html table
				output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the items.";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//inserting---------------------
		public String insertItem(String p_id, String p_name,String p_age, String type_of_case, String d_name )
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for inserting.";
				}
				// create a prepared statement
				String query = " INSERT INTO `appointmnets`(`p_id`, `p_name`, `p_age`, `type_of_case`, `d_name`)"+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(p_id));
				preparedStmt.setString(2, p_name);
				preparedStmt.setInt(3, Integer.parseInt(p_age));
				preparedStmt.setString(4, type_of_case);
				preparedStmt.setString(5, d_name);
				// execute the statement
				preparedStmt.execute();
				
				 System.out.print("successfuly inserted");
				 
				con.close();
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//update items
		public String updateItem(String p_id, String p_name,String p_age, String type_of_case, String d_name)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{
					return "Error while connecting to the database for updating.";
				}
				// create a prepared statement
				String query = "UPDATE `appointmnets` SET `p_name`=?,`p_age`=?,`type_of_case`=?,`d_name`=? WHERE `p_id`=? ";
			
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				
				preparedStmt.setString(1, p_name);
				preparedStmt.setInt(2, Integer.parseInt(p_age));
				preparedStmt.setString(3, type_of_case);
				preparedStmt.setString(4, d_name);
				preparedStmt.setInt(5, Integer.parseInt(p_id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		//delete items------------------------
		public String deleteItem(String p_id) {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}
				// create a prepared statement
				String query = "DELETE FROM `appointmnets` WHERE `p_id`=? ";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(p_id));
				// execute the statement
				preparedStmt.execute();
				con.close();
				//output = "Deleted successfully";
				String newItems = readItems();
				output = "{\"status\":\"success\", \"data\": \"" + newItems + "\"}";
			} catch (Exception e) {
				//output = "Error while deleting the item.";
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
				
				System.err.println(e.getMessage());
			}
			return output;
		}
	
}
