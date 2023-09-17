package com.project.indistraw.domain.movie.application.exception

import com.project.indistraw.global.error.ErrorCode
import com.project.indistraw.global.error.exception.IndiStrawException

class DirectorNotFoundException: IndiStrawException(ErrorCode.DIRECTOR_NOT_FOUND)