//IT number - IT19125558
//Name - R.A.S madushanka
//Function - Funding Bodies Management

package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Body {

	private Connection connect() { 
		Connection con = null; 
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", ""); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return con; 
	}
	
	public String insertObject(String description, String amount, Date date) { 
		String output = ""; 
		try { 
			Connection con = connect();
			
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			} 
	 
			// create a prepared statement
			String query = " INSERT INTO bodies "
				+ " (`id`,`description`,`amount`,`date`)"
				+ " VALUES (?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query); 

			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, description);
			preparedStmt.setDouble(3, Double.parseDouble(amount)); 
			preparedStmt.setDate(4, date); 
			preparedStmt.execute(); 
			con.close(); 
			output = "Inserted successfully"; 
		} catch (Exception e) { 
			output = "Error while inserting the object."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	}
	
	public String readObjects() { 
		String output = ""; 
		
		try { 
			Connection con = connect(); 
			if (con == null) {
				return "Error while connecting to the database for reading.";
			} 
			
			output = "<table border='1'><tr><th>Id</th><th>Description</th>" +
					"<th>Amount</th>" + 
					"<th>Date</th>" +
					"<th>Update</th><th>Remove</th></tr>"; 
	 
			String query = "SELECT * FROM bodies"; 
			Statement stmt = con.createStatement(); 
			ResultSet rs = stmt.executeQuery(query); 
	 
			// iterate through the rows in the result set
			while (rs.next()) { 
				String id = Integer.toString(rs.getInt("id")); 
				String description = rs.getString("description"); 
				String amount = Double.toString(rs.getDouble("amount")); 
				Date date = rs.getDate("date"); 

				// Add into the html table
				output += "<tr><td>" + id + "</td>"; 
				output += "<td>" + description + "</td>"; 
				output += "<td>" + amount + "</td>"; 
				output += "<td>" + date + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
					+ "<td><form method='post' action='bodies.jsp'>"
					+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
					+ "<input name='id' type='hidden' value='" + id 
					+ "'>" + "</form></td></tr>"; 
			} 
			
			con.close(); 

			output += "</table>"; 
		} catch (Exception e) { 
			output = "Error while reading the objects."; 
			System.err.println(e.getMessage()); 
		} 
		return output; 
	} 
	
	public String updateObject(String id, String description, String amount, Date date) { 
		String output = ""; 
		
		try { 
			Connection con = connect(); 
			
			if (con == null) {
				return "Error while connecting to the database for updating."; 
			} 
			
			// create a prepared statement
			String query = "UPDATE bodies SET description=?,amount=?,date=? WHERE id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
			// binding values
			preparedStmt.setString(1, description);
			preparedStmt.setDouble(3, Double.parseDouble(amount)); 
			preparedStmt.setDate(4, date); 
			preparedStmt.setInt(5, Integer.parseInt(id)); 
		 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Updated successfully"; 
		} catch (Exception e) { 
			output = "Error while updating the item."; 
			System.err.println(e.getMessage()); 
		}
		
		return output; 
	} 
		
	public String deleteObject(String id) { 
		String output = ""; 
		
		try { 
			Connection con = connect(); 
			
			if (con == null) {
				return "Error while connecting to the database for deleting."; 
			} 
			
			// create a prepared statement
			String query = "DELETE FROM bodies WHERE id=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id)); 
			// execute the statement
			preparedStmt.execute(); 
			con.close(); 
			output = "Deleted successfully"; 
		} catch (Exception e) { 
			output = "Error while deleting the object."; 
			System.err.println(e.getMessage()); 
		} 
		 
		return output; 
	}
}
