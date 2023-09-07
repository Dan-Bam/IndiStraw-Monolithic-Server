package com.project.indistraw.domain.crowdfunding.application.port.input.dto

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding

data class CrowdfundingListDto(
    val idx: Long,
    val title: String,
    val description: String,
    val percentage: Double,
    val thumbnailUrl: String,
    val statusType: Crowdfunding.StatusType
)