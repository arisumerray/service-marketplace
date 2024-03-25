package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateFeedbackDto;
import org.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private final FeedbackService feedbackService;
    @PostMapping("/{offerId}")
    public ResponseEntity<?> sendFeedback(@PathVariable Integer offerId,
                                               @RequestBody CreateFeedbackDto feedback,
                                               Principal principal
    ) {
        try {
            return ResponseEntity.ok().body(feedbackService.createFeedback(offerId, feedback, principal.getName()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackByUserId(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok().body(feedbackService.getAllFeedbacksByUserId(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
