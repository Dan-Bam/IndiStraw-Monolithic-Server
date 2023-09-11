package com.project.indistraw.domain.account.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class AddressEntity(
    @Column(nullable = true)
    val zipcode: String?,

    @Column(nullable = true)
    val streetAddress: String?,

    @Column(nullable = true)
    val detailAddress: String?
)