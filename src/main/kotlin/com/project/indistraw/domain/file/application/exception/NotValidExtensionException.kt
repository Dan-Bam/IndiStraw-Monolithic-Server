package com.project.indistraw.domain.file.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class NotValidExtensionException: IndiStrawException(ErrorCode.NOT_VALID_EXTENSION)