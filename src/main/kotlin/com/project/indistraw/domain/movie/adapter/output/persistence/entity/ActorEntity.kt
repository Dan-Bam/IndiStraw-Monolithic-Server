package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import javax.persistence.*

@Entity
@Table(name = "actor")
class ActorEntity(
    @Column(name = "actor_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val profileUrl: String,

    @Column(nullable = false)
    val name: String
): BaseIdxEntity(idx)