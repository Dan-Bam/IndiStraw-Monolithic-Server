package com.project.indistraw.domain.crowdfunding.application.port.input.dto

import java.time.LocalDate

data class CreateCrowdfundingDto(
    val title: String,
    val description: String,
    val targetAmount: Long,
    val directorAccount: DirectorAccountDto,
    val reward: List<RewardDto>,
    val endDate: LocalDate,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val fileList: List<String>
)