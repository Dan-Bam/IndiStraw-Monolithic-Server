package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.DirectorMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.DirectorRepository
import com.project.indistraw.domain.movie.application.port.output.CommandDirectorPort
import com.project.indistraw.domain.movie.domain.Director
import org.springframework.stereotype.Component

@Component
class CommandDirectorPersistenceAdapter(
    private val directorRepository: DirectorRepository,
    private val directorMapper: DirectorMapper
): CommandDirectorPort {

    override fun saveDirector(director: Director): Director {
        val director = directorRepository.save(directorMapper.toEntity(director))
        return directorMapper.toDomain(director)!!
    }

}