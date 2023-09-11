package com.project.indistraw.domain.crowdfunding.domain

import com.project.indistraw.crowdfunding.application.common.annotation.Aggregate
import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.AggregateRoot
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@AggregateRoot
data class Crowdfunding(
    val idx: Long,
    val writerIdx: UUID,
    val title: String,
    val description: String,
    val amount: Amount,
    val directorAccount: DirectorAccount,
    val createdAt: LocalDateTime,
    val endDate: LocalDate,
    var viewCount: Long,
    var statusType: StatusType,
    val thumbnailUrl: String,
    val imageList: List<String>,
    val fileList: List<String>
) {

    @Aggregate
    data class Amount(
        var totalAmount: Long,
        val targetAmount: Long
    )

    @Aggregate
    data class DirectorAccount(
        val bank: String,
        val account: String
    )

    @Aggregate
    enum class StatusType {

        UNDER_REVIEW, RECRUITING, FINISHED

    }

    fun increaseViewCount(): Crowdfunding {
        this.viewCount = viewCount.inc()
        return this
    }

    fun increaseTotalAmount(price: Long): Crowdfunding {
        this.amount.totalAmount += price
        return this
    }

    fun updateStatus(statusType: StatusType): Crowdfunding {
        this.statusType = statusType
        return this
    }

}