package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieHistoryMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieHistoryRepository
import com.project.indistraw.domain.movie.application.port.output.QueryMovieHistoryPort
import com.project.indistraw.domain.movie.domain.Movie
import com.project.indistraw.domain.movie.domain.MovieHistory
import org.springframework.stereotype.Component

@Component
class QueryMovieHistoryPersistenceAdapter(
    private val movieHistoryMapper: MovieHistoryMapper,
    private val movieMapper: MovieMapper,
    private val movieHistoryRepository: MovieHistoryRepository
): QueryMovieHistoryPort {

    override fun findByMovie(movie: Movie): MovieHistory? {
        val movieHistory = movieHistoryRepository.findByMovie(movieMapper.toEntity(movie))
        return movieHistoryMapper.toDomain(movieHistory)
    }

}