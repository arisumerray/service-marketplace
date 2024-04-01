package org.example.service.implementation;

import lombok.AllArgsConstructor;
import org.example.entity.Document;
import org.example.repository.DocumentRepository;
import org.example.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private final DocumentRepository documentRepository;

    public String getDocument(String name) {
        return documentRepository.getDocumentLinkByName(name);
    }

    @Override
    public List<String> getDocuments() {
        return documentRepository.findAll().stream().map(Document::getLink).toList();
    }
}
