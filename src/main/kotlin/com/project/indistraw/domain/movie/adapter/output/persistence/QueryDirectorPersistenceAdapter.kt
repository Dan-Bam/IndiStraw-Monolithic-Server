package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.DirectorMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.DirectorRepository
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.domain.Director
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryDirectorPersistenceAdapter(
    private val directorRepository: DirectorRepository,
    private val directorMapper: DirectorMapper
): QueryDirectorPort {

    override fun findByIdOrNull(idx: Long): Director? {
        val director = directorRepository.findByIdOrNull(idx)
        return directorMapper.toDomain(director)
    }

    override fun findByNameContaining(name: String): List<Director> {
        val director = directorRepository.findByNameContaining(name)
        return director.map { directorMapper.toDomain(it)!! }
    }

    override fun existsByIdx(idx: Long): Boolean =
        directorRepository.existsByIdx(idx)

}