package com.project.indistraw.domain.crowdfunding.application.port.input.dto

data class MyCrowdfundingDetailDto(
    val title: String,
    val thumbnailUrl: String,
    val amount: AmountDto,
    val remainingDay: Long,
    val fundingCount: Long,
    val reward: List<RewardDto>,
    val orderer: List<OrdererDto>
)