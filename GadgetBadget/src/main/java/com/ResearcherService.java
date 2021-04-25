//IT Number - IT19127606
//Name - Warnasooriya P.B.
//Function - Researcher


package com;

import model.Researcher;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Researcher")
public class ResearcherService 
{
	Researcher researcherObj = new Researcher();
	
	//Implement the Read researcher Operation
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readResearcher()
	{
		return researcherObj.readResearcher();
	} 
	
	//Implement the Create/Insert researcher Operation
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertResearcher(@FormParam("researcherName") String researcherName,
	 @FormParam("projectName") String projectName,
	 @FormParam("phoneNO") String phoneNO,
	 @FormParam("date") String date)
	{
	 String output = researcherObj.insertResearcher(researcherName, projectName, phoneNO, date);
	return output;
	}

	//Implement the Update Operation
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateResearcher(String researcherData)
	{
		//Convert the input string to a JSON object
		JsonObject researcherObject = new JsonParser().parse(researcherData).getAsJsonObject();
		
		//Read the values from the JSON object
		String researcherID = researcherObject.get("researcherID").getAsString();
		String researcherName = researcherObject.get("researcherName").getAsString();
		String projectName = researcherObject.get("projectName").getAsString();
		String phoneNO = researcherObject.get("phoneNO").getAsString();
		String date = researcherObject.get("date").getAsString();
		String output = researcherObj.updateResearcher(researcherID, researcherName, projectName, phoneNO, date);
	
		return output;
	}

	//Implement the Delete Operation
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteResearcher(String researcherData)
	{
	 //Convert the input string to an XML document
	 Document doc = Jsoup.parse(researcherData, "", Parser.xmlParser());

	 //Read the value from the element <researcherID>
	 String researcherID = doc.select("researcherID").text();
	 
	 String output = researcherObj.deleteResearcher(researcherID);
	 
	return output;
	}
}
