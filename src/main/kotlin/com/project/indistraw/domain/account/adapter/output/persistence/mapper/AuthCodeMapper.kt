package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AuthCodeEntity
import com.project.indistraw.domain.account.domain.AuthCode
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface AuthCodeMapper {

    infix fun toEntity(domain: AuthCode): AuthCodeEntity
    infix fun toDomain(entity: AuthCodeEntity?): AuthCode?

}