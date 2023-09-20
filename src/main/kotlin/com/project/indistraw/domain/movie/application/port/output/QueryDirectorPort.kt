package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Director

interface QueryDirectorPort {

    fun findById(id: Int): Director?
    fun findByNameContaining(name: String): List<Director>

}