package core;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import user.UserConstants;

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
