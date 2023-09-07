package com.project.indistraw.domain.crowdfunding.application.port.input.dto

data class AmountDto(
    val targetAmount: Long,
    val totalAmount: Long,
    val percentage: Double,
)