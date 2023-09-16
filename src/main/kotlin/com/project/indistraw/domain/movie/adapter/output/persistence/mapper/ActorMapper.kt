package com.project.indistraw.domain.movie.adapter.output.persistence.mapper

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.ActorEntity
import com.project.indistraw.domain.movie.domain.Actor
import org.springframework.stereotype.Component

@Component
class ActorMapper {

    fun toEntity(domain: Actor): ActorEntity =
        ActorEntity(
            id = domain.id,
            profileUrl = domain.profileUrl,
            name = domain.name
        )

    fun toDomain(entity: ActorEntity?): Actor? =
        entity?.let {
            Actor(
                id = it.id,
                profileUrl = it.profileUrl,
                name = it.name
            )
        }

}