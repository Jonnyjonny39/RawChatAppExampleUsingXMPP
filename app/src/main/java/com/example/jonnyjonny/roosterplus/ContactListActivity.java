package com.example.jonnyjonny.roosterplus;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.jonnyjonny.roosterplus.adapters.ContactListAdapter;

public class ContactListActivity extends AppCompatActivity implements ContactListAdapter.OnItemClickListener {
    private RecyclerView contactListRecyclerView;
    private static final String LOGTAG="ContactListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton newContactButton = (FloatingActionButton) findViewById(R.id.new_contact_button);
        newContactButton.setBackgroundTintList(getResources().getColorStateList(R.color.colorPrimary));
        newContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();

            }
        });
        contactListRecyclerView=(RecyclerView)findViewById(R.id.contact_list_recycler_view);
        contactListRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        ContactListAdapter mAdapter=new ContactListAdapter(getApplicationContext());
        mAdapter.setmOnItemClickListener(this);
        contactListRecyclerView.setAdapter(mAdapter);
    }
    private void addContact(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle(R.string.add_contact_label_text);
        final EditText input=new EditText(this);

        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton(R.string.add_contact_confirm_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOGTAG,"User Clicked On OK");
            }
        });
        builder.setNegativeButton(R.string.add_contact_cancel_text, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(LOGTAG,"User Clicked On Cancel");
                dialog.cancel();

            }
        });
        builder.show();
    }

    @Override
    public void onItemClick(String contactJid) {
        Intent i=new Intent(ContactListActivity.this,ChatViewActivity.class);
        startActivity(i);
    }
}
