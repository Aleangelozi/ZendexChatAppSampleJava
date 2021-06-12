package com.aleangelozi.chatappsample;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mChatButton = findViewById(R.id.chat_button);

        ChatZendesk chat = new ChatZendesk(this);
        chat.initChatZenDesk();

        mChatButton.setOnClickListener(v -> chat.openChat());
    }

}