package com.example.jonnyjonny.roosterplus.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonnyjonny.roosterplus.R;
import com.example.jonnyjonny.roosterplus.model.Chat;
import com.example.jonnyjonny.roosterplus.model.ChatModel;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatHolder> {
    public interface OnItemClickListener{
        public void onItemClick(String contactJid);
    }
    List<Chat>chatList;
    private OnItemClickListener mOnItemOnClickListener;

    public OnItemClickListener getmOnItemOnClickListener() {
        return mOnItemOnClickListener;
    }

    public void setmOnItemOnClickListener(OnItemClickListener mOnItemOnClickListener) {
        this.mOnItemOnClickListener = mOnItemOnClickListener;
    }

    public ChatListAdapter(Context context) {
        this.chatList = ChatModel.get(context).getChats();
    }

    @NonNull
    @Override
    public ChatHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.chat_list_item,parent,false);

        return new ChatHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatHolder holder, int position) {
        Chat chat=chatList.get(position);
        holder.bindChat(chat);

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
class ChatHolder extends RecyclerView.ViewHolder{
    private static final String LOGTAG="ChatHolder";
    private TextView contactTextView;
    private TextView messageAbstractTextView;
    private TextView timestampTextView;
    private ImageView profileImage;
    private Chat mChat;
    private ChatListAdapter mChatListAdapter;

    public ChatHolder(@NonNull View itemView,ChatListAdapter adapter) {
        super(itemView);
        contactTextView=(TextView)itemView.findViewById(R.id.contact_jid);
        messageAbstractTextView=(TextView)itemView.findViewById(R.id.message_abstract);
        timestampTextView=(TextView)itemView.findViewById(R.id.text_message_timestamp);
        profileImage=(ImageView)itemView.findViewById(R.id.profile);
        mChatListAdapter=adapter;
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatListAdapter.OnItemClickListener listener=mChatListAdapter.getmOnItemOnClickListener();
                if (listener!=null){
                    listener.onItemClick(contactTextView.getText().toString());
                }
                Log.d(LOGTAG,"Clicked on the item in the recyclerView");
            }
        });
    }
    public void bindChat(Chat chat){
        contactTextView.setText(chat.getJid());
        messageAbstractTextView.setText(chat.getLastMessage());
        profileImage.setImageResource(R.drawable.ic_profile);
        timestampTextView.setText("12:01 AM");
    }
}
