package com.project.indistraw.domain.account.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class DuplicatedNewPasswordException: IndiStrawException(ErrorCode.DUPLICATE_NEW_PASSWORD)