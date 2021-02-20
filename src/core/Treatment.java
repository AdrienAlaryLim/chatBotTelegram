package core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import user.UserDatabaseRequests;

public class Treatment {
	
	/**
	 * Return the response that the bot have to send. 
	 * @param userMessageText the message sent by the user
	 * @return
	 */
	public static String returnMessage(String userMessageText)
	{		
		System.out.println("Begin treatment");
		String botResponse = userMessageText;
		Boolean questionExists = Boolean.FALSE;
		userMessageText = userMessageText.replace("'", " ");
		userMessageText = userMessageText.replace("\"", " ");
		userMessageText = userMessageText.replace("\\", " ");
		userMessageText = userMessageText.replace("?", " ");
		
		try {
			System.out.println("Try to found question");
			
			String resultReturnIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
			System.out.println("The id of the question : " + resultReturnIdQuestion);
			int intIdQuestion = Integer.valueOf(resultReturnIdQuestion);
			
			questionExists = Boolean.TRUE;
			
			botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByQuestionId(intIdQuestion), UserDatabaseRequests.getColumnResponse());
			
			System.out.println("Answer of that question : " + botResponse);
			
			if(botResponse == "" || botResponse == null || botResponse == RequestTreatment.ERROR)
			{
				throw new Exception("The response of this question is not answered");
			}
		}catch(Exception e)
		{
			System.out.println("The research block of question has failed");
			if (questionExists.equals(Boolean.FALSE))
			{
				System.out.println("Insert the new question");
				RequestTreatment.prepareInsertQuestion(userMessageText);
			}
			
			String resultReturnIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
			System.out.println("The id of the question : " + resultReturnIdQuestion);
			int intIdQuestionInserted = Integer.valueOf(resultReturnIdQuestion);
				
			List<String> splitWords = new ArrayList<String>(Arrays.asList(userMessageText.split(" ")));
	    
		    String sqlArrayKeywordFilter = buildSqlFilterString(splitWords);
		    
		    
		    System.out.println("Search related keywords in database");
		    List<String> keyWordsFound = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter), UserDatabaseRequests.getColumnMot());
		    
		    if(Boolean.FALSE.equals(keyWordsFound.isEmpty()))
	        {
		    	System.out.println("Search the highest number of matching keywords");
	        	//HashMap<String, Integer> mapOfQuestionsFound = new HashMap<>();
		        //String[] sqlArrayQuestionFilter = buildSqlFilterString(keyWordsFound);
		        //System.out.println("Keywords found: " + sqlArrayQuestionFilter);
		        
	        	//getKeywordMostRepresented();
	        	
	        	HashMap<String, Integer> mapOfKeywords = new HashMap<>();
	        	
	        	List<String> listOfMaxMatchingKeywords = getHigestFoundKeyword(mapOfKeywords, keyWordsFound, splitWords);
	        	
	        	
		        int higestFoundQuestionId = 0;
		        
		        
		        List<String> listOfIdQuestionsFound = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectReponseByMotsCles(listOfMaxMatchingKeywords.get(0)), UserDatabaseRequests.getColumnIdResponse());
		    	
		        //List<String> listOfIdQuestionsFound = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectReponseByMotsCles(listOfMaxMatchingKeywords.get(0)), UserDatabaseRequests.getColumnResponse());
		    	
		        //botResponse = listOfIdQuestionsFound.get(0);
		        
		       // higestFoundQuestionId = getHigestFoundQuestionId(mapOfQuestionsFound, listOfIdQuestionsFound);
		    	
		    	/*System.out.println("Map questions found (idQuestion=numberOfKeywordsFound): " + mapOfQuestionsFound.toString());
		    	
		    	System.out.println("Return the response with the higher rating");
		    	
		    	int confidenceIndicator = setConfidenceIndicator(mapOfQuestionsFound, higestFoundQuestionId);
		    	String conflictedQuestions = null;
		    	if(mapOfQuestionsFound.size()>1)
		    	{
		    		conflictedQuestions = getStringOfIdConflictedQuestions(mapOfQuestionsFound);
		    	}
		    	*/
		    	int idReponse = Integer.parseInt(RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByQuestionId(Integer.parseInt(listOfIdQuestionsFound.get(0))), UserDatabaseRequests.getColumnIdResponse()));
		        botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByQuestionId(idReponse), UserDatabaseRequests.getColumnResponse());
		        
		        
				System.out.println("Insert the response found to all the questions most represented");
				RequestTreatment.prepareInsertAnswering(intIdQuestionInserted, idReponse, 100, null);
				//RequestTreatment.prepareInsertAnswering(intIdQuestionInserted, idReponse, confidenceIndicator, conflictedQuestions);
	        }
		}
		if(botResponse.equals(RequestTreatment.ERROR))
		{
			
        	botResponse = userMessageText;
		}
		
		return botResponse;
	}
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	private static String buildSqlFilterString(List<String> arrayString)
    {
		String sqlStringArrayFilter = "";
		int count = 1;
		 
		for(String string : arrayString)
	    {
			if(count == 1)
			{
				sqlStringArrayFilter = "LIKE '%" + string + "/%'";
				count ++;
			}
			else if(arrayString.size() != count)
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + " OR mot LIKE '%" + string + "/%'";
	    		count++;
	    	}
	    }
	    
		/*if(arrayString.length > 1)
    	{
		    for(int i=1; i<arrayString.length; i++)
		    {
		    	String stringIterate = arrayString[i];
		    	
		    	if(stringIterate.length() > 3) 
		    	{
			    	if(stringIterate.endsWith("s") || stringIterate.endsWith("e"))
			    	sqlStringArrayFilter = sqlStringArrayFilter + " OR mot LIKE '%" + stringIterate.substring( 1, stringIterate.length() - 1 ) + ";%'";
			    	
		    	}
		    	else 
		    	{
		    		sqlStringArrayFilter = sqlStringArrayFilter + " OR mot LIKE '%" + arrayString[i] + "?;%'";
		    	}
		    	sqlStringArrayFilter = sqlStringArrayFilter + " OR mot LIKE '%" + arrayString[i] + ";%'";
		    }
    	}*/
	    return sqlStringArrayFilter;
    }
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	/*private static String buildSqlFilterString(String[] arrayString)
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
    }*/
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	/*private static String buildSqlFilterString(List<String> arrayString)
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
    }*/
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	/*private static String buildSqlFilterString(List<String> arrayString)
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
    }*/
	
	
	/**
	 * Get the keyword the most represented
	 * @param mapOfQuestionsFound
	 * @param listOfIdQuestion
	 * @return int
	 */
	private static List<String> getHigestFoundKeyword(HashMap<String, Integer> mapOfKeyword, List<String> listOfKeyword, List<String> splitWords)
    {
		
		List<String> maxMatchingKeywords = new ArrayList<>();
		
		//int keywordMostRepresented = 0;
		int countMaxKeywordFound = 0;
		
		
		for(String keywords : listOfKeyword)
        {
        	mapOfKeyword.put(keywords,0);
        	
        	List<String> wordSplitted = new ArrayList<String>(Arrays.asList(keywords.split("/")));
        	int countKeywordFound = mapOfKeyword.get(keywords);
        	
        	for(String word : wordSplitted)
        	{
        		if(splitWords.contains(word)) 
        		{
        			mapOfKeyword.put(keywords, mapOfKeyword.get(keywords) + 1); 
        			countKeywordFound++;
        			
        			System.out.println("Keyword '"+ keywords + "' found: " + countKeywordFound + " times");
        			if(countKeywordFound == countMaxKeywordFound)
        			{
        				maxMatchingKeywords.add(keywords);
        			}
        			else if(countKeywordFound > countMaxKeywordFound)
            		{
        				countMaxKeywordFound = countKeywordFound;
        				//keywordMostRepresented = ;
        				maxMatchingKeywords.clear();
        				maxMatchingKeywords.add(keywords);
            		}
        			
        		}
        	}
        }
        System.out.println("Keywords found: " + mapOfKeyword.toString());
		
		HashMap<String, Integer> mapOfMostQuestionsFound = new HashMap<>();
		
		Iterator<Entry<String, Integer>> it = mapOfKeyword.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
	        if(pair.getValue().equals(countMaxKeywordFound))
			{
				mapOfMostQuestionsFound.put(pair.getKey(),(Integer) pair.getValue());
			}
	        it.remove();
	    }
	    
	    mapOfKeyword.clear();
	    mapOfKeyword.putAll(mapOfMostQuestionsFound);
		
		return maxMatchingKeywords;
    }
	
	
	/**
	 * Get the id of the question the most represented
	 * @param mapOfQuestionsFound
	 * @param listOfIdQuestion
	 * @return int
	 */
	private static int getHigestFoundQuestionId(HashMap<String, Integer> mapOfQuestionsFound, List<String> listOfIdQuestion)
    {
		int idQuestionMostRepresented = 0;
		int countMaxValue = 0 ;
		for(String idQuestion : listOfIdQuestion)
		{
			try {
			int countIdFound = mapOfQuestionsFound.get(idQuestion) + 1;
			mapOfQuestionsFound.put(idQuestion, countIdFound);
			
			System.out.println("Number of id '"+ idQuestion + "' found: " + countIdFound + " times");
			if(countIdFound > countMaxValue)
    		{
				countMaxValue = countIdFound;
				idQuestionMostRepresented = Integer.parseInt(idQuestion);
    		}
			}catch(Exception  e) {
				System.out.println("Add Id Question : " + idQuestion);
				mapOfQuestionsFound.put(idQuestion, 1);
			}
		}
		
		HashMap<String, Integer> mapOfMostQuestionsFound = new HashMap<>();
		
		Iterator<Entry<String, Integer>> it = mapOfQuestionsFound.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
	        if(pair.getValue().equals(countMaxValue))
			{
				mapOfMostQuestionsFound.put(pair.getKey(),(Integer) pair.getValue());
			}
	        it.remove();
	    }
	    
		mapOfQuestionsFound.clear();
		mapOfQuestionsFound.putAll(mapOfMostQuestionsFound);
		
		return idQuestionMostRepresented;
    }
	
	/**
	 * Set the confidence indicator
	 * @param mapOfQuestionsFound
	 * @param countMaxValue
	 * @return int
	 */
	private static int setConfidenceIndicator(HashMap<String, Integer> mapOfQuestionsFound, int countMaxValue)
    {
		int confidenceIndicator = 100;
    
		confidenceIndicator = confidenceIndicator / mapOfQuestionsFound.size();
		
		return confidenceIndicator;
    }
	
	/**
	 * Return the string of the conflicted id questions
	 * @param mapOfQuestionsFound
	 * @return String
	 */
	private static String getStringOfIdConflictedQuestions(HashMap<String, Integer> mapOfQuestionsFound)
	{
		String conflictedQuestionsString = "{";
		int count = 0;
		Iterator<Entry<String, Integer>> it = mapOfQuestionsFound.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry<String, Integer> pair = (Map.Entry<String, Integer>)it.next();
	        
	        conflictedQuestionsString = conflictedQuestionsString + pair.getKey();
	        
	    	if(mapOfQuestionsFound.size() != count)
	    	{
	    		conflictedQuestionsString = conflictedQuestionsString + ",";
	    		count++;
	    	}else
	    	{
	    		conflictedQuestionsString = conflictedQuestionsString + "}";
	    	}
	        
	        it.remove();
	    }
	    
	    return conflictedQuestionsString;
	}
}
