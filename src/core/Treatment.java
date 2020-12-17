package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	        
	        List<String> keyWordsFound = new ArrayList<>();
	        while(rs.next())
	        {
	        	keyWordsFound.add(rs.getString("MOT"));
	        	System.out.println(rs.getString("MOT"));
	        }
	        
	        if(Boolean.FALSE.equals(keyWordsFound.isEmpty()))
	        {
	        	HashMap<String, Integer> mapOfQuestionsFound = new HashMap<>();
		        String sqlArrayQuestionFilter = buildSqlFilterString(keyWordsFound);
		        int higestFoundQuestionId = 0;
		        
		        System.out.println(UserDatabaseRequests.buildSelectQuestionsByMotsClesIn(sqlArrayQuestionFilter));
		        
		        con = DriverManager.getConnection(UserConstants.getSqlUrl(), UserConstants.getSqlUser(), UserConstants.getSqlPassword());
		        ps = con.prepareStatement(UserDatabaseRequests.buildSelectQuestionsByMotsClesIn(sqlArrayQuestionFilter)); 
		        rs = ps.executeQuery();
		        
	        	while(rs.next())
		        {
	        		String idQuestion = rs.getString("ID_QUESTION");
	        		higestFoundQuestionId = getHigestFoundQuestionId(mapOfQuestionsFound, idQuestion);
		        }
		        
	        	ps = con.prepareStatement(UserDatabaseRequests.buildSelectReponseByQuestionId(higestFoundQuestionId)); 
		        rs = ps.executeQuery();
		        
		        System.out.println(UserDatabaseRequests.buildSelectReponseByQuestionId(higestFoundQuestionId));
		        
	        	while(rs.next())
		        {
	        		botResponse = rs.getString("RESPONSE");
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
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	private static String buildSqlFilterString(List<String> arrayString)
    {
		String sqlStringArrayFilter = "(";
	    int count = 1;
		
	    for(String string : arrayString)
	    {
	    	sqlStringArrayFilter = sqlStringArrayFilter + "'" + string + "'";
	    	
	    	if(arrayString.size() != count)
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + ",";
	    		count++;
	    	}else
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + ")";
	    	}
	    }
	    
	    return sqlStringArrayFilter;
    }
	
	private static int getHigestFoundQuestionId(HashMap<String, Integer> mapOfQuestionsFound, String idQuestion)
    {
		int countMaxValue = 0 ;
		try {
			int countIdFound = mapOfQuestionsFound.get(idQuestion);
			mapOfQuestionsFound.put(idQuestion, countIdFound+1);
			if(countIdFound > countMaxValue)
    		{
				countMaxValue = Integer.parseInt(idQuestion);
    		}
		}catch(Exception  e) {
			mapOfQuestionsFound.put(idQuestion, 1);
		}
		
		System.out.println("idQuestion found: " + idQuestion);
		return countMaxValue;
    }
	
}
