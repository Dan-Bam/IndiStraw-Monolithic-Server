package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.QRCodeUUIDEntity
import com.project.indistraw.domain.account.domain.QRCodeUUID
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface QRCodeUUIDMapper {

    infix fun toEntity(domain: QRCodeUUID): QRCodeUUIDEntity
    infix fun toDomain(entity: QRCodeUUIDEntity?): QRCodeUUID?

}