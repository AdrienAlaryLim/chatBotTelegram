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
		String botResponse = null;
		Boolean questionExists = Boolean.FALSE;
		userMessageText = userMessageText.replace("'", " ");
		userMessageText = userMessageText.replace("\"", " ");
		userMessageText = userMessageText.replace("\\", " ");
		userMessageText = userMessageText.replace("?", " ");
		userMessageText = userMessageText.replace("  ", " ");
		
		try {
			System.out.println(" Try to found question");
			
			String intIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
			
			if(intIdQuestion == null || intIdQuestion.trim().isEmpty())
			{
				throw new Exception(" The question is not existing");
			}
			
			System.out.println(" The id of the question : " + intIdQuestion);
			questionExists = Boolean.TRUE;
			
			botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByQuestionId(intIdQuestion), UserDatabaseRequests.getColumnResponse());
			
			if(botResponse == null || botResponse.trim().isEmpty())
			{
				throw new Exception(" The response of this question is not answered");
			}
		}catch(Exception exectionPrimary)
		{
			try {
				
				System.out.println(" The research block of question has failed");
				if (questionExists.equals(Boolean.FALSE))
				{
					System.out.println(" Insert the new question");
					RequestTreatment.prepareInsertQuestion(userMessageText);
				}
				
				String resultReturnIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
				System.out.println(" The id of the question : " + resultReturnIdQuestion);
				int intIdQuestionInserted = Integer.valueOf(resultReturnIdQuestion);
					
				List<String> splitWords = new ArrayList<String>(Arrays.asList(userMessageText.split(" ")));
		    
			    String sqlArrayKeywordFilter = buildSqlFilterString(splitWords);
			    
			    System.out.println(" Search related keywords matching to : " + sqlArrayKeywordFilter);
			    
			    List<String> keyWordsFound = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter), UserDatabaseRequests.getColumnMot());
			    
			    if(Boolean.FALSE.equals(keyWordsFound.isEmpty()))
		        {
			    	System.out.println(" Search the highest number of matching keywords");
		        	HashMap<String, Integer> mapOfKeywords = new HashMap<>();
		        	
		        	List<String> listOfMaxMatchingKeywords = getHigestFoundKeyword(mapOfKeywords, keyWordsFound, splitWords);
		        	
			    	List<String> listIdKeywords = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectIdMotsClesByMotCle(buildSqlKeywordsFindIdString(listOfMaxMatchingKeywords)), UserDatabaseRequests.getColumnIdMotCle());
		        	
			    	System.out.println(" Return the response with the higher keyword rating");
			    	
			    	int confidenceIndicator = setConfidenceIndicatorToKeywords(listIdKeywords);
			    	
			    	System.out.println(" Give a keyword confidence indicator : " + confidenceIndicator);
			    	
			    	String conflictedKeywords = null;
			    	if(listIdKeywords.size()>1)
			    	{
			    		conflictedKeywords = getStringOfIdConflictedKeywords(listIdKeywords);
			    	}
			    	
			    	System.out.println(" Return the list of conflicted keywords : " + conflictedKeywords);
			    	
			        String idResponseFound = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByMotsCles(listOfMaxMatchingKeywords.get(0)), UserDatabaseRequests.getColumnIdResponse());
			        System.out.println(" For the first occurence of keyword \"" + listOfMaxMatchingKeywords.get(0) + "\" --> response id = " + idResponseFound);
			    	
			        if(idResponseFound == null || idResponseFound.trim().isEmpty())
					{
						throw new Exception(" The response of this question is not answered");
					}
			        
			    	botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByResponseId(idResponseFound), UserDatabaseRequests.getColumnResponse());
			    	System.out.println(" Final bot response : " + botResponse);
			    	
					System.out.println(" Insert the response found to all the questions most represented");
					RequestTreatment.prepareInsertAnswering(intIdQuestionInserted, idResponseFound, confidenceIndicator, conflictedKeywords);
		        }
			}
			catch(Exception exectionSecond)
			{
				botResponse = userMessageText;
			}
		}
		System.out.println("End treatment");
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
			else 
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + " OR mot LIKE '%" + string + "/%'";
	    		count++;
	    	}
	    }
	    return sqlStringArrayFilter;
    }
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * @param arrayString
	 * @return String
	 */
	private static String buildSqlKeywordsFindIdString(List<String> arrayString)
    {
		String sqlStringArrayFilter = "mot = ";
	    int count = 1;
		
	    for(String string : arrayString)
	    {
	    	sqlStringArrayFilter = sqlStringArrayFilter + "'" + string + "'";
	    	
	    	if(arrayString.size() != count)
	    	{
	    		sqlStringArrayFilter = sqlStringArrayFilter + " OR mot = ";
	    		count++;
	    	}
	    }
	    
	    System.out.println(" Keyword for find keyword_id : " + sqlStringArrayFilter);
	    
	    return sqlStringArrayFilter;
    }
	
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
        			
        			System.out.println("     Keyword '"+ keywords + "' found: " + countKeywordFound + " times");
        			if(countKeywordFound == countMaxKeywordFound)
        			{
        				maxMatchingKeywords.add(keywords);
        			}
        			else if(countKeywordFound > countMaxKeywordFound)
            		{
        				countMaxKeywordFound = countKeywordFound;
        				maxMatchingKeywords.clear();
        				maxMatchingKeywords.add(keywords);
            		}
        			
        		}
        	}
        }
        System.out.println(" Map complete Keywords found: " + mapOfKeyword.toString());
		
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
	    
	    System.out.println(" Keywords found: " + mapOfMostQuestionsFound.toString());
		
		return maxMatchingKeywords;
    }
	
	/**
	 * Set the confidence indicator to keywords
	 * @param mapOfQuestionsFound
	 * @return int
	 */
	private static int setConfidenceIndicatorToKeywords(List<String> listOfMaxKeywords)
    {
		int confidenceIndicator = 100;
		System.out.println(" Number of conflicted : " + listOfMaxKeywords.size());
		confidenceIndicator = confidenceIndicator / listOfMaxKeywords.size();
		
		return confidenceIndicator;
    }
	
	/**
	 * Return the string of the conflicted id questions
	 * @param mapOfQuestionsFound
	 * @return String
	 */
	private static String getStringOfIdConflictedKeywords(List<String> listOfKeywordsId)
	{
		String conflictedQuestionsString = "{";
		int count = 1;
		
	    for(String keywords : listOfKeywordsId)
	    {	        
	        conflictedQuestionsString = conflictedQuestionsString + keywords;
	        
	    	if(listOfKeywordsId.size() != count)
	    	{
	    		conflictedQuestionsString = conflictedQuestionsString + ",";
	    		count++;
	    	}else
	    	{
	    		conflictedQuestionsString = conflictedQuestionsString + "}";
	    	}
	    }
	    
	    return conflictedQuestionsString;
	}
}
