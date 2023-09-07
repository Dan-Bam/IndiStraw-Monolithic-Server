package com.project.indistraw.domain.account.application.port.input

import com.project.indistraw.domain.account.application.port.input.dto.UpdateAddressDto

interface UpdateAddressUseCase {

    fun execute(dto: UpdateAddressDto)

}