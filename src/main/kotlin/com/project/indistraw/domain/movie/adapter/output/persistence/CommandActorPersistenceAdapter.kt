package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.ActorMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.ActorRepository
import com.project.indistraw.domain.movie.application.port.output.CommandActorPort
import com.project.indistraw.domain.movie.domain.Actor
import org.springframework.stereotype.Component

@Component
class CommandActorPersistenceAdapter(
    private val actorRepository: ActorRepository,
    private val actorMapper: ActorMapper
): CommandActorPort {

    override fun saveActor(actor: Actor): Actor {
        val actor = actorRepository.save(actorMapper.toEntity(actor))
        return actorMapper.toDomain(actor)!!
    }

}