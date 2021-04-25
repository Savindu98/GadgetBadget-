//IT number - IT19125558
//Name - R.A.S madushanka
//Function - Funding Bodies Management


package com;

import java.sql.Date;
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import model.Body;
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 

@Path("/bodies") 
public class BodyService { 
	Body bodyObj = new Body();
	
	@GET
	@Path("/") 
	@Produces(MediaType.TEXT_HTML) 
	public String fundingBodies() { 
		return bodyObj.readObjects();
	} 
	
	@POST
	@Path("/") 
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String insertItem(@FormParam("description") String description, 
		@FormParam("amount") String amount, 
		@FormParam("date") String date) 
	{ 
		String output = bodyObj.insertObject(description, amount, Date.valueOf(date)); 
		return output; 
	}
	
	@PUT
	@Path("/") 
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String updateItem(String itemData) {
		JsonObject object = new JsonParser().parse(itemData).getAsJsonObject(); 
	
		String id = object.get("id").getAsString(); 
		String description = object.get("description").getAsString(); 
		String amount = object.get("amount").getAsString(); 
		Date date = Date.valueOf(object.get("date").getAsString());
		String output = bodyObj.updateObject(id, description, amount, date); 
		return output; 
	}

	@DELETE
	@Path("/") 
	@Consumes(MediaType.APPLICATION_XML) 
	@Produces(MediaType.TEXT_PLAIN) 
	public String deleteItem(String itemData) {
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser()); 
	 
		String id = doc.select("id").text(); 
		String output = bodyObj.deleteObject(id); 
		return output; 
	}

}
