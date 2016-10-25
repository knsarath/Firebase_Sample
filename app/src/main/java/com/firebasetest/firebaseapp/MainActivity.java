package com.firebasetest.firebaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private Firebase mNameRef;
    private ListView mListView;
    private ArrayList<String> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameRef = new Firebase(getString(R.string.firebase_db)); // adding /Users at the end of base db url will add a new object called User
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mList);
        mListView = (ListView) findViewById(R.id.list);
        mListView.setAdapter(adapter);

        mNameRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final String value = dataSnapshot.getValue(String.class);
                mList.add(value);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                try {
                    int index = Integer.parseInt(dataSnapshot.getKey());
                    if (index < mList.size()) {
                        mList.remove(index);
                        mList.add(index, dataSnapshot.getValue(String.class));
                        adapter.notifyDataSetChanged();

                    }
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                final String value = dataSnapshot.getValue(String.class);
                if (mList.contains(value)) {
                    mList.remove(value);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
