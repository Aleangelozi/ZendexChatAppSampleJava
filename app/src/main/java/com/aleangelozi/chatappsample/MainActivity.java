package com.aleangelozi.chatappsample;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import zendesk.chat.Chat;
import zendesk.chat.ChatEngine;
import zendesk.messaging.MessagingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chat.INSTANCE.init(this, BuildConfig.ZENDESK_CHAT_ACCOUNT_KEY);

        Button mChatButton = findViewById(R.id.chat_button);

        mChatButton.setOnClickListener(v -> MessagingActivity.builder()
            .withEngines(ChatEngine.engine())
            .show(v.getContext()));
    }
}