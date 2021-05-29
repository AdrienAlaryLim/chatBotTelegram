package com.chatbot.telegram;

import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import com.chatbot.telegram.config.UserConstants;
import com.chatbot.telegram.config.UserDatabaseRequests;
import com.chatbot.telegram.core.RequestTreatment;
import com.chatbot.telegram.core.Treatment;

public class ChatBotInterract extends TelegramLongPollingBot 
{
	String sqlUrl = UserConstants.getSqlUrl();
	
	@Override
    public String getBotToken() {
        return UserConstants.getBotToken();
    }
	
	@Override
    public String getBotUsername() {
        return UserConstants.getBotUsername();
    }
	
	@Override
	public void onUpdateReceived(Update update)
	{
		// We check if the update has a message and the message has text
	    if (update.hasMessage() && update.getMessage().hasText()) 
	    {
	        // Set variables
	        String userMessageText = update.getMessage().getText();
	        long chatId = update.getMessage().getChatId();
	        String strChatId = String.valueOf(chatId);
	        
	        // Create a message object object
	        SendMessage message = new SendMessage();
	        
	        // Check if the message is a /command from an administrator channel
	        if(userMessageText.charAt(0) == '/' && UserConstants.getListOfCommands().contains(userMessageText) 
	        		&& UserConstants.getListOfAdminChannels().contains(strChatId))
	        {
	        	// Switch for position in command arrayList
	        	switch (UserConstants.getListOfAdminChannels().indexOf(strChatId)) {
				case 0:
					List<String> listOfQuestions = new ArrayList<>();
		        	
		        	// Collect all the question where the bot hasn't answer
		        	listOfQuestions = RequestTreatment.returnQueryArrayResponse(UserDatabaseRequests.buildSelectQuestionsUnanswered(), UserDatabaseRequests.getColumnQuestion());
		        
		        	// Function to play again questions that the bot has not give any answer
		        	replayQuestionsUnanswered(listOfQuestions, update);
					break;

				default:
					break;
				}
	        }
	        
	        // Set the channelId to reply on the right user
	        message.setChatId(strChatId);
	        
	        // Set the bot response from the user message based
	        message.setText(Treatment.returnMessage(userMessageText));
	        
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void replayQuestionsUnanswered(List<String> listOfQuestions, Update update)
	{
		for(String question : listOfQuestions)
		{
	        // Set variables
	        String userMessageText = question;
	        long chatId = update.getMessage().getChatId();
	        String strChatId = String.valueOf(chatId);
	        
	        // Create a message object object
	        SendMessage message = new SendMessage();
	        
	        message.setChatId(strChatId);
	           
	        message.setText(Treatment.returnMessage(userMessageText));
	        
	        try {
	            execute(message); // Sending our message object to user
	        } catch (TelegramApiException e) {
	            e.printStackTrace();
	        }
		}
	}
}
