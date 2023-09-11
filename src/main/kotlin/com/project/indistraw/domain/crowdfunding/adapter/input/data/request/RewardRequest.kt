package com.project.indistraw.domain.crowdfunding.adapter.input.data.request

import javax.validation.constraints.NotNull

data class RewardRequest(
    @field:NotNull
    val title: String,

    @field:NotNull
    val description: String,

    @field:NotNull
    val price: Long,

    @field:NotNull
    val imageList: List<String>,

    val totalCount: Long
)