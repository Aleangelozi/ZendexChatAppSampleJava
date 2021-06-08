package com.aleangelozi.chatappsample;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import zendesk.chat.Chat;
import zendesk.chat.ChatConfiguration;
import zendesk.chat.ChatEngine;
import zendesk.chat.PreChatFormFieldStatus;
import zendesk.messaging.MessagingActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Chat.INSTANCE.init(this, BuildConfig.ZENDESK_CHAT_ACCOUNT_KEY);

        Button mChatButton = findViewById(R.id.chat_button);

        mChatButton.setOnClickListener(v -> initializeChatSdk());
    }

    private void initializeChatSdk() {

        ChatConfiguration chatConfiguration = ChatConfiguration.builder()
                .withPreChatFormEnabled(true)
                .withTranscriptEnabled(true)
                .withNameFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withEmailFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withPhoneFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withDepartmentFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .build();

        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(this, chatConfiguration);
    }

}