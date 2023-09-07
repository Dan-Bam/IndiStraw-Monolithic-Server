package com.project.indistraw.domain.crowdfunding.adapter.input.data.response

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import java.util.*

data class CrowdfundingDetailResponse(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val writer: Writer,
    val amount: Amount,
    val remainingDay: Long,
    val fundingCount: Long,
    val imageList: List<String>,
    val fileList: List<String>,
    val reward: List<RewardResponse>,
    val isFunding: Boolean,
    val status: Crowdfunding.StatusType
) {

    data class Writer(
        val idx: UUID,
        val name: String
    )

    data class Amount(
        val targetAmount: Long,
        val totalAmount: Long,
        val percentage: Double
    )

}
