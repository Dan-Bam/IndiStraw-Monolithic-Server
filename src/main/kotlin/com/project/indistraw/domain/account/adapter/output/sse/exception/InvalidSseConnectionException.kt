package com.project.indistraw.domain.account.adapter.output.sse.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class InvalidSseConnectionException: IndiStrawException(ErrorCode.INVALID_SSE_CONNECTION)