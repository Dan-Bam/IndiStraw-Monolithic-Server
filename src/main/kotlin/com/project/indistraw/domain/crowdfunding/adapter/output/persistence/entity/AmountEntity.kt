package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AmountEntity(
    @Column(nullable = false)
    val totalAmount: Long,

    @Column(nullable = false)
    val targetAmount: Long
)