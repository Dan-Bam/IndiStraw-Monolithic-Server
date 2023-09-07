package com.project.indistraw.domain.pay.adapter.output.persistence.mapper

import com.project.indistraw.domain.pay.adapter.output.persistence.entity.PayInfoEntity
import com.project.indistraw.domain.pay.domain.PayInfo
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface PayInfoMapper {

    infix fun toEntity(payInfo: PayInfo): PayInfoEntity

}