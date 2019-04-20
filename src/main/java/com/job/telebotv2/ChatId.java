package com.job.telebotv2;


import javax.persistence.*;

@Entity
public class ChatId {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String chatid;

    public ChatId(String chatid) {
        this.chatid = chatid;
    }

    public ChatId() {
    }

    public String getChatid() {
        return chatid;
    }

    public void setChatid(String chatid) {
        this.chatid = chatid;
    }
}
