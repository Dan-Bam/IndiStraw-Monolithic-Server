package com.project.indistraw.domain.crowdfunding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class RewardNotFoundException: IndiStrawException(ErrorCode.REWARD_NOT_FOUND)