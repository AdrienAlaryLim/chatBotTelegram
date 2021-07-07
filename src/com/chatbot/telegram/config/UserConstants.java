package com.chatbot.telegram.config;

import java.util.Arrays;
import java.util.List;

public class UserConstants 
{
	// Define your telegram bot token API key
	// ex: 1234567890:MyTelegramBotAPIKeyHash
	private static final String BOT_TOKEN = System.getenv().get("BOT_TOKEN");
	
	// Define your telegram bot username
	// ex: MyTelegramBotUsername
	private static final String BOT_USERNAME = System.getenv().get("BOT_USERNAME");

	// Define your admin telegram channels
	// ex: Arrays.asList("channelAdmin1", "channelAdmin2")
	private static final List<String> LIST_OF_ADMIN_CHANNELS = Arrays.asList("channelAdmin1", "channelAdmin2");
	
	// Define your admin telegram commands
	private static final List<String> LIST_OF_COMMANDS = Arrays.asList("/replayUnanswered");

	// Define your confident balance based on user question accuracy
	// (exemple with 50 - 50, to reach 100 max based value)
	private static final Integer CONFIDENT_QUESTION_BASED = 50;
	
	// Define your confident balance based on keyword accuracy
	// (exemple with 50 - 50, to reach 100 max based value)
	private static final Integer CONFIDENT_KEWORD_FOUND_BASED = 50;
	
	// Define the URL that is used to call GET request
	private static final String URL_GET_REQUEST = System.getenv().get("WEBSITE_URL_REQUEST");
	
	// Define default string when bot can't answer the question
	private static final String RESPONSE_NOT_FOUND = "Je ne suis pas assez renseigné pour répondre à cette question, réessayez dans quelques jours !";

	public static String getBotToken() {
		return BOT_TOKEN;
	}

	public static String getBotUsername() {
		return BOT_USERNAME;
	}

	public static List<String> getListOfAdminChannels() {
		return LIST_OF_ADMIN_CHANNELS;
	}

	public static List<String> getListOfCommands() {
		return LIST_OF_COMMANDS;
	}
	
	public static Integer getConfidentQuestionBased(){
		return CONFIDENT_QUESTION_BASED;
	}
	
	public static Integer getConfidentKeywordFoundBased(){
		return CONFIDENT_KEWORD_FOUND_BASED;
	}
	
	public static String getUrlGetRequest() {
		return URL_GET_REQUEST;
	}

	public static String getResponseNotFound() {
		return RESPONSE_NOT_FOUND;
	}
	
}