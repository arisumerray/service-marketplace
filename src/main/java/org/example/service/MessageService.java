package org.example.service;

import org.example.entity.Message;

import java.util.List;

public interface MessageService {
    Message createMessage(Integer dialogId, String content, String authorName);

    List<Message> getMessages(Integer dialogId);
}
