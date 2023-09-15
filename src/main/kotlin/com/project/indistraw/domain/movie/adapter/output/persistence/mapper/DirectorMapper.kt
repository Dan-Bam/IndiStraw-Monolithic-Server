package com.project.indistraw.domain.movie.adapter.output.persistence.mapper

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.DirectorEntity
import com.project.indistraw.domain.movie.domain.Director
import org.springframework.stereotype.Component

@Component
class DirectorMapper {

    fun toEntity(domain: Director): DirectorEntity =
        DirectorEntity(
            idx = domain.idx,
            profileUrl = domain.profileUrl,
            name = domain.name,
        )

    fun toDomain(entity: DirectorEntity?): Director? =
        entity?.let {
            Director(
                idx = entity.idx,
                profileUrl = entity.profileUrl,
                name = entity.name
            )
        }

}