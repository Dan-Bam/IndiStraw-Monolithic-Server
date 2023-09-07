package com.project.indistraw.domain.crowdfunding.application.port.input.dto

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import java.util.*

data class CrowdfundingDetailDto(
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val writer: Writer,
    val amount: AmountDto,
    val remainingDay: Long,
    val fundingCount: Long,
    val imageList: List<String>,
    val fileList: List<String>,
    val reward: List<RewardDto>,
    val isFunding: Boolean,
    val statusType: Crowdfunding.StatusType
) {

    data class Writer(
        val idx: UUID,
        val name: String
    )

}
