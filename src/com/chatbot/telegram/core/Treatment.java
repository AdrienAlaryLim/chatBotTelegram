package com.chatbot.telegram.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.chatbot.telegram.config.UserConstants;
import com.chatbot.telegram.config.UserDatabaseRequests;

public class Treatment 
{
	
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
		Map<String, String> questionToTreat = null;
		
		userMessageText = StringUtils.stringNormalizer(userMessageText);
		
		try {
			System.out.println(" Try to found the user question");	
			
			questionToTreat = JsonReader.callJson(UserDatabaseRequests.getRequestQuestionByWords(), UserDatabaseRequests.getUrlColumnQuestion(), userMessageText);
			
			for (Map.Entry<String, String> m : questionToTreat.entrySet())
		    {
				System.out.println("  "+ m.getKey() + ":" + m.getValue());					
		    }
			
			String idQuestion = questionToTreat.getOrDefault("id_question", "");
			
			System.out.println(" id question Found : " + idQuestion);		
			
			// If not found, generate exception
			if(idQuestion == null || idQuestion.trim().isEmpty())
			{
				throw new Exception(" The question is not existing");
			}
			
			// If no error, set questionExists as TRUE
			questionExists = Boolean.TRUE;
			
			try {
				System.out.println(" The id of the question : " + idQuestion);
				
				// Try to found the associated response to the question
				Map<String, String> reponseGet = JsonReader.callJson(UserDatabaseRequests.getRequestReponseByIdQuestion(), UserDatabaseRequests.getUrlColumnIdQuestion(), idQuestion);
				
				
				botResponse = reponseGet.get(UserDatabaseRequests.getColumnIdQuestion());
				
				// If question or response not found, generate error to get the research treatment block
				if(botResponse == null || botResponse.trim().isEmpty())
				{
					throw new Exception(" The response of this question is not answered");
				}
			}catch(Exception noResponse)
			{
				System.out.println(" No response associated to user question");
				botResponse = UserConstants.getResponseNotFound();
			}
		}catch(Exception exectionPrimary)
		{
			try {
				// This bloc is available if the user question is not found or has no response
				System.out.println(" The research block of question has failed");
				
				// If question not found, insert it in the database
				if (questionExists.equals(Boolean.FALSE))
				{
					System.out.println(" Insert the new question");
					
					questionToTreat = JsonReader.callJson(UserDatabaseRequests.getRequestInsertQuestion(), UserDatabaseRequests.getUrlColumnQuestion(), userMessageText);
					
				}
				
				String idQuestion = null;
				for (Map.Entry<String, String> m : questionToTreat.entrySet())
			    {
					System.out.println("  "+ m.getKey() + ":" + m.getValue());	
					if(UserDatabaseRequests.getColumnIdQuestion().equals(m.getKey()))
					{
						idQuestion = m.getValue();
					}
									
			    }
			
				System.out.println(" The id of the question : " + idQuestion);
				
				// Split the user question by the blank character " " in an array
				List<String> splitWords = new ArrayList<String>(Arrays.asList(userMessageText.split(" ")));
				
				// Use this array to build a sql string to find all SOUNDS LIKE words in keyword table
			    String sqlKeywordFilter = StringUtils.buildSqlKeywordFilter(splitWords);
			    
			    System.out.println(" Search related keywords matching to : " + sqlKeywordFilter);
			    
			    // Search all related SOUNDS LIKE words in keyword table
			    
			    List<Map<String, String>> listKeyWordsFoundMap = JsonReader.callListJson(UserDatabaseRequests.getRequestMotCleSounds(), UserDatabaseRequests.getUrlColumnMotsCles(), sqlKeywordFilter);
				
			    System.out.println(" Print the keyword map : " + listKeyWordsFoundMap);
			    
			    if(Boolean.TRUE.equals(listKeyWordsFoundMap.isEmpty()))
		        {
			    	// If no matching keywords, throw exception
			    	throw new Exception(" There are not matching keywords for the given words");
		        }
			    
			    List<String> keyWordsFound = new ArrayList<>();
			    for (Map<String, String> map : listKeyWordsFoundMap)
			    {
			    	keyWordsFound.add(map.get(UserDatabaseRequests.getColumnMot()));
			    }
			    System.out.println(" Print keyword found: " + keyWordsFound);
			    
			    // Search the list of the most confident keywords
		    	System.out.println(" Search the highest number of matching keywords");
		    	LinkedHashMap<String, Integer> mapOfKeywords = new LinkedHashMap<>();
	        	List<String> listOfMaxMatchingKeywords = TreatmentFunctions.getHigestConfidentKeywords(mapOfKeywords, keyWordsFound, splitWords);
	        	
	        	System.out.println(" Search the highest number of matching keywords");
	        	
	        	// Get the list of keywords id found as most confident
	        	List<String> listIdKeywords = new ArrayList<>();
	        	
			    for (String keyword : listOfMaxMatchingKeywords)
			    {
			    	for (Map<String, String> map : listKeyWordsFoundMap)
				    {
			    		if(keyword == map.get(UserDatabaseRequests.getColumnMot()))
		    			{
		    				listIdKeywords.add(map.get(UserDatabaseRequests.getColumnIdMotCle()));
		    			}	
				    }
			    }
			    
		    	// Filter the keywords to have the most confident as first position in the map
		    	System.out.println(" Return the response with the higher keyword rating");
		    	Map.Entry<String,Integer> entry = mapOfKeywords.entrySet().iterator().next();
		    		int confidenceIndicator = entry.getValue();
		    	
		    	System.out.println(" Give a keyword confidence indicator : " + confidenceIndicator);
		    	
		    	// If many keywords found with big relative confident indicator, return a string with all these ID
		    	String conflictedKeywords = null;
		    	if(listOfMaxMatchingKeywords.size()>1)
		    	{
		    		conflictedKeywords = StringUtils.getStringOfIdConflictedKeywords(listIdKeywords);
		    	}
		    	
		    	System.out.println(" Return the string of conflicted keywords : " + conflictedKeywords);
		    	
		    	// Get the id of the associated response to the most confident keyword
		        Map<String, String> mapResponseFound = JsonReader.callJson(UserDatabaseRequests.getRequestReponseByMotCle(), UserDatabaseRequests.getUrlColumnMotsCles(), listOfMaxMatchingKeywords.get(0));
				
		        String idResponseFound = mapResponseFound.get(UserDatabaseRequests.getColumnIdReponse());
		        
		        // If not found throw an exception
		        if(idResponseFound == null || idResponseFound.trim().isEmpty())
				{
					throw new Exception(" The response of this question is not answered");
				}
		        
		        // Set the bot response
		    	botResponse = mapResponseFound.get(UserDatabaseRequests.getColumnResponse());
		    	System.out.println(" Final bot response : " + botResponse);
		    	
		    	// Insert in "repondre" table with all trace of conflicts to help admin corrections if needed
				System.out.println(" Insert the response found to all the questions most represented");
				//RequestTreatment.prepareInsertAnswering(intIdQuestionInserted, idResponseFound, confidenceIndicator, conflictedKeywords);
				JsonReader.insertResponse(idQuestion, idResponseFound, confidenceIndicator, conflictedKeywords);
			}
			catch(Exception exceptionSecond)
			{
				// If any error occured, the bot response has to be the userQuestion.
				System.out.println(" Error no matching keywords found");
				botResponse = userMessageText;
			}
		}
		// Return the botResponse
		System.out.println("End treatment");
		return botResponse;
	}
}
