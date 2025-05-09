package com.redvelvet.business.base.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant


@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "red_velvet_sequence")
    @SequenceGenerator(
        name = "red_velvet_sequence",
        sequenceName = "red_velvet_sequence_id",
        allocationSize = 50
    )
    var id: Long? = null

    @CreationTimestamp
    @Column(columnDefinition = "DATETIME(6)", nullable = false)
    var createdDate: Instant = Instant.now()

    @UpdateTimestamp
    @Column(columnDefinition = "DATETIME(6)")
    var lastModifiedDate: Instant? = null
}