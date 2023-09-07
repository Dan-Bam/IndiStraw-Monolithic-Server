package com.project.indistraw.domain.crowdfunding.adapter.input.mapper

import com.project.indistraw.domain.funding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.domain.funding.application.port.input.dto.SaveFundingDto
import org.springframework.stereotype.Component

@Component
class FundingDataMapper {

    fun toDto(crowdfundingIdx: Long, rewordIdx: Long, request: SaveFundingRequest): SaveFundingDto =
        SaveFundingDto(
            crowdfundingIdx = crowdfundingIdx,
            rewordIdx = rewordIdx,
            receiptId = request.receiptId,
            price = request.price,
            extraPrice = request.extraPrice
        )

}