package user;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class of user constants, values can be used in whole code
 * @author Adrien
 *
 */
public class UserConstants 
{
	// Define your url to connect to your database with your database name
	// ex : jdbc:mysql://127.0.0.1:3306/mydb_name
	
	private static final String sqlUrl = "jdbc:mysql://127.0.0.1:3306/mydb_name";
	
	// Your database user
	// ex : john
	private static final String sqlUser = "john";
	
	// Your password to acces database
	// ex : Pa$$w0rd
	private static final String sqlPassword = "Pa$$w0rd";
	
	// Define your telegram bot token API key
	// ex: 1234567890:MyTelegramBotAPIKeyHash
	private static final String botToken = "1234567890:MyTelegramBotAPIKeyHash";
	
	// Define your telegram bot username
	// ex: MyTelegramBotUsername
	private static final String botUsername = "MyTelegramBotUsername";
	
	/**
	 * Get the sql Url path to the database
	 * @return String sqlUrl
	 */
	public static String getSqlUrl() {
		return sqlUrl;
	}

	/**
	 * Get the sql user of the database
	 * @return String sqlUser
	 */
	public static String getSqlUser() {
		return sqlUser;
	}
	
	/**
	 * Get the sql password of the database
	 * @return String sqlPassword
	 */
	public static String getSqlPassword() {
		return sqlPassword;
	}
	
	/**
	 * Get the bot API token in telegram
	 * @return String botToken
	 */
	public static String getBotToken() {
		return botToken;
	}

	/**
	 * Get the bot user name in telegram
	 * @return String botUsername
	 */
	public static String getBotusername() {
		return botUsername;
	}
}
