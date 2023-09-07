package com.project.indistraw.domain.crowdfunding.adapter.input.data.request

import java.time.LocalDate
import javax.validation.constraints.NotNull

data class CreateCrowdfundingRequest(
    @field:NotNull
    val title: String,

    @field:NotNull
    val description: String,

    @field:NotNull
    val targetAmount: Long,

    val directorAccount: DirectorAccountRequest,

    val reward: List<RewardRequest>,

    val endDate: LocalDate,

    @field:NotNull
    val thumbnailUrl: String,

    @field:NotNull
    val imageList: List<String>,

    @field:NotNull
    val fileList: List<String>
)