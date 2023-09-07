package com.project.indistraw.domain.crowdfunding.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.crowdfunding.application.common.util.CalculateAmountUtil
import com.project.indistraw.domain.crowdfunding.application.port.input.CrowdfundingListUseCase
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingListDto
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.CrowdfundingPagingDto
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@ServiceWithReadOnlyTransaction
class CrowdfundingListService(
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val calculateAmountUtil: CalculateAmountUtil
): CrowdfundingListUseCase {

    override fun execute(pageable: Pageable): CrowdfundingPagingDto {
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order.desc("createdAt")))
        val crowdfundingList = queryCrowdfundingPort.findAll(pageRequest)

        val crowdfundingListDto = crowdfundingList.map {
            CrowdfundingListDto(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = calculateAmountUtil.calculateAmountPercentage(it.amount),
                thumbnailUrl = it.thumbnailUrl,
                statusType = it.statusType
            )
        }.toList()

        return CrowdfundingPagingDto(
            isLast = crowdfundingList.isLast,
            list = crowdfundingListDto
        )
    }

}