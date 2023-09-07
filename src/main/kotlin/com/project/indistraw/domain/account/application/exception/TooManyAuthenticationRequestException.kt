package com.project.indistraw.domain.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class TooManyAuthenticationRequestException: IndiStrawException(ErrorCode.TOO_MANY_AUTHENTICATION_REQUEST)