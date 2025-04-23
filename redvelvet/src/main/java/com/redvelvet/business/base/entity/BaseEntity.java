package com.redvelvet.business.base.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "red_velvet_sequence")
    @SequenceGenerator(name = "red_velvet_sequence", sequenceName = "red_velvet_sequence_id", allocationSize = 50)
    private long id;

    @CreationTimestamp
    private Instant createdDate = Instant.now();

    @UpdateTimestamp
    private Instant lastModifiedDate;

}


