package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdEntity
import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "movie")
class MovieEntity(
    @Column(name = "movie_id", nullable = false)
    override val id: Int,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val description: String,

    @Column(nullable = false)
    val movieUrl: String,

    @Column(nullable = false)
    val thumbnailUrl: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: AccountEntity,

    @ElementCollection
    @Column(name = "movie_actor")
    var actor: List<Int>,

    @ElementCollection
    @Column(name = "movie_director")
    var director: List<Int>,

    var clowdTrue: Boolean,

    @ElementCollection
    @Column(name = "movie_highlight")
    val movieHighLight: List<String>,

    @Enumerated(EnumType.STRING)
    val genre: Genre,

    @Column(nullable = false, updatable = false)
    val createdAt: LocalDateTime,
): BaseIdEntity(id)