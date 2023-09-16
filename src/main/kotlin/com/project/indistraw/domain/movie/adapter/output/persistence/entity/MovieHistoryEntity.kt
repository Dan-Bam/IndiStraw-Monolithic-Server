package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdEntity
import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import javax.persistence.*

@Entity
@Table(name = "movie_history")
class MovieHistoryEntity(
    @Column(name = "movie_history_id", nullable = false)
    override val id: Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: AccountEntity,

    @Column(nullable = false)
    var historyTime: Float
): BaseIdEntity(id)