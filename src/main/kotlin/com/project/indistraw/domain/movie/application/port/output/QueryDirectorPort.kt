package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Director

interface QueryDirectorPort {

    fun findById(idx: Int): Director?

}