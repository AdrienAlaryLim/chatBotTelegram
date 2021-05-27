package com.chatbot.telegram.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
		
		userMessageText = StringUtils.stringNormalizer(userMessageText);
		
		try {
			// Research the id question in the question table
			String intIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
			
			// If not found, generate exception
			if(intIdQuestion == null || intIdQuestion.trim().isEmpty())
			{
				throw new Exception(" The question is not existing");
			}
			
			// If no error, set questionExists as TRUE
			questionExists = Boolean.TRUE;
			System.out.println(" The id of the question : " + intIdQuestion);
			
			// Try to found the associated response to the question
			botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByQuestionId(intIdQuestion), UserDatabaseRequests.getColumnResponse());
			
			// If question or response not found, generate error to get the research treatment block
			if(botResponse == null || botResponse.trim().isEmpty())
			{
				throw new Exception(" The response of this question is not answered");
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
					RequestTreatment.prepareInsertQuestion(userMessageText);
				}
				
				// Get the id of the inserted question
				String resultReturnIdQuestion = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectQuestionByWholeWords(userMessageText), UserDatabaseRequests.getColumnIdQuestion());
				System.out.println(" The id of the question : " + resultReturnIdQuestion);
				int intIdQuestionInserted = Integer.valueOf(resultReturnIdQuestion);
				
				// Split the user question by the blank character " " in an array
				List<String> splitWords = new ArrayList<String>(Arrays.asList(userMessageText.split(" ")));
				
				// Use this array to build a sql string to find all SOUNDS LIKE words in keyword table
			    String sqlArrayKeywordFilter = StringUtils.buildSqlFilterString(splitWords);
			    System.out.println(" Search related keywords matching to : " + sqlArrayKeywordFilter);
			    
			    // Search all related SOUNDS LIKE words in keyword table
			    List<String> keyWordsFound = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectMotsClesIn(sqlArrayKeywordFilter), UserDatabaseRequests.getColumnMot());
			    
			    if(Boolean.TRUE.equals(keyWordsFound.isEmpty()))
		        {
			    	// If no matching keywords, throw exception
			    	throw new Exception(" There are not matching keywords for the given words");
		        }
			    
			    // Search the list of the most confident keywords
		    	System.out.println(" Search the highest number of matching keywords");
		    	LinkedHashMap<String, Integer> mapOfKeywords = new LinkedHashMap<>();
	        	List<String> listOfMaxMatchingKeywords = TreatmentFunctions.getHigestConfidentKeywords(mapOfKeywords, keyWordsFound, splitWords);
	        	
	        	// Get the list of keywords id found as most confident
		    	List<String> listIdKeywords = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectIdMotsClesByMotCle(StringUtils.buildSqlKeywordsFindIdString(listOfMaxMatchingKeywords)), UserDatabaseRequests.getColumnIdMotCle());
	        	
		    	// Filter the keywords to have the most confident as first position in the map
		    	System.out.println(" Return the response with the higher keyword rating");
		    	Map.Entry<String,Integer> entry = mapOfKeywords.entrySet().iterator().next();
		    		int confidenceIndicator = entry.getValue();
		    	
		    	System.out.println(" Give a keyword confidence indicator : " + confidenceIndicator);
		    	
		    	// If many keywords found with big relative confident indicator, return a string with all these ID
		    	String conflictedKeywords = null;
		    	if(listIdKeywords.size()>1)
		    	{
		    		conflictedKeywords = StringUtils.getStringOfIdConflictedKeywords(listIdKeywords);
		    	}
		    	
		    	System.out.println(" Return the string of conflicted keywords : " + conflictedKeywords);
		    	
		    	// Get the id of the associated response to the most confident keyword
		        String idResponseFound = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByMotsCles(listOfMaxMatchingKeywords.get(0)), UserDatabaseRequests.getColumnIdResponse());
		        System.out.println(" For the first occurence of keyword \"" + listOfMaxMatchingKeywords.get(0) + "\" --> response id = " + idResponseFound);
		    	
		        // If not found throw an exception
		        if(idResponseFound == null || idResponseFound.trim().isEmpty())
				{
					throw new Exception(" The response of this question is not answered");
				}
		        
		        // Set the bot response
		    	botResponse = RequestTreatment.returnQueryStringResponse(UserDatabaseRequests.buildSelectReponseByResponseId(idResponseFound), UserDatabaseRequests.getColumnResponse());
		    	System.out.println(" Final bot response : " + botResponse);
		    	
		    	// Insert in "repondre" table with all trace of conflicts to help admin corrections if needed
				System.out.println(" Insert the response found to all the questions most represented");
				RequestTreatment.prepareInsertAnswering(intIdQuestionInserted, idResponseFound, confidenceIndicator, conflictedKeywords);
		        
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
