package com.project.indistraw.domain.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class AuthCodeNotMatchException: IndiStrawException(ErrorCode.AUTH_CODE_NOT_MATCH)