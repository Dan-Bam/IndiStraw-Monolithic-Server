package com.project.indistraw.domain.crowdfunding.adapter.input.data.response

import java.util.*

data class MyCrowdfundingDetailResponse(
    val title: String,
    val thumbnailUrl: String,
    val amount: Amount,
    val remainingDay: Long,
    val fundingCount: Long,
    val reward: List<RewardResponse>,
    val ordererList: List<Orderer>
) {

    data class Amount(
        val targetAmount: Long,
        val totalAmount: Long,
        val percentage: Double
    )

    data class Orderer(
        val accountIdx: UUID,
        val name: String,
        val phoneNumber: String,
        val zipcode: String?,
        val address: String?,
        val profileUrl: String?
    )

}
