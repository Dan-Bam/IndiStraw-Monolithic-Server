package com.project.indistraw.domain.crowdfunding.adapter.input.data.response

data class RewardResponse(
    val idx: Long,
    val title: String,
    val description: String,
    val price: Long,
    val imageList: List<String>,
    val totalCount: Long?
)