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
	    
	    List<String> keyWordsFound = sendSqlQuery(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter), UserDatabaseRequests.getColumnMot());
	    
	    if(Boolean.FALSE.equals(keyWordsFound.isEmpty()))
        {
        	HashMap<String, Integer> mapOfQuestionsFound = new HashMap<>();
	        String sqlArrayQuestionFilter = buildSqlFilterString(keyWordsFound);
	        int higestFoundQuestionId = 0;
	        
	        List<String> listOfIdQuestionsFound = sendSqlQuery(UserDatabaseRequests.buildSelectQuestionsByMotsClesIn(sqlArrayQuestionFilter), UserDatabaseRequests.getColumnIdQuestion());
	    	higestFoundQuestionId = getHigestFoundQuestionId(mapOfQuestionsFound, listOfIdQuestionsFound);
	        
	        botResponse = sendSqlQuery(UserDatabaseRequests.buildSelectReponseByQuestionId(higestFoundQuestionId), UserDatabaseRequests.getColumnResponse()).get(0);
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
	
	/**
	 * Get the id of the question the most represented
	 * @param mapOfQuestionsFound
	 * @param listOfIdQuestion
	 * @return int
	 */
	private static int getHigestFoundQuestionId(HashMap<String, Integer> mapOfQuestionsFound, List<String> listOfIdQuestion)
    {
		int countMaxValue = 0 ;
		for(String idQuestion : listOfIdQuestion)
		{
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
		}
		
		return countMaxValue;
    }
	
	/**
	 * Build and send the SQL query to the database. 
	 * Return a List of string, containing the result of the request.
	 * @param sqlStringRequest 
	 * @param sqlColumn 
	 * @return List<String>
	 */
	private static List<String> sendSqlQuery(String sqlStringRequest, String sqlColumn)
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
	
}
