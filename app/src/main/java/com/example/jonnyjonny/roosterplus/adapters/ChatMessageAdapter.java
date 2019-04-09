package com.example.jonnyjonny.roosterplus.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jonnyjonny.roosterplus.R;
import com.example.jonnyjonny.roosterplus.model.ChatMessage;
import com.example.jonnyjonny.roosterplus.model.ChatMessagesModel;
import com.example.jonnyjonny.roosterplus.model.ChatModel;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageViewHolder>implements ChatMessagesModel.OnMessageAddListener {

   // public void setmOnInformRecyclerViewToScrollDownListener(OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener) {
     //   this.mOnInformRecyclerViewToScrollDownListener = mOnInformRecyclerViewToScrollDownListener;
   // }

    public interface OnInformRecyclerViewToScrollDownListener{
        public void onInformRecyclerViewToScrollView(int size);
    }
    private static final int SENT=1;
    private static final int RECEIVED=2;
    private static final String LOGTAG="ChatMessageAdapter";

    private List<ChatMessage>mChatMessageList;
    private LayoutInflater mLayoutInflater;
    private Context mContext;
    private String contactJid;
    private OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener;

    public void setmOnInformRecyclerViewToScrollDownListener(OnInformRecyclerViewToScrollDownListener mOnInformRecyclerViewToScrollDownListener) {
        this.mOnInformRecyclerViewToScrollDownListener = mOnInformRecyclerViewToScrollDownListener;
    }





    public ChatMessageAdapter(Context context, String contactJid){
       this.mLayoutInflater=LayoutInflater.from(context);
        this.mContext=context;
        this.contactJid=contactJid;

        mChatMessageList= ChatMessagesModel.get(context).getMessages();

        ChatMessagesModel.get(mContext).setMessageAddListener(this);
    }
    public void informRecyclerViewToScrollDownListener(){
        mOnInformRecyclerViewToScrollDownListener.onInformRecyclerViewToScrollView(mChatMessageList.size());
    }
    @NonNull
    @Override
    public ChatMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType){
            case SENT:
                itemView=mLayoutInflater.inflate(R.layout.chat_message_sent,parent,false);
                return new ChatMessageViewHolder(itemView);
            case RECEIVED:
                itemView=mLayoutInflater.inflate(R.layout.chat_message_received,parent,false);
                return new ChatMessageViewHolder(itemView);
                default:
                    itemView=mLayoutInflater.inflate(R.layout.chat_message_sent,parent,false);
                    return new ChatMessageViewHolder(itemView);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageViewHolder holder, int position) {
        ChatMessage chatMessage=mChatMessageList.get(position);
        holder.bindChat(chatMessage);


    }

    @Override
    public int getItemCount() {
        return mChatMessageList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ChatMessage.Type messageType=mChatMessageList.get(position).getType();
        if (messageType== ChatMessage.Type.SENT){
            return SENT;
        }else {
            return RECEIVED;
        }

    }

    @Override
    public void onMessageAdd() {
        mChatMessageList= ChatMessagesModel.get(mContext).getMessages();
        notifyDataSetChanged();
        informRecyclerViewToScrollDownListener();
    }
}
class ChatMessageViewHolder extends RecyclerView.ViewHolder{
    private TextView mMessageBody,mMessageTimestamp;
    private ImageView profileImage;

    public ChatMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        mMessageBody=(TextView)itemView.findViewById(R.id.text_message_body);
        mMessageTimestamp=(TextView)itemView.findViewById(R.id.text_message_timestamp);
        profileImage=(ImageView)itemView.findViewById(R.id.profile);
    }
    public void bindChat(ChatMessage chatMessage){
        mMessageBody.setText(chatMessage.getMessage());
        mMessageTimestamp.setText(chatMessage.getFormattedTime());
        profileImage.setImageResource(R.drawable.ic_profile);
    }
}
