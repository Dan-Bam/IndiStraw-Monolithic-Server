package com.project.indistraw.domain.funding.application.port.input

import com.project.indistraw.domain.funding.application.port.input.dto.SaveFundingDto

interface SaveFundingUseCase {

    fun execute(dto: SaveFundingDto)

}