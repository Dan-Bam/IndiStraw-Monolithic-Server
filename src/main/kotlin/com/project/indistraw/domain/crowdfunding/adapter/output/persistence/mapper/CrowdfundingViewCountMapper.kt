package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingViewCountEntity
import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface CrowdfundingViewCountMapper {

    infix fun toDomain(crowdfundingViewCountEntity: CrowdfundingViewCountEntity): CrowdfundingViewCount

}