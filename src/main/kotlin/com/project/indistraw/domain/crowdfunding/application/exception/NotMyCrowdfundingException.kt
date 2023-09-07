package com.project.indistraw.domain.crowdfunding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class NotMyCrowdfundingException: IndiStrawException(ErrorCode.NOT_MY_CROWDFUNDING)