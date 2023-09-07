package com.project.indistraw.domain.pay.adapter.output.pay.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class FailedPayConfirmException: IndiStrawException(ErrorCode.FAILED_PAY_CONFIRM)