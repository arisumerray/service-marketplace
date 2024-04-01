package org.example.service;

import java.util.List;

public interface DocumentService {
    String getDocument(String name);

    List<String> getDocuments();
}
