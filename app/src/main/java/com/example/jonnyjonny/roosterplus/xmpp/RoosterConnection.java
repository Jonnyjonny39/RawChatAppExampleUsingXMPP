package com.example.jonnyjonny.roosterplus.xmpp;

import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;

import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackConfiguration;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;

import java.io.IOException;

public  class RoosterConnection implements ConnectionListener {
    private static final String LOGTAG="Rooster Connection";

    private final Context mApplicationContext;
    private String mUsername;
    private String mPassword;
    private String mServiceName;
    private XMPPTCPConnection mConnection;
    public RoosterConnection(Context mApplicationContext){
        Log.d(LOGTAG,"RoosterConnection Constructor Called");
        this.mApplicationContext=mApplicationContext;
    }
    public void connect()throws IOException,XMPPException,SmackException{
        XMPPTCPConnectionConfiguration conf=XMPPTCPConnectionConfiguration.builder().
                setXmppDomain(mServiceName).
                setHost(mServiceName).
                setResource("Rooster+")
                .setKeystoreType(null)
                .setSendPresence(true)
                .setSecurityMode(ConnectionConfiguration.SecurityMode.required).
                setCompressionEnabled(true).build();
        SmackConfiguration.DEBUG=true;
        XMPPTCPConnection.setUseStreamManagementDefault(true);

        mConnection=new XMPPTCPConnection(conf);
        mConnection.setUseStreamManagement(true);
        mConnection.setUseStreamManagementResumption(true);
        mConnection.setPreferredResumptionTime(5);

    }
    public void disconnect(){

    }
    private void gatherCredentials(){
        String jid= PreferenceManager.getDefaultSharedPreferences(mApplicationContext).getString("xmpp jid",null);
        mPassword=PreferenceManager.getDefaultSharedPreferences(mApplicationContext).getString("xmpp password",null);
        if (jid!=null){
            mUsername=jid.split("@")[0];
            mServiceName=jid.split("@")[1];
        }else{
            mUsername="";
            mServiceName="";

        }

    }


    @Override
    public void connected(XMPPConnection connection) {

    }

    @Override
    public void authenticated(XMPPConnection connection, boolean resumed) {

    }

    @Override
    public void connectionClosed() {

    }

    @Override
    public void connectionClosedOnError(Exception e) {

    }
}
