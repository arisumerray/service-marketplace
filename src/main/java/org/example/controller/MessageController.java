package org.example.controller;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/messages")
public class MessageController {
    private final MessageService messageService;

    @PostMapping
    public ResponseEntity<?> sendMessage(@RequestParam Integer dialogId, @RequestBody String messageText, Principal principal) {
        try {
            return ResponseEntity.ok(messageService.createMessage(dialogId, messageText, principal.getName()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getMessages(@RequestParam Integer dialogId) {
        try {
            return ResponseEntity.ok(messageService.getMessages(dialogId));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
