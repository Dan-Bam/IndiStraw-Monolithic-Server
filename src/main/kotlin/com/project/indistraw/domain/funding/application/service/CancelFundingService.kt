package com.project.indistraw.domain.funding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.crowdfunding.application.exception.FundingNotFoundException
import com.project.indistraw.domain.crowdfunding.application.exception.ReceiptIdNotFoundException
import com.project.indistraw.domain.funding.application.port.input.CancelFundingUseCase
import com.project.indistraw.domain.funding.application.port.output.CommandFundingPort
import com.project.indistraw.domain.pay.application.port.output.PayPort
import com.project.indistraw.domain.pay.application.port.output.QueryFundingPort
import com.project.indistraw.domain.pay.application.port.output.QueryPayInfoPort

@ServiceWithTransaction
class CancelFundingService(
    private val queryFundingPort: QueryFundingPort,
    private val commandFundingPort: CommandFundingPort,
    private val queryPayInfoPort: QueryPayInfoPort,
    private val payPort: PayPort
): CancelFundingUseCase {

    override fun execute(fundingIdx: Long, receiptId: String) {
        if (!queryPayInfoPort.existByReceiptId(receiptId)) {
            throw ReceiptIdNotFoundException()
        }
        val funding = queryFundingPort.findByIdx(fundingIdx)
            ?: throw FundingNotFoundException()
        // receiptId를 가지고 bootpay 결제 취소 요청을 합니다.
        // TODO(의존성을 직접 가지는것이 아닌 event를 통해 의존성을 제거하고 뒤따라 이를 에러를 방지한다.)
        payPort.cancel(receiptId)
        commandFundingPort.deleteFunding(funding)
    }

}