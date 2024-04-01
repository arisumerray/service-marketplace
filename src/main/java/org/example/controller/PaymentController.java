package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paymnets")
public class PaymentController {
    @Autowired
    private final PaymentService paymentService;

    @PostMapping("/offer/{offerId}")
    public ResponseEntity<String> payForOffer(@PathVariable Integer offerId, Principal principal) {
        try {
            return ResponseEntity.ok().body(paymentService.payForOffer(offerId, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
