package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import user.UserConstants;
import user.UserDatabaseRequests;

public class Treatment {
	
	/**
	 * Return the response that the bot have to send.
	 * @param userMessageText the message sent by the user
	 * @return
	 */
	public static String returnMessage(String userMessageText)
	{
		String botResponse = userMessageText;
		String[] splitWords = userMessageText.split(" ");
    
	    String sqlStringArrayFilter = buildSqlFilterString(splitWords);
	    
	    try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(UserDatabaseRequests.buildSelectMotsClesIn(sqlStringArrayFilter)); 
	        ResultSet rs = ps.executeQuery();
	        System.out.println("Array string filter: " + sqlStringArrayFilter);
	        
	        while(rs.next())
	        {
	        	String question = rs.getString("MOT");
	        	botResponse = question; 
	            System.out.println("Result set: " + question);
	        }
	        
	        con.close();
		}
		catch(SQLException  e) {
			e.printStackTrace();
		}
	    
	    return botResponse;
	}
	
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	private static String buildSqlFilterString(String[] arrayString)
    {
		String sqlStringArrayFilter = "(";
	    
	    for(int i=0; i<arrayString.length; i++)
	    {
	    	sqlStringArrayFilter = sqlStringArrayFilter + "'" + arrayString[i] + "'";
	    	
	    	if(i != arrayString.length-1)
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + ",";
	    	}else
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + ")";
	    	}
	    }
	    
	    return sqlStringArrayFilter;
    }
}
