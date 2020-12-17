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
    
	    String sqlArrayKeywordFilter = buildSqlFilterString(splitWords);
	    
	    try{
	    	Connection con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
	    	
	    	PreparedStatement ps = con.prepareStatement(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter)); 
	        ResultSet rs = ps.executeQuery();
	        System.out.println("Array string filter: " + sqlArrayKeywordFilter);
	        
	        System.out.println(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter));
	        
	        String[] keyWordsFound = {};
	        int countUserKeyWords = 0;
	        while(rs.next())
	        {
	        	keyWordsFound[countUserKeyWords] = rs.getString("MOT");
	        	countUserKeyWords++;
	        }
	        
	        String valeur = "1";
	        
	        System.out.println(keyWordsFound[countUserKeyWords]);
	        System.out.println(buildSqlFilterString(keyWordsFound));
	        
	        if(keyWordsFound[0] != null)
	        {
		        int[] arrayOfQuestionsFound = {};
		        String sqlArrayQuestionFilter = buildSqlFilterString(keyWordsFound);
		        
		        System.out.println(UserDatabaseRequests.buildSelectQuestionsByMotsClesIn(sqlArrayQuestionFilter));
		        
		        con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
		        ps = con.prepareStatement(UserDatabaseRequests.buildSelectQuestionsByMotsClesIn(sqlArrayQuestionFilter)); 
		        rs = ps.executeQuery();
		        
	        	while(rs.next())
		        {
	        		int idQuestion = rs.getInt("ID_QUESTION");
	        		
	        		for(int id : arrayOfQuestionsFound)
	        		{
	        			if(idQuestion == id)
		        		{
		        			arrayOfQuestionsFound[idQuestion] ++;
		        			
		        		}
	        			else
	        			{
	        				arrayOfQuestionsFound[idQuestion] = 1;
	        			}
	        		}
		        }
	        	
		        System.out.println("idQuestion found: " + arrayOfQuestionsFound);
		        
	        	int idQuestionFound = 0;
	        	for(int id : arrayOfQuestionsFound)
	    		{
	        		if(id > idQuestionFound)
	        		{
	        			idQuestionFound = id;
	        		}
	    		}
	        	
	        	ps = con.prepareStatement(UserDatabaseRequests.buildSelectReponseByQuestionId(idQuestionFound)); 
		        rs = ps.executeQuery();
		        
		        
	        	while(rs.next())
		        {
	        		botResponse = rs.getString("REPONSE");
		        }
	        	
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
