package com.project.indistraw.domain.funding.application.port.output

import com.project.indistraw.domain.funding.domain.Funding

interface CommandFundingPort {

    fun saveFunding(funding: Funding)
    fun deleteFunding(funding: Funding)

}