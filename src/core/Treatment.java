package core;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.Soundex;

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
		System.out.println("Begin treatment");
		String botResponse = null;
		Boolean questionExists = Boolean.FALSE;		
		userMessageText = Normalizer.normalize(userMessageText, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		userMessageText = userMessageText.toLowerCase();
		userMessageText = userMessageText.replaceAll("[^A-Za-z0-9 ]"," ");
		userMessageText = userMessageText.trim().replaceAll(" +", " ");
		
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
			    	LinkedHashMap<String, Integer> mapOfKeywords = new LinkedHashMap<>();
		        	
		        	List<String> listOfMaxMatchingKeywords = getHigestFoundKeyword(mapOfKeywords, keyWordsFound, splitWords);
		        	
			    	List<String> listIdKeywords = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectIdMotsClesByMotCle(buildSqlKeywordsFindIdString(listOfMaxMatchingKeywords)), UserDatabaseRequests.getColumnIdMotCle());
		        	
			    	System.out.println(" Return the response with the higher keyword rating");
			    	
			    	Map.Entry<String,Integer> entry = mapOfKeywords.entrySet().iterator().next();
			    		int confidenceIndicator = entry.getValue();
			    	
			    	
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
				System.out.println(" Error no matching keywords found");
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
				sqlStringArrayFilter = "SOUNDS LIKE '%" + string + "/%'";
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
	private static List<String> getHigestFoundKeyword(LinkedHashMap<String, Integer> mapOfKeyword, List<String> sqlResultListOfKeyword, List<String> userSplitWords)
    {
		
		List<String> maxMatchingKeywords = new ArrayList<>();
		List<String> resultMaxKeywordConfident = new ArrayList<>();
		
		int countMaxKeywordFound = 0;
		List<Integer> nbWordsInKeyword = new ArrayList<Integer>();
		Soundex soundex = new Soundex();
		
		List<String> sdxUserSplitWords = new ArrayList<String>();
		
		for(String userWord : userSplitWords)
		{
			sdxUserSplitWords.add(soundex.encode(userWord));
		}
		
		for(String keywords : sqlResultListOfKeyword)
        {
			
        	mapOfKeyword.put(keywords,0);
        	
        	List<String> wordSplitted = new ArrayList<String>(Arrays.asList(keywords.split("/")));
        	int countKeywordFound = Math.round(mapOfKeyword.get(keywords));
        	
        	for(String word : wordSplitted)
        	{
        		String phoneticValue = soundex.encode(word);
    			
        		if(sdxUserSplitWords.contains(phoneticValue))
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
        				nbWordsInKeyword.add(wordSplitted.size());
            		}
        		}
        	}
        }
		
		resultMaxKeywordConfident = setSetConfidence(mapOfKeyword, userSplitWords.size());
		
		return resultMaxKeywordConfident;
    }
	
	/**
	 * Set confidence indicator in the map. Based on 100 = max value
	 * @param mapOfKeyword
	 * @param nbInputPhraseWords
	 */
	private static List<String> setSetConfidence(LinkedHashMap<String, Integer> mapOfKeyword, Integer nbInputPhraseWords)
    {
		List<String> resultKeepKeyword = new ArrayList<>();
		Integer maxConfidentKeep = 0;
		
	    for (Map.Entry<String, Integer> m : mapOfKeyword.entrySet())
	    {
	    	Integer nbTotalKWordSplitted = new ArrayList<String>(Arrays.asList(m.getKey().split("/"))).size();
	    	
	    	Integer confident = calculConfidentKwFound(m.getValue(), nbInputPhraseWords, nbTotalKWordSplitted);
	    	
	    	m.setValue(confident);
	    	
	    	if(confident > maxConfidentKeep)
	    	{
	    		maxConfidentKeep = confident;
	    	}
	    }
	    
	    //Keep -50% confidence of the max confidence in the map
	    Integer halfConsistantKeep = Math.round(maxConfidentKeep / 2);
	    
	    mapOfKeyword.entrySet().removeIf(entry -> entry.getValue() < halfConsistantKeep);
	    System.out.println(" Keywords condident found: " + mapOfKeyword.toString());

	    mapOfKeyword.entrySet()
	    .stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
	    .forEachOrdered(x -> {
	    		mapOfKeyword.put(x.getKey(), x.getValue());
	    		resultKeepKeyword.add(x.getKey());
	    	});
	    
	    System.out.println(" Keywords condident found: " + mapOfKeyword.toString());
	    
	    return resultKeepKeyword;
    }
	
	private static Integer calculConfidentKwFound(Integer nbKwFound, Integer valueToDecayUserQuestion, Integer valueToDecayKwFound)
	{
		
		if(valueToDecayUserQuestion == 0 || valueToDecayUserQuestion == null)
		{
			return 0;
		}
		
		Integer confidenceUserQuestion = UserConstants.getConfidentQuestionBased() * nbKwFound / valueToDecayUserQuestion;
		Integer confidenceKwFound = UserConstants.getConfidentKeywordFoundBased() * nbKwFound / valueToDecayKwFound;
		Integer result = confidenceUserQuestion + confidenceKwFound;
		
		return result;
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
