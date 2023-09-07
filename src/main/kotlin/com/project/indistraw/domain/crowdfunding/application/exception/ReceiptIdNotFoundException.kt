package com.project.indistraw.domain.crowdfunding.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class ReceiptIdNotFoundException: IndiStrawException(ErrorCode.RECEIPT_ID_NOT_FOUND)