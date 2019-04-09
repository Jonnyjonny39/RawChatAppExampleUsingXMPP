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
import com.example.jonnyjonny.roosterplus.model.Contact;
import com.example.jonnyjonny.roosterplus.model.ContactModel;

import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactHolder> {
    public interface OnItemClickListener{
        public void onItemClick(String contactJid);
    }
    private List<Contact>mContacts;
    private Context mContext;
    private static final String LOGTAG="ContactListAdapter";

    public OnItemClickListener getmOnItemClickListener() {
        return mOnItemClickListener;
    }

    public void setmOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    private OnItemClickListener mOnItemClickListener;

    public ContactListAdapter( Context context) {
        mContacts= ContactModel.get(context).getContacts();
        mContext=context;
        Log.d(LOGTAG,"Constructor of ChatListAdapter,the size of the backing list is:"+mContacts);
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.contact_list_item,parent,false);
        return new ContactHolder(view,this);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact=mContacts.get(position);
        holder.bindContact(contact);

    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}
class ContactHolder extends RecyclerView.ViewHolder{
    private TextView jidTextView;
    private TextView subscriptionTypeTextView;
    private Contact mContact;
    private ImageView profile_image;
    private ContactListAdapter mAdapter;
    private static final String LOGTAG="ContactHolder";

    public ContactHolder(@NonNull View itemView,ContactListAdapter adapter) {
        super(itemView);
        mAdapter=adapter;
        jidTextView=(TextView)itemView.findViewById(R.id.contact_jid_string);
        subscriptionTypeTextView=(TextView)itemView.findViewById(R.id.subscription_type);
        profile_image=(ImageView)itemView.findViewById(R.id.profile_contact);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOGTAG,"User Clicked on Contact Item");
                ContactListAdapter.OnItemClickListener listener=mAdapter.getmOnItemClickListener();
                if (listener!=null){
                    Log.d(LOGTAG,"Calling The Listener Method");
                    listener.onItemClick(jidTextView.getText().toString());
                }
            }
        });
    }
    void bindContact(Contact c){
        mContact=c;
        if(mContact==null){
            return;

        }
        jidTextView.setText(mContact.getJid());
        subscriptionTypeTextView.setText("NONE_NONE");
        profile_image.setImageResource(R.drawable.ic_profile);
    }
}
