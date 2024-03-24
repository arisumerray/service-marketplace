package org.example.service.implementation;

import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.example.ServiceOfferMapper;
import org.example.dto.CreateServiceOfferDto;
import org.example.entity.ServiceOffer;
import org.example.repository.ServiceOfferRepository;
import org.example.repository.UserRepository;
import org.example.service.ServiceOfferService;
import org.mapstruct.control.MappingControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class ServiceOfferServiceImpl implements ServiceOfferService {
    @Autowired
    private final ServiceOfferRepository serviceOfferRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final ServiceOfferMapper serviceOfferMapper;

    public ServiceOffer createServiceOffer(CreateServiceOfferDto createServiceOfferDto,
                                           Integer receiverId,
                                           String name) {
        var receiver = userRepository.findById(receiverId).get();
        var sender = userRepository.findByEmail(name).get();
        if (Objects.equals(receiver.getId(), sender.getId())) {
            throw new IllegalArgumentException("You can't send service offer to yourself");
        }
        var serviceOffer = serviceOfferMapper.fromDto(createServiceOfferDto);
        serviceOffer.setReceiverId(receiver);
        serviceOffer.setSenderId(sender);
        serviceOffer.setIsExecuted(false);
        return serviceOfferRepository.save(serviceOffer);
    }

    public List<ServiceOffer> getAllServiceOffersOutgoingById(String name) {
        return serviceOfferRepository.findAllOutgoingById(userRepository.findByEmail(name).get().getId());
    }

    public List<ServiceOffer> getAllServiceOffersIncomingById(String name) {
        return serviceOfferRepository.findAllIncomingById(userRepository.findByEmail(name).get().getId());
    }

    public ServiceOffer markAsExecuted(Integer offerId, String name) {
        var serviceOffer = serviceOfferRepository.findById(offerId).get();
        if (serviceOffer.getIsExecuted()) {
            throw new EntityExistsException("Service offer already executed");
        }
        if (!Objects.equals(serviceOffer.getReceiverId().getId(), userRepository.findByEmail(name).get().getId())) {
            throw new AccessDeniedException("You can execute only the service offers, that were sent to you");
        }
        serviceOffer.setIsExecuted(true);
        return serviceOfferRepository.save(serviceOffer);
    }
}
