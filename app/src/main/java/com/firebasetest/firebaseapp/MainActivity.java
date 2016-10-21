package com.firebasetest.firebaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Button;

import com.firebase.client.Firebase;

public class MainActivity extends AppCompatActivity {
    private static final String NAME = "name";
    private Firebase mRootRef;
    private Button mButton;
    private AppCompatEditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootRef = new Firebase(getString(R.string.firebase_db) + "Users"); // adding /Users at the end of base db url will add a new object called User

        mButton = (Button) findViewById(R.id.btn);
        mEditText = (AppCompatEditText) findViewById(R.id.value);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String value = mEditText.getText().toString();
                //Firebase mRefChild = mRootRef.child(NAME);


                mRootRef.push().setValue(value); // each time it creates a new id as the object property

            }
        });

    }
}
