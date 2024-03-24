package org.example;

import org.example.dto.CreateServiceOfferDto;
import org.example.entity.ServiceOffer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ServiceOfferMapper {
    ServiceOffer fromDto(CreateServiceOfferDto createServiceOfferDto);
}
