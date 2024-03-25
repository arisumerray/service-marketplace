package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "documents", schema = "public")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "link")
    private String link;

    @Column(name = "name")
    private String name;
}
