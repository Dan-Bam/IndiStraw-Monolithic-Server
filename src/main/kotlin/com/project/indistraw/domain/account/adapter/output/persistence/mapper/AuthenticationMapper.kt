package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AuthenticationEntity
import com.project.indistraw.domain.account.domain.Authentication
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface AuthenticationMapper {

    infix fun toEntity(domain: Authentication): AuthenticationEntity
    infix fun toDomain(entity: AuthenticationEntity?): Authentication?

}