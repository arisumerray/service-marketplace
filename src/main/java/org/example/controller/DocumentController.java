package org.example.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/documents")
public class DocumentController {
    @Autowired
    private final DocumentService documentService;
    @GetMapping("/{name}")
    public ResponseEntity<?> getDocument(@PathVariable String name) {
        try {
            return ResponseEntity.ok().body(documentService.getDocument(name));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getDocuments() {
        try {
            return ResponseEntity.ok().body(documentService.getDocuments());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}
