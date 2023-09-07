package com.project.indistraw.domain.crowdfunding.application.common.util

import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import org.springframework.stereotype.Component

@Component
class CalculateAmountUtil {

    fun calculateAmountPercentage(amount: Crowdfunding.Amount): Double {
        return if (amount.totalAmount == 0L) {
            0.0
        } else {
            amount.totalAmount.toDouble() / amount.targetAmount.toDouble() * 100
        }
    }

}