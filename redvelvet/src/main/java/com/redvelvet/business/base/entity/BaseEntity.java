package com.redvelvet.business.base.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "red_velvet_sequence")
    @SequenceGenerator(
            name = "red_velvet_sequence",
            sequenceName = "red_velvet_sequence_id",
            allocationSize = 50
    )
    private Long id;

    @CreationTimestamp
    @Column(name = "created_date", columnDefinition = "TIMESTAMP(6)", nullable = false)
    private Instant createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date", columnDefinition = "TIMESTAMP(6)")
    private Instant lastModifiedDate;
}
