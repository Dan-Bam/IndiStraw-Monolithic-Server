package com.project.indistraw.domain.funding.adapter.input

import com.project.indistraw.domain.crowdfunding.adapter.input.mapper.CrowdfundingDataMapper
import com.project.indistraw.domain.crowdfunding.adapter.input.mapper.FundingDataMapper
import com.project.indistraw.domain.crowdfunding.adapter.input.data.response.CrowdfundingListResponse
import com.project.indistraw.domain.funding.adapter.input.data.request.SaveFundingRequest
import com.project.indistraw.domain.funding.application.port.input.CancelFundingUseCase
import com.project.indistraw.domain.funding.application.port.input.FindMyFundingListUseCase
import com.project.indistraw.domain.funding.application.port.input.SaveFundingUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/v1/funding")
class FundingWebAdapter(
    private val fundingDataMapper: FundingDataMapper,
    private val crowdfundingDataMapper: CrowdfundingDataMapper,
    private val saveFundingUseCase: SaveFundingUseCase,
    private val cancelFundingUseCase: CancelFundingUseCase,
    private val findMyFundingListUseCase: FindMyFundingListUseCase
) {

    @PostMapping("crowdfunding/{crowdfundingIdx}/reword/{rewordIdx}")
    fun saveFunding(
        @PathVariable crowdfundingIdx: Long,
        @PathVariable rewordIdx: Long,
        @RequestBody request: SaveFundingRequest,
    ): ResponseEntity<Void> =
        fundingDataMapper.toDto(crowdfundingIdx, rewordIdx, request)
            .let { saveFundingUseCase.execute(it) }
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("cancel/{idx}/receipt/{receiptId}")
    fun cancelFunding(@PathVariable idx: Long, @PathVariable receiptId: String): ResponseEntity<Void> =
        cancelFundingUseCase.execute(idx, receiptId)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("my")
    fun findOrderCrowdfunding(): ResponseEntity<List<CrowdfundingListResponse>> =
        findMyFundingListUseCase.execute()
            .let { crowdfundingDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

}