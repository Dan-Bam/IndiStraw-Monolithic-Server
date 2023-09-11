package com.project.indistraw.common.entity

import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class BaseTimeEntity(
    @Column(nullable = false, updatable = false, columnDefinition = "DATETIME(6)")
    val createdDate: LocalDateTime = LocalDateTime.now(),

    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "DATETIME(6)")
    val modifiedAt: LocalDateTime = LocalDateTime.now(),

    @Column(nullable = true, updatable = false, columnDefinition = "DATETIME(6)")
    val deletedAt: LocalDateTime? = null
)