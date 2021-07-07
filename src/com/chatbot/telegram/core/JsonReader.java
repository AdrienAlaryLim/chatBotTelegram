package com.chatbot.telegram.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;

import com.chatbot.telegram.config.UserConstants;
import com.chatbot.telegram.config.UserDatabaseRequests;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

	/**
	 * Read json result on web page, return a Map of result
	 * @param request
	 * @param column
	 * @param columnValue
	 * @return Map<String, String>
	 * @throws IOException
	 * @throws JSONException
	 */
	 public static Map<String, String> callJson(String request, String column, String columnValue) throws IOException, JSONException
	 {
		 
		 String spaceLessColumnValue = columnValue.replaceAll(" ", "_");
		 
		 URL url = new URL(UserConstants.getUrlGetRequest() + request + "&" + column + spaceLessColumnValue);
		 System.out.println("  " + url.toString());
		 InputStream in = url.openStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		 StringBuilder result = new StringBuilder();
		 String line;
		 while((line = reader.readLine()) != null) {
		     result.append(line);
		 }
		 
		 return jsonToMap(result.toString());
	 }
	 
	 /**
	  * Read json result on web page, return a Map of result
	  * @param request
	  * @param column1
	  * @param columnValue1
	  * @param column2
	  * @param columnValue2
	  * @return
	  * @throws IOException
	  * @throws JSONException
	  */
	 public static Map<String, String> callJson(String request, String column1, String columnValue1, String column2, String columnValue2) throws IOException, JSONException
	 {
		 
		 String spaceLessColumnValue1 = columnValue1.replaceAll(" ", "_");
		 String spaceLessColumnValue2 = columnValue2.replaceAll(" ", "_");
		 
		 URL url = new URL(UserConstants.getUrlGetRequest() + request + "&" + column1 + spaceLessColumnValue1+ "&" + column2 + spaceLessColumnValue2);
		 System.out.println("  " + url.toString());
		 InputStream in = url.openStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		 StringBuilder result = new StringBuilder();
		 String line;
		 while((line = reader.readLine()) != null) {
		     result.append(line);
		 }
		 
		 return jsonToMap(result.toString());
		 
	 }
	 
	 /**
	  * Converts a give Map into a Json format
	  * @param json
	  * @return Map<String, String>
	  * @throws JsonParseException
	  * @throws IOException
	  */
	 public static Map<String, String> jsonToMap(String json) throws JsonParseException, IOException
	 {
		 Map<String, String> resultMap = new ObjectMapper().readValue(json, HashMap.class);
		 
		 return resultMap;
	 }
	 
	 /**
	  * Read json result on web page, return a List of Map of result
	  * @param request
	  * @param column
	  * @param columnValue
	  * @return List<Map<String, String>>
	  * @throws IOException
	  * @throws JSONException
	  */
	 public static List<Map<String, String>> callListJson(String request, String column, String columnValue) throws IOException, JSONException
	 {
		 
		 String spaceLessColumnValue = columnValue.replaceAll(" ", "_");
		 
		 URL url = new URL(UserConstants.getUrlGetRequest() + request + "&" + column + spaceLessColumnValue);
		 System.out.println("  " + url.toString());
		 InputStream in = url.openStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		 StringBuilder result = new StringBuilder();
		 String line;
		 while((line = reader.readLine()) != null) {
		     result.append(line);
		 }
		 
		 return jsonListToMap(result.toString());
	 }
	 
	 /**
	  * Read json result on web page, return a List of Map of result
	  * @param request
	  * @return List<Map<String, String>>
	  * @throws IOException
	  * @throws JSONException
	  */
	 public static List<Map<String, String>> callListJson(String request) throws IOException, JSONException
	 {
		 URL url = new URL(UserConstants.getUrlGetRequest() + request);
		 System.out.println("  " + url.toString());
		 InputStream in = url.openStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		 StringBuilder result = new StringBuilder();
		 String line;
		 while((line = reader.readLine()) != null) {
		     result.append(line);
		 }
		 
		 return jsonListToMap(result.toString());
	 }
	 
	 /**
	  * Returns a List of map from a String json formatted
	  * @param json
	  * @return List<Map<String, String>>
	  * @throws JsonParseException
	  * @throws IOException
	  */
	 public static List<Map<String, String>> jsonListToMap(String json) throws JsonParseException, IOException
	 {
		 ObjectMapper objectMapper = new ObjectMapper();
		 objectMapper.writerWithDefaultPrettyPrinter();
		 
		 List<Map<String, String>> listOfMap = new ArrayList<>();
		 
		 try {
			 listOfMap = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, Map.class));
			 
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
		 
		 
		 return listOfMap;
	 }
	 
	 /**
	  * Send request adapted for inserting response in database
	  * @param idQuestion
	  * @param idReponse
	  * @param confidence
	  * @param conflicts
	  * @throws IOException
	  * @throws JSONException
	  */
	 public static void insertResponse(String idQuestion, String idReponse, int confidence, String conflicts) throws IOException, JSONException
	 {	
		 URL url = new URL(UserConstants.getUrlGetRequest() + UserDatabaseRequests.getRequestInsertReponse() + "&" + UserDatabaseRequests.getUrlColumnIdQuestion() + idQuestion + "&" + UserDatabaseRequests.getUrlColumnIdReponse() + idReponse + "&" + UserDatabaseRequests.getColumnConfiance() + confidence + "&" + UserDatabaseRequests.getColumnConflicts() + conflicts);
		 System.out.println("  " + url.toString());
		 InputStream in = url.openStream();
		 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		 StringBuilder result = new StringBuilder();
		 String line;
		 while((line = reader.readLine()) != null) {
		     result.append(line);
		 }
	 }	
}
