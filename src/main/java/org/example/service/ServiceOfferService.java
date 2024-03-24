package org.example.service;

import org.example.dto.CreateServiceOfferDto;
import org.example.entity.ServiceOffer;

import java.util.List;

public interface ServiceOfferService {
    ServiceOffer createServiceOffer(CreateServiceOfferDto createServiceOfferDto, Integer receiverId, String name);

    List<ServiceOffer> getAllServiceOffersOutgoingById(String name);

    List<ServiceOffer> getAllServiceOffersIncomingById(String name);

    ServiceOffer markAsExecuted(Integer offerId, String name);
}
