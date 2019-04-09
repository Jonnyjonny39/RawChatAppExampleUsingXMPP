package com.example.jonnyjonny.roosterplus.model;

public class Contact {
    private String jid;
    private SubscriptionType subscriptionType;
    public enum SubscriptionType{
        NONE_NONE,
        NONE_PENDING,
        NONE_TO,
        PENDING_NONE,
        PENDING_PENDING,
        PENDING_TO,
        FROM_NONE,
        FROM_PENDING,
        FROM_TO
    }

    public String getJid() {
        return jid;
    }

    public void setJid(String jid) {
        this.jid = jid;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Contact(String jid, SubscriptionType subscriptionType){
        this.jid=jid;
        this.subscriptionType=subscriptionType;

    }
}
