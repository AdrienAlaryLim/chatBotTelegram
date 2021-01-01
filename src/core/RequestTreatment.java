package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import user.UserConstants;
import user.UserDatabaseRequests;

public class RequestTreatment {
	
	public static final String ERROR = "error";
	
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
		Boolean resultIsNull = Boolean.TRUE;
		
		try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(sqlStringRequest); 
	        ResultSet rs = ps.executeQuery();
	        
	        while(rs.next())
	        {
	        	resultIsNull = Boolean.FALSE;
	        	sqlResponse = rs.getString(sqlColumn);
	        }
	        
	        if(resultIsNull)
	        {
	        	throw new SQLException("Request response not found");
	        }
	        
	        con.close();
		 }
		 catch(SQLException  e) {
				e.printStackTrace();
				sqlResponse = ERROR;
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
	public static void prepareInsertAnswering(int intIdQuestionInserted,int responseId, int confidentIndicator, String stringConflictedQuestionId )
	{
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println("('"+ intIdQuestionInserted + "', '" + responseId + "', '" + date + "', '"+ confidentIndicator + "', '" + stringConflictedQuestionId + "')");
		String valuesInsert = "('"+ intIdQuestionInserted + "', '" + responseId + "', '" + date + "', '"+ confidentIndicator + "', '" + stringConflictedQuestionId + "')";
		
		String stringRequest = UserDatabaseRequests.buildInsertAnsweringValues(valuesInsert);
		
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
