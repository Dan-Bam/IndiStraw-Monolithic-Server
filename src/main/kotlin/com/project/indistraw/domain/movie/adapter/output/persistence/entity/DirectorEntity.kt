package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "director")
class DirectorEntity(
    @Column(name = "director_idx", nullable = false)
    override val idx: Long,

    @Column(nullable = false)
    val profileUrl: String,

    @Column(nullable = false)
    val name: String
): BaseIdxEntity(idx)