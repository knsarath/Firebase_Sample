package com.firebasetest.firebaseapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Firebase mNameRef;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNameRef = new Firebase(getString(R.string.firebase_db)); // adding /Users at the end of base db url will add a new object called User
        mTextView = (TextView) findViewById(R.id.name);

        mNameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final Map<String, String> map = dataSnapshot.getValue(Map.class);
                final String name = map.get("name");
                final String age = String.valueOf(map.get("Age"));
                final String profession = map.get("Profession");
                final String text = "Name : " + name + " <br> " + " Age : " + age + " <br> " + "  Profession :" + profession;
                mTextView.setText(Html.fromHtml(text));
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
}
