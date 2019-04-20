package com.job.telebotv2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@SpringBootApplication
public class SpringClass{

    @Autowired
    ChatIdRepos chatIdRepos;

    private static final Logger log = LoggerFactory.getLogger(SpringClass.class);
    @Bean
    public CommandLineRunner demo(UrlRepos repository, ChatIdRepos chatIdRepos) {
        return (args) -> {

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        if (repository.findAll() != null) {
                            Iterable<Url> iUrls = repository.findAll();
                            List<Url> urls = StreamSupport
                                    .stream(iUrls.spliterator(), false)
                                    .collect(Collectors.toList());

                            for (int i = 0; i < urls.size(); i++) {
                                log.info(urls.get(i).getUrl());
                                log.info(String.valueOf(urls.get(i).isStatus()));
                                if (urls.get(i).isStatus()) {
                                    if (setRequestCode(urls.get(i).getUrl()) == 404) {
                                        sendMessage(urls.get(i).getUrl(), getChatId());
                                        repository.delete(urls.get(i));
                                        urls.get(i).setStatus(false);
                                        repository.save(urls.get(i));
                                    }
                                }
                            }

                        }
                        try {
                            Thread.sleep(1800000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            thread.start();
        };
    }

    private String getChatId(){
        String id = "";
        Iterable<ChatId> chatIds = chatIdRepos.findAll();
        List<ChatId> chatIdList = StreamSupport
                .stream(chatIds.spliterator(), false)
                .collect(Collectors.toList());
        if(chatIdList.size() > 0)
        id = chatIdList.get(0).getChatid();
        return id;
    }

    private int setRequestCode(String myUrl){
        int code = 404;
        try {
            URL url = new URL(myUrl);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            code = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    private void sendMessage(String url, String chatId){
        if(!chatId.equals("")) {
            URL obj = null;
            try {
                obj = new URL("https://api.telegram.org/bot768833536:AAEIzKRHUQV2jY3VTDNlZCuikp-3o6O_r24/sendMessage?chat_id=" + chatId + "&text=БАН:%20" + url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                con.setRequestMethod("GET");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
