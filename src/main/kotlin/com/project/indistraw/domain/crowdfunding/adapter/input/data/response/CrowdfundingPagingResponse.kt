package com.project.indistraw.domain.crowdfunding.adapter.input.data.response

data class CrowdfundingPagingResponse(
    val isLast: Boolean,
    val list: List<CrowdfundingListResponse>
)