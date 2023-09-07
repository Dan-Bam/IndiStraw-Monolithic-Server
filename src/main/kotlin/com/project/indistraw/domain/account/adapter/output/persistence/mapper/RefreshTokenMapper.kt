package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.RefreshTokenEntity
import com.project.indistraw.domain.account.domain.RefreshToken
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface RefreshTokenMapper {

    infix fun toEntity(domain: RefreshToken): RefreshTokenEntity
    infix fun toDomain(entity: RefreshTokenEntity?): RefreshToken?

}