package com.project.indistraw.domain.crowdfunding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class FundingNotFoundException: IndiStrawException(ErrorCode.FUNDING_NOT_FOUND)