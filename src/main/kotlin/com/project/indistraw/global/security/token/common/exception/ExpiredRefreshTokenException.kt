package com.project.indistraw.global.security.token.common.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class ExpiredRefreshTokenException: IndiStrawException(ErrorCode.EXPIRED_REFRESH_TOKEN)