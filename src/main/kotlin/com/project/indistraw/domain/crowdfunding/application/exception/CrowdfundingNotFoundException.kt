package com.project.indistraw.domain.crowdfunding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class CrowdfundingNotFoundException: IndiStrawException(ErrorCode.CROWDFUNDING_NOT_FOUND)