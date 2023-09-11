package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity

import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
class DirectorAccountEntity(
    @Column(name = "director_bank", nullable = false)
    val bank: String,

    @Column(name = "director_account", nullable = false)
    val account: String
)