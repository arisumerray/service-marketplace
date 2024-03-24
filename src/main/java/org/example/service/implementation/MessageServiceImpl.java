package org.example.service.implementation;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.example.entity.Message;
import org.example.repository.DialogRepository;
import org.example.repository.MessageRepository;
import org.example.repository.UserRepository;
import org.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageRepository messageRepository;
    @Autowired
    private final DialogRepository dialogRepository;
    @Autowired
    private final UserRepository userRepository;

    public Message createMessage(Integer dialogId, String content, String authorName) {
        try {
            var message = Message.builder()
                    .id(0)
                    .dialogId(dialogRepository.findById(dialogId).get())
                    .content(content)
                    .createdAt(LocalDateTime.now())
                    .authorId(userRepository.findByEmail(authorName).get())
                    .build();
            return messageRepository.save(message);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User or dialog not found");
        }
    }

    public List<Message> getMessages(Integer dialogId) {
        return messageRepository.findAllByDialogId(dialogId);
    }
}
