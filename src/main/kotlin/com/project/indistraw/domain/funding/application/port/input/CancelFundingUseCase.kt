package com.project.indistraw.domain.funding.application.port.input

interface CancelFundingUseCase {

    fun execute(fundingIdx: Long, receiptId: String)

}