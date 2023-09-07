package com.project.indistraw.domain.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class AccountNotFoundException: IndiStrawException(ErrorCode.ACCOUNT_NOT_FOUND)