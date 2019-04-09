package com.example.jonnyjonny.roosterplus.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ContactModel {
    private static final String LOGTAG="ContactModel";
    private static ContactModel sContactModel;
    private Context mContext;
    private List<Contact>mContactList;
    public static ContactModel get(Context context){
        if (sContactModel==null){
            sContactModel=new ContactModel(context);
        }
        return sContactModel;
    }
    private ContactModel(Context context){
        mContext=context;
        mContactList=new ArrayList<>();

        Contact contact=new Contact("contact@server.com",Contact.SubscriptionType.NONE_NONE);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
        mContactList.add(contact);
    }

    public List<Contact> getContacts() {
        return mContactList;
    }
    public void addContact(Contact c){
        mContactList.add(c);
    }
}
