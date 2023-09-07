package com.project.indistraw.domain.account.adapter.input.mapper

import com.project.indistraw.domain.account.adapter.input.data.request.SignInRequest
import com.project.indistraw.domain.account.adapter.input.data.request.SignUpRequest
import com.project.indistraw.domain.account.adapter.input.data.response.TokenResponse
import com.project.indistraw.domain.account.application.port.input.dto.SignInDto
import com.project.indistraw.domain.account.application.port.input.dto.SignUpDto
import com.project.indistraw.domain.account.application.port.output.dto.TokenDto
import org.mapstruct.*

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
interface AuthDataMapper {

    infix fun toDto(request: SignUpRequest): SignUpDto
    infix fun toDto(request: SignInRequest): SignInDto
    @Mappings(
        Mapping(target = "accessTokenExpiredAt", expression = "java(LocalDateTime.now().plusSeconds(dto.getAccessTokenExpiredAt()))"),
        Mapping(target = "refreshTokenExpiredAt", expression = "java(LocalDateTime.now().plusSeconds(dto.getRefreshTokenExpiredAt()))")
    )
    infix fun toResponse(dto: TokenDto): TokenResponse

}