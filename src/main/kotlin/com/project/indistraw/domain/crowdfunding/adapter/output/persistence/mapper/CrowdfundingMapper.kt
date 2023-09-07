package com.project.indistraw.domain.crowdfunding.adapter.output.persistence.mapper

import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingEntity
import com.project.indistraw.domain.crowdfunding.adapter.output.persistence.entity.CrowdfundingViewCountEntity
import com.project.indistraw.domain.crowdfunding.domain.Crowdfunding
import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface CrowdfundingMapper {

    @Mappings(
        Mapping(target = "amount.totalAmount", source = "amount.totalAmount"),
        Mapping(target = "amount.targetAmount", source = "amount.targetAmount"),
        Mapping(target = "directorAccount.bank", source = "directorAccount.bank"),
        Mapping(target = "directorAccount.account", source = "directorAccount.account"),
        Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    )
    infix fun toEntity(domain: Crowdfunding): CrowdfundingEntity
    infix fun toEntity(domain: CrowdfundingViewCount): CrowdfundingViewCountEntity
    @Mappings(
        Mapping(target = "amount.totalAmount", source = "amount.totalAmount"),
        Mapping(target = "amount.targetAmount", source = "amount.targetAmount"),
        Mapping(target = "directorAccount.bank", source = "directorAccount.bank"),
        Mapping(target = "directorAccount.account", source = "directorAccount.account"),
        Mapping(target = "createdAt", expression = "java(LocalDateTime.now())")
    )
    infix fun toDomain(entity: CrowdfundingEntity?): Crowdfunding?

}