package user;

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

	public static String getSqlUrl() {
		return sqlUrl;
	}

	public static String getSqlUser() {
		return sqlUser;
	}

	public static String getSqlPassword() {
		return sqlPassword;
	}

	public static String getBotToken() {
		return botToken;
	}

	public static String getBotUsername() {
		return botUsername;
	}
}