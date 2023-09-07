package com.project.indistraw.domain.account.adapter.output.message.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class MessageSendFailedException: IndiStrawException(ErrorCode.MESSAGE_SEND_FAILED)