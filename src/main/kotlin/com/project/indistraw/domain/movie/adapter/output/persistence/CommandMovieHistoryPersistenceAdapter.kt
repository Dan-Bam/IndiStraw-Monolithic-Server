package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieHistoryMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieHistoryRepository
import com.project.indistraw.domain.movie.application.port.output.CommandMovieHistoryPort
import com.project.indistraw.domain.movie.domain.MovieHistory
import org.springframework.stereotype.Component

@Component
class CommandMovieHistoryPersistenceAdapter(
    private val movieHistoryMapper: MovieHistoryMapper,
    private val movieHistoryRepository: MovieHistoryRepository
): CommandMovieHistoryPort {

    override fun saveMovieHistory(movieHistory: MovieHistory) {
        movieHistoryRepository.save(movieHistoryMapper.toEntity(movieHistory))
    }

}