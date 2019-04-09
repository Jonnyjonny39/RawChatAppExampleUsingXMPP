package com.example.jonnyjonny.roosterplus;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.jonnyjonny.roosterplus.adapters.ChatListAdapter;

public class ChatListActivity extends AppCompatActivity implements ChatListAdapter.OnItemClickListener {
    private RecyclerView chatsRecyclerView;
    private FloatingActionButton newConversationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);
        chatsRecyclerView=(RecyclerView)findViewById(R.id.chatsRecyclerView);
        chatsRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ChatListAdapter mAdapter=new ChatListAdapter(getApplicationContext());
        mAdapter.setmOnItemOnClickListener(this);
        chatsRecyclerView.setAdapter(mAdapter);
        newConversationButton=(FloatingActionButton)findViewById(R.id.new_conversation_floating_button);
        newConversationButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        newConversationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ChatListActivity.this,ContactListActivity.class);
                startActivity(i);

            }
        });
    }

    @Override
    public void onItemClick(String contactJid) {
        Intent i=new Intent(ChatListActivity.this,ChatViewActivity.class);
        startActivity(i);
    }
}
