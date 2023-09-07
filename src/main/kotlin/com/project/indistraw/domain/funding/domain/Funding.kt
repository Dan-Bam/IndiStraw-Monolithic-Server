package com.project.indistraw.domain.funding.domain

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import com.project.indistraw.domain.account.domain.Account
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import com.project.indistraw.domain.crowdfunding.domain.Reward

@AggregateRoot
data class Funding(
    val idx: Long,
    val crowdfunding: Crowdfunding,
    val reword: Reward,
    val orderer: Account,
    val price: Long,
    val extraPrice: Long?,
    val activity: Activity
) {

    @Aggregate
    enum class Activity {

        DEPOSITED, NOT_DEPOSIT, DELIVERING, COMPLETED_DELIVERY, CANCEL, RETURN, REFUND

    }

}