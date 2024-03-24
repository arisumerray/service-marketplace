package org.example.repository;

import org.example.entity.ServiceOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceOfferRepository extends JpaRepository<ServiceOffer, Integer> {

    @Query(value = "SELECT * FROM public.service_offers WHERE sender_id = ?1", nativeQuery = true)
    List<ServiceOffer> findAllOutgoingById(Integer id);

    @Query(value = "SELECT * FROM public.service_offers WHERE receiver_id = ?1", nativeQuery = true)
    List<ServiceOffer> findAllIncomingById(Integer id);
}
