package org.example;

import org.example.dto.CreateFeedbackDto;
import org.example.entity.Feedback;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    Feedback fromDto(CreateFeedbackDto dto);

    List<CreateFeedbackDto> toDtos(List<Feedback> feedbacks);
}
