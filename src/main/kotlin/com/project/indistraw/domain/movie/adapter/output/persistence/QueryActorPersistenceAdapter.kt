package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.ActorMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.ActorRepository
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.domain.Actor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryActorPersistenceAdapter(
    private val actorRepository: ActorRepository,
    private val actorMapper: ActorMapper
): QueryActorPort {

    override fun findById(idx: Long): Actor? {
        val actor = actorRepository.findByIdOrNull(idx)
        return actorMapper.toDomain(actor)
    }

    override fun findByNameContaining(name: String): List<Actor> {
        val actorList = actorRepository.findByNameContaining(name)
        return actorList.map { actorMapper.toDomain(it)!! }
    }

}