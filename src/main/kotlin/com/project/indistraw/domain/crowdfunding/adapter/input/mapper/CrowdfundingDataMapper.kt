package com.project.indistraw.domain.crowdfunding.adapter.input.mapper

import com.project.indistraw.domain.crowdfunding.adapter.input.data.request.CreateCrowdfundingRequest
import com.project.indistraw.domain.crowdfunding.adapter.input.data.response.*
import com.project.indistraw.domain.crowdfunding.application.port.input.dto.*
import org.springframework.stereotype.Component

// map struct를 사용하여 하기는 너무 복잡하여 바꾸지 않고, 기존의 것을 대채 합니다.
@Component
class CrowdfundingDataMapper {

    infix fun toDto(dto: CreateCrowdfundingRequest): CreateCrowdfundingDto =
        CreateCrowdfundingDto(
            title = dto.title,
            description = dto.description,
            targetAmount = dto.targetAmount,
            directorAccount = DirectorAccountDto(
                bank = dto.directorAccount.bank,
                account = dto.directorAccount.account
            ),
            reward = dto.reward.map {
                RewardDto(
                    idx = 0L,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    totalCount = it.totalCount,
                    imageList = it.imageList
                )
            },
            endDate = dto.endDate,
            thumbnailUrl = dto.thumbnailUrl,
            imageList = dto.imageList,
            fileList = dto.fileList
        )

    infix fun toResponse(dto: CrowdfundingDetailDto): CrowdfundingDetailResponse =
        CrowdfundingDetailResponse(
            title = dto.title,
            description = dto.description,
            thumbnailUrl = dto.thumbnailUrl,
            writer = CrowdfundingDetailResponse.Writer(
                idx = dto.writer.idx,
                name = dto.writer.name
            ),
            amount = CrowdfundingDetailResponse.Amount(
                targetAmount = dto.amount.targetAmount,
                totalAmount = dto.amount.totalAmount,
                percentage = dto.amount.percentage
            ),
            remainingDay = dto.remainingDay,
            fundingCount = dto.fundingCount,
            imageList = dto.imageList,
            fileList = dto.fileList,
            reward = dto.reward.map {
                RewardResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageList = it.imageList,
                    totalCount = it.totalCount
                )
            },
            isFunding = dto.isFunding,
            status = dto.statusType
        )

    infix fun toResponse(dto: CrowdfundingPagingDto): CrowdfundingPagingResponse =
        CrowdfundingPagingResponse(
            isLast = dto.isLast,
            list = dto.list.map {
                CrowdfundingListResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    percentage = it.percentage,
                    thumbnailUrl = it.thumbnailUrl,
                    status = it.statusType
                )
            }
        )

    infix fun toResponse(dto: MyCrowdfundingDetailDto): MyCrowdfundingDetailResponse =
        MyCrowdfundingDetailResponse(
            title = dto.title,
            thumbnailUrl = dto.thumbnailUrl,
            amount = MyCrowdfundingDetailResponse.Amount(
                targetAmount = dto.amount.targetAmount,
                totalAmount = dto.amount.totalAmount,
                percentage = dto.amount.percentage
            ),
            remainingDay = dto.remainingDay,
            fundingCount = dto.fundingCount,
            reward = dto.reward.map {
                RewardResponse(
                    idx = it.idx,
                    title = it.title,
                    description = it.description,
                    price = it.price,
                    imageList = it.imageList,
                    totalCount = it.totalCount
                )
            },
            ordererList = dto.orderer.map {
                MyCrowdfundingDetailResponse.Orderer(
                    accountIdx = it.accountIdx,
                    name = it.name,
                    phoneNumber = it.phoneNumber,
                    zipcode = it.zipcode,
                    address = it.address,
                    profileUrl = it.profileUrl
                )
            }
        )

    infix fun toResponse(dto: List<CrowdfundingListDto>): List<CrowdfundingListResponse> =
        dto.map {
            CrowdfundingListResponse(
                idx = it.idx,
                title = it.title,
                description = it.description,
                percentage = it.percentage,
                thumbnailUrl = it.thumbnailUrl,
                status = it.statusType
            )
        }

}
