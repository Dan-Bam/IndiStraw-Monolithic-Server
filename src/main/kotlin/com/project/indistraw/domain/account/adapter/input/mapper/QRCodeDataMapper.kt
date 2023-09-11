package com.project.indistraw.domain.account.adapter.input.mapper

import com.project.indistraw.domain.account.adapter.input.data.request.QRCodeUUIDRequest
import com.project.indistraw.domain.account.application.port.input.dto.QRCodeUUIDDto
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface QRCodeDataMapper {

    infix fun toDto(request: QRCodeUUIDRequest): QRCodeUUIDDto

}