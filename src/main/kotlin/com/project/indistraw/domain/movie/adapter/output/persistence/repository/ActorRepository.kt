package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.ActorEntity
import org.springframework.data.repository.CrudRepository

interface ActorRepository: CrudRepository<ActorEntity, Long> {
    fun findByNameContaining(name: String): List<ActorEntity>
    fun save(actorEntity: ActorEntity): ActorEntity
    fun existsByIdx(idx: Long): Boolean
}