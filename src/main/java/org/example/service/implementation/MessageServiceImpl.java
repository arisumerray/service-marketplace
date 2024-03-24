package org.example.service.implementation;

import lombok.AllArgsConstructor;
import org.example.repository.MessageRepository;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageRepository messageRepository;
}
