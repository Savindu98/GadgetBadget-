//IT Number - IT19127606
//Name - Warnasooriya P.B.
//Function - Researcher


package model;

import java.sql.*;

public class Researcher 
{
	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
	 
		try
		{
			Class.forName("com.mysql.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/gadgetbadget", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
	 
		return con;
	}
	
	public String insertResearcher(String rName, String pName, String pNO, String rdate)
	{
		String output = "";
	 
		try
		{
			Connection con = connect();
	 
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
	 
			// create a prepared statement
			String query = " insert into researcher(`researcherID`,`researcherName`,`projectName`,`phoneNO`,`date`)" + " values (?, ?, ?, ?, ?)";
	 
			PreparedStatement preparedStmt = con.prepareStatement(query);
	 
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, rName);
			preparedStmt.setString(3, pName);
			preparedStmt.setString(4, pNO);
			preparedStmt.setString(5, rdate);
	
			// execute the statement
			preparedStmt.execute();
			con.close();
	 
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the researcher.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	public String readResearcher()
	{
		String output = "";
	 
		try
		{
			Connection con = connect();
	 
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Researcher Name</th><th>Project Name</th>" +
					"<th>Phone NO</th>" +
					"<th>Date</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from researcher";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
	 
			// iterate through the rows in the result set
			while (rs.next())
			{
				String researcherID = Integer.toString(rs.getInt("researcherID"));
				String researcherName = rs.getString("researcherName");
				String projectName = rs.getString("projectName");
				String phoneNO = rs.getString("phoneNO");
				String date = rs.getString("date");
	 
				// Add into the html table
				output += "<tr><td>" + researcherName + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + phoneNO + "</td>";
				output += "<td>" + date + "</td>";
	 
				// buttons
				output += "<td><input name='btnUpdate'" 
						+"type='button' value='Update' class='btn btn-secondary'></td>"
						+"<td><form method='post' action='researcher.jsp'>"
						+"<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+"<input name='researcherID' type='hidden' value='" + researcherID + "'>" + "</form></td></tr>";
			}
			con.close();
	 
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the researcher.";
			System.err.println(e.getMessage());
		}
		
		return output;
	 }
	
	public String updateResearcher(String rID, String rName, String pName, String pNO, String rdate)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
		 
			 if (con == null)
			 {return "Error while connecting to the database for updating."; }
			 
			 // create a prepared statement
			 String query = "UPDATE researcher SET researcherName=?,projectName=?,phoneNO=?,date=? WHERE researcherID=?";
		 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setString(1, rName);
			 preparedStmt.setString(2, pName);
			 preparedStmt.setString(3, pNO);
			 preparedStmt.setString(4, rdate);
			 preparedStmt.setInt(5, Integer.parseInt(rID));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		 
			 output = "Updated successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while updating the researcher.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}
	
	public String deleteResearcher(String researcherID)
	{
		 String output = "";
		 
		 try
		 {
			 Connection con = connect();
		 
			 if (con == null)
			 {return "Error while connecting to the database for deleting."; }
		 
			 // create a prepared statement
			 String query = "delete from researcher where researcherID=?";
		 
			 PreparedStatement preparedStmt = con.prepareStatement(query);
		 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(researcherID));
		 
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
		 
			 output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
			 output = "Error while deleting the researcher.";
			 System.err.println(e.getMessage());
		 }
		 return output;
	}

}
