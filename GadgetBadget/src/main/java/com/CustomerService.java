//IT Number - IT19126852
//Name - Chandrasena K.B.D.J
//Function - Customer Management

package com;

import model.Customer;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customers")
public class CustomerService
{
	Customer customerObj = new Customer();
	
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)

public String readCustomer()
{
	return customerObj.readCustomer() ;
}

@POST
@Path("/")
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
@Produces(MediaType.TEXT_PLAIN)

public String insertItem(@FormParam("CusName") String CusName,
 @FormParam("CusEmail") String CusEmail,
 @FormParam("CusAdress") String CusAdress,
 @FormParam("CusPhone") String CusPhone,
 @FormParam("PostalCode") String PostalCode)
{
 String output = customerObj.insertCustomer(CusName, CusEmail, CusAdress, CusPhone,PostalCode);
return output;
}

@PUT
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.TEXT_PLAIN)
public String updateCustomer(String itemData)
{
//Convert the input string to a JSON object
 JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
//Read the values from the JSON object
 String User_ID = itemObject.get("User_ID").getAsString();
 String CusName = itemObject.get("CusName").getAsString();
 String CusEmail = itemObject.get("CusEmail").getAsString();
 String CusAdress = itemObject.get("CusAdress").getAsString();
 String CusPhone = itemObject.get("CusPhone").getAsString();
 String PostalCode = itemObject.get("PostalCode").getAsString();
 
 String output = customerObj.updateCustomer(User_ID, CusName, CusEmail, CusAdress, CusPhone,PostalCode);
return output;
}

@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteCustomer(String itemData)
{
	
//Convert the input string to an XML document
 Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());

//Read the value from the element <itemID>
 String User_ID = doc.select("User_ID").text();
 String output = customerObj.deleteCustomer(User_ID);
 
return output;

}


}
