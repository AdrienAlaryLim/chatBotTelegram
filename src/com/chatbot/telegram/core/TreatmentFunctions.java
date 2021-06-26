package com.chatbot.telegram.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.language.Soundex;

import com.chatbot.telegram.config.UserConstants;

public class TreatmentFunctions 
{		
	/**
	 * Get the keyword the most confident
	 * @param mapOfKeyword
	 * @param sqlResultListOfKeyword
	 * @param userSplitWords
	 * @return List<String>
	 */
	public static List<String> getHigestConfidentKeywords(LinkedHashMap<String, Integer> mapOfKeyword, List<String> sqlResultListOfKeyword, List<String> userSplitWords)
    {
		// Initialize values for the treatment
		Soundex soundex = new Soundex();
		int countMaxKeywordFound = 0;
		List<String> maxMatchingKeywords = new ArrayList<>();
		List<String> resultMaxKeywordConfident = new ArrayList<>();
		List<String> sdxUserSplitWords = new ArrayList<>();
		List<Integer> nbWordsInKeyword = new ArrayList<>();
		
		// For each word found in the userQuestion, encode their soundex value in a list
		for(String userWord : userSplitWords)
		{
			sdxUserSplitWords.add(soundex.encode(userWord));
		}
		
		// Treatment to apply for each keyword found in sqlResponse
		for(String keywords : sqlResultListOfKeyword)
        {
			// Put the keyword in map, with counter found set as 0
        	mapOfKeyword.put(keywords,0);
        	
        	// Split the given keyword in an array to treat them one by one
        	List<String> wordSplitted = new ArrayList<String>(Arrays.asList(keywords.split("/")));
        	int countKeywordFound = Math.round(mapOfKeyword.get(keywords));
        	
        	// For each word in the given keyword, find soundex, compare and count
        	for(String word : wordSplitted)
        	{
        		// Encode the word soundex value
        		String phoneticValue = soundex.encode(word);
    			
        		// If the soundex value is already found, increment counter
        		if(sdxUserSplitWords.contains(phoneticValue))
        		{
        			// For the matching keyword, the counter takes +1
        			mapOfKeyword.put(keywords, mapOfKeyword.get(keywords) + 1); 
        			countKeywordFound++;
        			
        			System.out.println("     Keyword '"+ keywords + "' found: " + countKeywordFound + " times");
        			
        			// If the most found value is reached, add the sqlKeyword
        			if(countKeywordFound == countMaxKeywordFound)
        			{
        				maxMatchingKeywords.add(keywords);
        			}
        			// If sqlKeyword is over the max value, reset the max value with the added keyword
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
		
		// Calculate the confident indicator of the highest found values
		resultMaxKeywordConfident = setSetConfidence(mapOfKeyword, userSplitWords.size());
		
		return resultMaxKeywordConfident;
    }
	
	/**
	 * Set confidence indicator in the map. Based on 100 = max value, 0 = min value
	 * @param mapOfKeyword
	 * @param nbInputPhraseWords
	 * @return List<String>
	 */
	public static List<String> setSetConfidence(LinkedHashMap<String, Integer> mapOfKeyword, Integer nbInputPhraseWords)
    {
		List<String> resultKeepKeyword = new ArrayList<>();
		Integer maxConfidentKeep = 0;
		
		// For each represented keywords, calculate the confidence
	    for (Map.Entry<String, Integer> m : mapOfKeyword.entrySet())
	    {
	    	// Get the number of words in a given keyword
	    	Integer nbTotalKWordSplitted = new ArrayList<String>(Arrays.asList(m.getKey().split("/"))).size();
	    	
	    	// Give a confident value for the given keyword
	    	Integer confident = calculConfidentKwFound(m.getValue(), nbInputPhraseWords, nbTotalKWordSplitted);
	    	
	    	m.setValue(confident);
	    	
	    	if(confident > maxConfidentKeep)
	    	{
	    		// Set the maximum confident value
	    		maxConfidentKeep = confident;
	    	}
	    }
	    
	    // Suppress confident values less than 50% of the maximum found in the map
	    Integer halfConsistantKeep = Math.round(maxConfidentKeep / 2);
	    
	    mapOfKeyword.entrySet().removeIf(entry -> entry.getValue() < halfConsistantKeep);

	    // Filter map as DESC order
	    mapOfKeyword.entrySet()
	    .stream()
	    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())) 
	    .forEachOrdered(x -> {
	    		mapOfKeyword.put(x.getKey(), x.getValue());
	    		resultKeepKeyword.add(x.getKey());
	    	});
	    
	    System.out.println(" Keywords confident found: " + mapOfKeyword.toString());
	    
	    // Return the confidence values refered to the keywords
	    return resultKeepKeyword;
    }
	
	/**
	 * Give a value that indicates the confidence level 0 -> 100 (100 = max)
	 * @param nbKwFound
	 * @param valueToDecayUserQuestion 
	 * @param valueToDecayKwFound
	 * @return
	 */
	public static Integer calculConfidentKwFound(Integer nbKwFound, Integer valueToDecayUserQuestion, Integer valueToDecayKwFound)
	{
		
		if(valueToDecayUserQuestion == 0 || valueToDecayUserQuestion == null)
		{
			return 0;
		}
		
		// Define confidence value based on keyword found in the user question (50 max as default)
		Integer confidenceUserQuestion = UserConstants.getConfidentQuestionBased() * nbKwFound / valueToDecayUserQuestion;
		
		// Define confidence value based on occurence found keyword (50 max as default)
		Integer confidenceKwFound = UserConstants.getConfidentKeywordFoundBased() * nbKwFound / valueToDecayKwFound;
		
		// Add both confident values to get a 100 based confidence
		Integer result = confidenceUserQuestion + confidenceKwFound;
		
		return result;
	}
	
	/**
	 * Give a list of all values from a map
	 * @param Map
	 * @return 
	 */
	public static <T> List<T> getValuesFromMap(Map<?, T> map) {
	    return new ArrayList<>(map.values());
	}
}
