package com.project.indistraw.domain.account.adapter.input.mapper

import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAccountInfoRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAddressRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdatePasswordRequest
import com.project.indistraw.domain.account.adapter.input.data.response.AccountInfoResponse
import com.project.indistraw.domain.movie.adapter.input.data.response.FilmographyResponse
import com.project.indistraw.domain.account.application.port.input.dto.*
import com.project.indistraw.domain.movie.application.port.input.dto.FilmographyDto
import org.springframework.stereotype.Component

@Component
class AccountDataMapper {

    infix fun toDto(request: UpdatePasswordRequest): UpdatePasswordDto =
        UpdatePasswordDto(
            phoneNumber = request.phoneNumber,
            newPassword = request.newPassword
        )

    infix fun toDto(request: UpdateAddressRequest): UpdateAddressDto =
        UpdateAddressDto(
            zipcode = request.zipcode,
            streetAddress = request.streetAddress,
            detailAddress = request.detailAddress
        )

    infix fun toDto(request: UpdateAccountInfoRequest): UpdateAccountInfoDto =
        UpdateAccountInfoDto(
            name = request.name,
            profileUrl = request.profileUrl
        )

    infix fun toResponse(dto: AccountInfoDto): AccountInfoResponse =
        AccountInfoResponse(
            accountIdx = dto.accountIdx,
            id = dto.id,
            name = dto.name,
            phoneNumber = dto.phoneNumber,
            zipcode = dto.zipcode,
            address = dto.address,
            profileUrl = dto.profileUrl
        )

    infix fun toResponse(dto: FilmographyDto): FilmographyResponse =
        FilmographyResponse(
            idx = dto.idx,
            thumbnailUrl = dto.thumbnailUrl
        )

}