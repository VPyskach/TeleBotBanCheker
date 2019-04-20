package com.job.telebotv2;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.slf4j.Logger;

@Component
public class Bot extends TelegramLongPollingBot {


    @Autowired
    ChatIdRepos chatIdRepos;

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println("get message /start");
        addChatId(update.getMessage().getChatId().toString());
    }

    @Override
    public String getBotUsername() {
        return "AppBanCheckBot";
    }

    @Override
    public String getBotToken() {
        return "768833536:AAEIzKRHUQV2jY3VTDNlZCuikp-3o6O_r24";
    }

    void addChatId(String text){
        ChatId chatId = new ChatId(text);
        chatIdRepos.deleteAll();
        chatIdRepos.save(chatId);
    }
}
