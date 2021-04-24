//IT Number - IT19125626
//Name - Dulanjaya L.A.P.S
//Function - Project Management

package com;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Project;

@Path("/Projects")
public class ProjectService {
	Project projectObj = new Project(); 
	
	//Read Method
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML) 
	
	public String readItems() 
	{  
		return projectObj.readItems(); 
		
	}
	//Insert method
	
		@POST 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String insertItem(@FormParam("itemcode") String itemcode, 
				@FormParam("itemname") String itemname,
				@FormParam("quantity") String quantity, 
				@FormParam("description") String description, 
				@FormParam("supemail") String supemail) 
		{
			String output = projectObj.insertItem(itemcode, itemname, quantity, description,supemail);
			return output; 
		}
		
		//Update method
		
		@PUT 
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String updateItem(String itemData) 
		{
			
			//Convert the input string to a JSON object 
			JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject(); 
			
			//Read the values from the JSON object 
			String itemid = itemObject.get("itemid").getAsString(); 
			String itemcode = itemObject.get("itemcode").getAsString();  
			String itemname = itemObject.get("itemname").getAsString(); 
			String quantity = itemObject.get("quantity").getAsString(); 
			String description = itemObject.get("description").getAsString(); 
			String supemail = itemObject.get("supemail").getAsString(); 
			
			 String output = projectObj.updateItem(itemid, itemcode, itemname, quantity, description , supemail); 
			 return output; 
		}
		
		//Delete Method
		
		@DELETE 
		@Path("/") 
		@Consumes(MediaType.APPLICATION_XML) 
		@Produces(MediaType.TEXT_PLAIN) 
		
		public String deleteItem(String itemData) 
		{
			//Convert the input string to an XML document 
			Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
			//Read the value from the element <itemID> 
			String itemid = doc.select("itemid").text(); 
			
			String output = projectObj.deleteItem(itemid); 
			return output; 
			
			
		}

}
