package com.project.indistraw.domain.movie.adapter.output.persistence.mapper

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.ActorEntity
import com.project.indistraw.domain.movie.domain.Actor
import org.springframework.stereotype.Component

@Component
class ActorMapper {

    fun toEntity(domain: Actor): ActorEntity =
        ActorEntity(
            idx = domain.idx,
            profileUrl = domain.profileUrl,
            name = domain.name
        )

    fun toDomain(entity: ActorEntity?): Actor? =
        entity?.let {
            Actor(
                idx = it.idx,
                profileUrl = it.profileUrl,
                name = it.name
            )
        }

}