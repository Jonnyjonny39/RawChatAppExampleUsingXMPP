package com.example.jonnyjonny.roosterplus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.jonnyjonny.roosterplus.adapters.ChatMessageAdapter;
import com.example.jonnyjonny.roosterplus.model.ChatMessage;
import com.example.jonnyjonny.roosterplus.model.ChatMessagesModel;
import com.example.jonnyjonny.roosterplus.ui.KeyboardUtil;

public class ChatViewActivity extends AppCompatActivity implements ChatMessageAdapter.OnInformRecyclerViewToScrollDownListener,KeyboardUtil.KeyboardVisibilityListener {
    RecyclerView chatMessageRecyclerView;
    private EditText textSendEditText;
    private ImageButton sendMessageButton;
    ChatMessageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_view);
        chatMessageRecyclerView=(RecyclerView)findViewById(R.id.chatMessageRecyclerView);
        chatMessageRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        adapter=new ChatMessageAdapter(getApplicationContext(),"user@server.com");

        adapter.setmOnInformRecyclerViewToScrollDownListener(this);
        chatMessageRecyclerView.setAdapter(adapter);
        textSendEditText=findViewById(R.id.textinput);
        sendMessageButton=findViewById(R.id.textSendButton);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatMessagesModel.get(getApplicationContext()).addMessages(new ChatMessage(textSendEditText.getText().toString(),System.currentTimeMillis(), ChatMessage.Type.SENT,"user@server.com"));
                textSendEditText.getText().clear();
            }
        });
        KeyboardUtil.setKeyboardVisibilityListener(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.activity_chat_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.receive_message){
            ChatMessagesModel.get(getApplicationContext()).addMessages(new ChatMessage("This Is A Message That You Received",System.currentTimeMillis(), ChatMessage.Type.RECEIVED,"user@server.com"));


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInformRecyclerViewToScrollView(int size) {
        chatMessageRecyclerView.scrollToPosition(size-1);
    }

    @Override
    public void onKeyboardVisibilityChanged(boolean keyboardVisible) {
        adapter.informRecyclerViewToScrollDownListener();


    }
}
