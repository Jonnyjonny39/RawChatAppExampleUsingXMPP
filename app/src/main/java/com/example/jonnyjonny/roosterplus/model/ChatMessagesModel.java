package com.example.jonnyjonny.roosterplus.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ChatMessagesModel {
    public interface OnMessageAddListener{
        void onMessageAdd();
    }
    private static ChatMessagesModel sChatMessageModel;
    private Context context;
    List<ChatMessage>messages;
    OnMessageAddListener messageAddListener;

    public void setMessageAddListener(OnMessageAddListener messageAddListener) {
        this.messageAddListener = messageAddListener;
    }

    public static ChatMessagesModel get(Context context){
        if (sChatMessageModel==null){
            sChatMessageModel=new ChatMessagesModel(context);
        }
        return sChatMessageModel;
    }
    private ChatMessagesModel(Context context){

        this.context=context;
        ChatMessage message1=new ChatMessage("Hey There",System.currentTimeMillis(),ChatMessage.Type.SENT,"user@server.com");

        ChatMessage message2=new ChatMessage("How Are You Doing",System.currentTimeMillis(),ChatMessage.Type.RECEIVED,"user@server.com");

        messages=new ArrayList<>();

        messages.add(message1);
        messages.add(message2);
        messages.add(message1);
        messages.add(message2);
        messages.add(message1);
        messages.add(message2);
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }
    public void addMessages(ChatMessage message){
        messages.add(message);
        messageAddListener.onMessageAdd();
    }
}
