package user;

import java.util.Arrays;
import java.util.List;

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
	private static final String botToken = System.getenv("BOT_TOKEN");
	
	// Define your telegram bot username
	// ex: MyTelegramBotUsername
	private static final String botUsername = System.getenv("BOT_USERNAME");
	
	// Define your admin telegram channels
	// ex: Arrays.asList("channelAdmin1", "channelAdmin2")
	private static final List<String> listOfAdminChannels = Arrays.asList("channelAdmin1", "channelAdmin2");
	
	// Define your admin telegram commands
	private static final List<String> listOfCommands = Arrays.asList("/replayUnanswered");

	// Define your confident balance based on user question accuracy
	// (exemple with 50 - 50, to reach 100 max based value)
	private static final Integer CONFIDENT_QUESTION_BASED = 50;
	
	// Define your confident balance based on keyword accuracy
	// (exemple with 50 - 50, to reach 100 max based value)
	private static final Integer CONFIDENT_KEWORD_FOUND_BASED = 50;

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

	public static List<String> getListOfAdminChannels() {
		return listOfAdminChannels;
	}

	public static List<String> getListOfCommands() {
		return listOfCommands;
	}
	
	public static Integer getConfidentQuestionBased(){
		return CONFIDENT_QUESTION_BASED;
	}
	
	public static Integer getConfidentKeywordFoundBased(){
		return CONFIDENT_KEWORD_FOUND_BASED;
	}
}