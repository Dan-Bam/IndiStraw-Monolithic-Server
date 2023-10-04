package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Actor

interface QueryActorPort {

    fun findById(idx: Long): Actor?
    fun findByNameContaining(name: String): List<Actor>

}