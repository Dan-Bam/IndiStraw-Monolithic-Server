package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "actor")
class ActorEntity(
    @Column(name = "actor_idx", nullable = false)
    override val idx: Int,

    @Column(nullable = false)
    val profileUrl: String,

    @Column(nullable = false)
    val name: String
): BaseIdEntity(idx)