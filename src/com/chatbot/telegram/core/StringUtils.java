package com.chatbot.telegram.core;

import java.text.Normalizer;
import java.util.List;

public class StringUtils 
{
	/**
	 * Normalize a string to get non utf-8 characters
	 * This allows to apply functions based on character analysis, non UTF-8 compatible
	 * @param string
	 * @return
	 */
	public static String stringNormalizer(String string)
	{
		string = Normalizer.normalize(string, Normalizer.Form.NFD).replaceAll("[\u0300-\u036F]", "");
		string = string.toLowerCase();
		string = string.replaceAll("[^A-Za-z0-9 ]"," ");
		string = string.trim().replaceAll(" +", " ");
		
		return string;
	}
	
	/**
	 * Build a string shaped in array to be used as a filter in sql query
	 * Example = "mot = SOUNDS LIKE '%mot1/%' OR mot = OR mot LIKE '%mot2/%'"
	 * @param arrayString
	 * @return String
	 */
	public static String buildSqlFilterString(List<String> arrayString)
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
	 * Example = "mot = 'mot1' OR mot = 'mot2'"
	 * @param arrayString
	 * @return String
	 */
	public static String buildSqlKeywordsFindIdString(List<String> arrayString)
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
	 * Return the string of the conflicted id questions
	 * Example = {id1,id2,id3,idN}
	 * @param mapOfQuestionsFound
	 * @return String
	 */
	public static String getStringOfIdConflictedKeywords(List<String> listOfKeywordsId)
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
