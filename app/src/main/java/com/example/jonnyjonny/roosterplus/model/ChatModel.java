package com.example.jonnyjonny.roosterplus.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ChatModel {
    private static  ChatModel sChatsModel;
    private Context mContext;
    public static ChatModel get(Context context){
        if(sChatsModel==null){
            sChatsModel=new ChatModel(context);
        }
        return sChatsModel;

    }
    private ChatModel(Context context){
        mContext=context;

    }
    public List<Chat>getChats(){
        List<Chat>chats=new ArrayList<>();
        Chat chat1=new Chat("user1@server1.com","Hey There");
        Chat chat2=new Chat("user1@server1.com","Hey There");
        Chat chat3=new Chat("user1@server1.com","Hey There");
        Chat chat4=new Chat("user1@server1.com","Hey There");
        Chat chat5=new Chat("user1@server1.com","Hey There");
        Chat chat6=new Chat("user1@server1.com","Hey There");
        Chat chat7=new Chat("user1@server1.com","Hey There");
        chats.add(chat7);
        chats.add(chat1);
        chats.add(chat2);
        chats.add(chat3);
        chats.add(chat4);
        chats.add(chat5);
        chats.add(chat6);
        return chats;
    }

}
