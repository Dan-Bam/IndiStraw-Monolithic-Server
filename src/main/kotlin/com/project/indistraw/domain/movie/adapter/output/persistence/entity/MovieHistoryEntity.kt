package com.project.indistraw.domain.movie.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseIdxEntity
import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import javax.persistence.*

@Entity
@Table(name = "movie_history")
class MovieHistoryEntity(
    @Column(name = "movie_history_idx", nullable = false)
    override val idx: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_idx")
    val movie: MovieEntity,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_idx")
    val account: AccountEntity,

    @Column(nullable = false)
    var historyTime: Float
): BaseIdxEntity(idx)