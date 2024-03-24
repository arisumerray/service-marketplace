package org.example.controller;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.dto.DialogDto;
import org.example.entity.Dialog;
import org.example.repository.UserRepository;
import org.example.service.DialogService;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dialogs")
public class DialogController {
    @Autowired
    private final DialogService dialogService;

    @Autowired
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<DialogDto>> getAllDialogs(Principal principal) {
        return ResponseEntity.ok(dialogService
                .getAllDialogs(userRepository.findByEmail(principal.getName()).get().getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDialog(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(dialogService.getDialog(id));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createDialog(@RequestParam Integer userToId, Principal principal) {
        try {
            return ResponseEntity.ok(dialogService.createDialog(userToId,
                    userRepository.findByEmail(principal.getName()).get().getId()));
        } catch (EntityExistsException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }
}
