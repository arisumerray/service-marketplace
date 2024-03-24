package org.example.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "service_offers", schema = "public")
public class ServiceOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "price", nullable = false)
    private Integer price;

    @JsonIgnoreProperties(value = {
            "email",
            "passwordHash",
            "role",
            "createdAt",
            "authorities",
            "username",
            "accountNonExpired",
            "accountNonLocked",
            "credentialsNonExpired",
            "password",
            "enabled"})
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User senderId;

    @JsonIgnoreProperties(value = {
            "email",
            "passwordHash",
            "role",
            "createdAt",
            "authorities",
            "username",
            "accountNonExpired",
            "accountNonLocked",
            "credentialsNonExpired",
            "password",
            "enabled"})
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiverId;

    @Column(name = "is_executed", nullable = false)
    private Boolean isExecuted;
}
