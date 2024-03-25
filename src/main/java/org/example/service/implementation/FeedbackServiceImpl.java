package org.example.service.implementation;

import lombok.AllArgsConstructor;
import org.example.FeedbackMapper;
import org.example.dto.CreateFeedbackDto;
import org.example.entity.Feedback;
import org.example.repository.FeedbackRepository;
import org.example.repository.ServiceOfferRepository;
import org.example.repository.UserRepository;
import org.example.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private final FeedbackRepository feedbackRepository;
    @Autowired
    private final FeedbackMapper feedbackMapper;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ServiceOfferRepository serviceOfferRepository;

    public Feedback createFeedback(Integer offerId, CreateFeedbackDto createFeedbackDto, String name) {
        var feedback = feedbackMapper.fromDto(createFeedbackDto);
        var userFrom = userRepository.findByEmail(name).get();
        var offer = serviceOfferRepository.findById(offerId).get();
        if (!Objects.equals(userFrom.getId(), offer.getReceiverId().getId())) {
            throw new IllegalArgumentException("You can't give feedback to this service offer");
        }
        if (!offer.getIsExecuted()) {
            throw new IllegalArgumentException("You can't give feedback to not executed service offer");
        }
        feedback.setUserId(offer.getSenderId());
        return feedbackRepository.save(feedback);
    }

    public List<CreateFeedbackDto> getAllFeedbacksByUserId(Integer offerId) {
        return feedbackMapper.toDtos(feedbackRepository.findAllByUserId(offerId));
    }
}
