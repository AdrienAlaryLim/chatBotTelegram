package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDatabaseRequests {
	
	private static final String selectQuestion1 = "SELECT * FROM questions WHERE id_question = 1";
	
	private static final String selectMotsClesIn = "SELECT * FROM mots_cles WHERE mot IN ";
	
	/**
	 * 
	 * @return String selectQuestion1
	 */
	public static String getSelectQuestion1() 
	{
		return selectQuestion1;
	}

	/**
	 * Build the request "select in mots cles IN" with the given array
	 * @param stringFilterArray the filtering array
	 * @return String selectMotsClesIn
	 */
	public static String buildSelectMotsClesIn(String stringFilterArray) {
		return selectMotsClesIn + stringFilterArray;
	}
}
