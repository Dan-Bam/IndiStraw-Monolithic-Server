package com.project.indistraw.domain.movie.application.port.output

import com.project.indistraw.domain.movie.domain.Director

interface CommandDirectorPort {

    fun saveDirector(director: Director): Director

}