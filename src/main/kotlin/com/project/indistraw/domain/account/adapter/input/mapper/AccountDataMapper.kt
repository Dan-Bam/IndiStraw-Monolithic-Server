package com.project.indistraw.domain.account.adapter.input.mapper

import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAccountInfoRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAddressRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdatePasswordRequest
import com.project.indistraw.domain.account.adapter.input.data.response.AccountInfoResponse
import com.project.indistraw.domain.account.application.port.input.dto.AccountInfoDto
import com.project.indistraw.domain.account.application.port.input.dto.UpdateAccountInfoDto
import com.project.indistraw.domain.account.application.port.input.dto.UpdateAddressDto
import com.project.indistraw.domain.account.application.port.input.dto.UpdatePasswordDto
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.MappingConstants
import org.mapstruct.ReportingPolicy

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface AccountDataMapper {

    infix fun toDto(request: UpdatePasswordRequest): UpdatePasswordDto
    infix fun toDto(request: UpdateAddressRequest): UpdateAddressDto
    infix fun toDto(request: UpdateAccountInfoRequest): UpdateAccountInfoDto
    infix fun toResponse(dto: AccountInfoDto): AccountInfoResponse

}