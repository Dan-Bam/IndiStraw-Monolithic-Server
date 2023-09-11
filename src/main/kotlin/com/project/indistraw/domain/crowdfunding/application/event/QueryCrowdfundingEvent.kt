package com.project.indistraw.domain.crowdfunding.application.event

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding

data class QueryCrowdfundingEvent(
    val crowdfunding: Crowdfunding,
    val ip: String
)