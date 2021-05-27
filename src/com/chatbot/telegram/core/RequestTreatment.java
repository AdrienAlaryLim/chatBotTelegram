package com.chatbot.telegram.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.chatbot.telegram.config.UserConstants;
import com.chatbot.telegram.config.UserDatabaseRequests;

public class RequestTreatment {
	
	/**
	 * Build and send the SQL query to the database. 
	 * Return a List of string, containing the result of the request.
	 * @param sqlStringRequest 
	 * @param sqlColumn 
	 * @return List<String>
	 */
	public static List<String> returnQueryArrayResponse(String sqlStringRequest, String sqlColumn)
	{
		List<String> sqlResponse = new ArrayList<>();
		
		try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	PreparedStatement ps = con.prepareStatement(sqlStringRequest); 
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next())
	        {
	        	sqlResponse.add(rs.getString(sqlColumn));
	        }
	        
	        con.close();
		 }
		 catch(SQLException  e) {
				e.printStackTrace();
		 }
		 
		 return sqlResponse;
	}
	
	/**
	 * Build and send the SQL query to the database. 
	 * Return a string, containing the result of the request.
	 * @param sqlStringRequest 
	 * @param sqlColumn 
	 * @return List<String>
	 */
	public static String returnQueryStringResponse(String sqlStringRequest, String sqlColumn)
	{
		String sqlResponse = new String();
		
		try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(sqlStringRequest); 
	        ResultSet rs = ps.executeQuery();
        
	        while(rs.next())
	        {
	        	sqlResponse = rs.getString(sqlColumn);
	        }
	        
	        con.close();
		 }
		 catch(SQLException  e) {
				e.printStackTrace();
				System.out.println(sqlStringRequest);
		 }
		 
		 return sqlResponse;
	}
	
	/**
	 * Build and execute the insert question query
	 * @param userQuestion the question asked by the user
	 */
	public static void prepareInsertQuestion(String userQuestion)
	{
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println("('" + userQuestion + "', '" + date + "')");
		String valuesInsert = "('" + userQuestion + "', '" + date + "')";
		
		String stringRequest = UserDatabaseRequests.buildInsertQuestionValues(valuesInsert);
		
		try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(stringRequest); 
	        ps.execute();
	        
	        con.close();
		 }
		 catch(SQLException  e) {
			 
		 }
	}
	
	/**
	 * Build and execute the insert question query
	 * @param userQuestion the question asked by the user
	 */
	public static void prepareInsertAnswering(int intIdQuestionInserted, String responseId, int confidentIndicator, String conflictedKeywords )
	{
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		
		conflictedKeywords = conflictedKeywords == null ?  null : "'" + conflictedKeywords + "'";
		
		System.out.println("('"+ intIdQuestionInserted + "', '" + responseId + "', '" + date + "', '"+ confidentIndicator + "', " + conflictedKeywords + ")");
		String valuesInsert = "('"+ intIdQuestionInserted + "', '" + responseId + "', '" + date + "', '"+ confidentIndicator + "', " + conflictedKeywords + ")";
		
		String stringRequest = UserDatabaseRequests.buildInsertAnsweringValues(valuesInsert);
		
		System.out.print(stringRequest);
		
		try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(stringRequest); 
	        ps.execute();
	        
	        con.close();
		}
		catch(SQLException  e) {
			e.printStackTrace();
		}
	}	
}
