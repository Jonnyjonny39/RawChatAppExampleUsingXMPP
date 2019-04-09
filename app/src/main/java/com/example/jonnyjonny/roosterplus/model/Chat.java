package com.example.jonnyjonny.roosterplus.model;

public class Chat {
    private String jid;
    private String lastMessage;

    public String getJid() {
        return jid;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public Chat(String jid, String lastMessage) {
        this.jid = jid;
        this.lastMessage = lastMessage;

    }
}
