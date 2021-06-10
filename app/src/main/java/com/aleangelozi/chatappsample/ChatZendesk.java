package com.aleangelozi.chatappsample;

import android.content.Context;

import zendesk.chat.Chat;
import zendesk.chat.ChatConfiguration;
import zendesk.chat.ChatEngine;
import zendesk.chat.ChatSessionStatus;
import zendesk.chat.ChatState;
import zendesk.chat.ObservationScope;
import zendesk.chat.Observer;
import zendesk.chat.PreChatFormFieldStatus;
import zendesk.chat.ProfileProvider;
import zendesk.chat.VisitorInfo;
import zendesk.messaging.MessagingActivity;

public class ChatZendesk {
    private boolean visitorSet = false;

    public void initializeChatSdk(Context context) {

        initVisitorInfo();

        ChatConfiguration chatConfiguration = ChatConfiguration.builder()
                .withPreChatFormEnabled(true)
                .withTranscriptEnabled(false)
                .withNameFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withEmailFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withPhoneFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .withDepartmentFieldStatus(PreChatFormFieldStatus.HIDDEN)
                .build();

        MessagingActivity.builder()
                .withEngines(ChatEngine.engine())
                .show(context, chatConfiguration);

    }

    private void initVisitorInfo() {
        String name = "Josh";
        String email = "canada@canada.com";

        ProfileProvider profileProvider = Chat.INSTANCE.providers().profileProvider();

        final ObservationScope observationScope = new ObservationScope();
        Chat.INSTANCE.providers().chatProvider().observeChatState(observationScope,
                new Observer<ChatState>() {
                    @Override
                    public void update(ChatState chatState) {
                        ChatSessionStatus chatStatus = chatState.getChatSessionStatus();

                        if (chatStatus == ChatSessionStatus.INITIALIZING) {
                            if (!visitorSet) {

                                profileProvider.setVisitorInfo(setVisitorInfo(name, email), null);
                                visitorSet = true;
                            }
                        } else {

                            profileProvider.setVisitorInfo(setVisitorInfo(name, email), null);
                            visitorSet = true;
                        }
                    }
                });

    }

    private VisitorInfo setVisitorInfo(String name, String email) {

        return VisitorInfo.builder()
                .withName(name)
                .withEmail(email)
                .build();
    }
}
