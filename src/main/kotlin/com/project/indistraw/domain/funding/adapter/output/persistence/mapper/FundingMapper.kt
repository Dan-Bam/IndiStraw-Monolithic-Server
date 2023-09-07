package com.project.indistraw.domain.funding.adapter.output.persistence.mapper

import com.project.indistraw.domain.funding.adapter.output.persistence.entity.FundingEntity
import com.project.indistraw.domain.funding.domain.Funding
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface FundingMapper {

    infix fun toEntity(domain: Funding): FundingEntity
    infix fun toDomain(entity: FundingEntity?): Funding?

}