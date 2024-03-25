package org.example.service;

import org.example.dto.CreateFeedbackDto;
import org.example.entity.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Integer offerId, CreateFeedbackDto createFeedbackDto, String name);
    List<CreateFeedbackDto> getAllFeedbacksByUserId(Integer offerId);
}
