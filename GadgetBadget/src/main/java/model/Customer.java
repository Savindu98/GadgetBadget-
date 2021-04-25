//IT Number - IT19126852
//Name - Chandrasena K.B.D.J
//Function - Customer Management

package model;

import java.sql.*;

public class Customer { // A common method to connect to the DB
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/order", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

public String insertCustomer(String name, String enamil, String address,String number,String PosCode)
 {
		 String output = "";
		 try
		 {
				 Connection con = connect();
				 
				 if (con == null)
				 {return "Error while connecting to the database for inserting."; }
				 
				 // create a prepared statement
				 String query = "insert into customers(`User_ID`,`CusName`,`CusEmail`,`CusAdress`,`CusPhone`,`PostalCode`)"
				 + " values (?, ?, ?, ?, ?, ?)";
				 
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 // binding values
				 
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setString(2, name);
				 preparedStmt.setString(3, enamil);
				 preparedStmt.setString(4, address);
				 preparedStmt.setInt(5, Integer.parseInt(number));
				 preparedStmt.setInt(6, Integer.parseInt(PosCode));
				 
				//execute the statement
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
}

public String readCustomer()
{
			String output = "";
			try
			{
				Connection con = connect();
				
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				
				// Prepare the html table to be displayed
				output = "<table border=\"1\"><tr><th>Customer ID</th>"
						+ "<th>Customer Name</th>"
						+ "<th>Customer Email</th>"
						+ "<th>Customer Address</th>"
						+ "<th>Contact Numaber</th>"
						+ "<th>Postal Code</th>"
						+ "<th>Update</th><th>"
						+ "Remove</th></tr>";
				String query = "select * from customers";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
				String User_ID = Integer.toString(rs.getInt("User_ID"));
				String CusName = rs.getString("CusName");
				String CusEmail = rs.getString("CusEmail");
				String CusAdress = rs.getString("CusAdress");
				String CusPhone = Integer.toString(rs.getInt("CusPhone"));
				String PostalCode = Integer.toString(rs.getInt("PostalCode"));
				
				
				// Add into the html table
				output += "<tr><td>" + User_ID + "</td>";
				output += "<td>" + CusName + "</td>";
				output += "<td>" + CusEmail + "</td>";
				output += "<td>" + CusAdress + "</td>";
				output += "<td>" + CusPhone + "</td>";
				output += "<td>" + PostalCode + "</td>";
				
				// buttons
				output += "<td><input name=\"btnUpdate\" type=\"button\"value=\"Update\" class=\"btn btn-secondary\"></td>"
				+ "<td><form method=\"post\" action=\"items.jsp\">"
				+ "<input name=\"btnRemove\" type=\"submit\" value=\"Remove\"class=\"btn btn-danger\">"
				+ "\">" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
			}
			catch (Exception e)
			{
				output = "Error while reading the Customer.";
				System.err.println(e.getMessage());
			}
			return output;
}

public String updateCustomer(String ID, String name, String enamil, String address, String number,String PosCode)
{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE customers SET CusName=?,CusEmail=?,CusAdress=?,CusPhone=?,PostalCode=? WHERE User_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, enamil);
			preparedStmt.setString(3, address);
			preparedStmt.setInt(4, Integer.parseInt(number));
			preparedStmt.setInt(5, Integer.parseInt(PosCode));
			preparedStmt.setInt(6, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the Customer.";
			System.err.println(e.getMessage());
		}
		return output;
}

public String deleteCustomer(String User_ID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
// create a prepared statement
			String query = "delete from customers where User_ID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
			preparedStmt.setInt(1, Integer.parseInt(User_ID));
// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
