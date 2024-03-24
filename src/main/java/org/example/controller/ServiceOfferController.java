package org.example.controller;

import jakarta.persistence.EntityExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.ServiceOfferMapper;
import org.example.dto.CreateServiceOfferDto;
import org.example.entity.ServiceOffer;
import org.example.service.ServiceOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/serviceoffers")
public class ServiceOfferController {

    @Autowired
    private final ServiceOfferService serviceOfferService;

    @GetMapping("/outgoing")
    public ResponseEntity<?> getAllServiceOffersOutgoingById(Principal principal) {
        try {
            return ResponseEntity.ok(serviceOfferService
                    .getAllServiceOffersOutgoingById(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping("/incoming")
    public ResponseEntity<?> getAllServiceOffersIncomingById(Principal principal) {
        try {
            return ResponseEntity.ok(serviceOfferService
                    .getAllServiceOffersIncomingById(principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createServiceOffer(@RequestBody CreateServiceOfferDto createServiceOfferDto,
                                                @RequestParam Integer receiverId,
                                                Principal principal
    ) {
        try {
            return ResponseEntity.ok().body(serviceOfferService
                    .createServiceOffer(createServiceOfferDto, receiverId, principal.getName()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> markAsExecutedServiceOffer(@PathVariable Integer id,
                                                        Principal principal
    ) {
        try {
            return ResponseEntity.ok().body(serviceOfferService.markAsExecuted(id, principal.getName()));
        } catch (EntityExistsException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        } catch (AccessDeniedException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
