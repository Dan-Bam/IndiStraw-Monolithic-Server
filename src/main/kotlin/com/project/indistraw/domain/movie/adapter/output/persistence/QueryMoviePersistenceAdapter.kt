package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieRepository
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import com.project.indistraw.domain.movie.domain.Movie
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class QueryMoviePersistenceAdapter(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
): QueryMoviePort {

    override fun findAll(pageRequest: PageRequest): Page<Movie> {
        val movieList = movieRepository.findAll(pageRequest)
        return movieList.map { movieMapper.toDomain(it) }
    }

    override fun findAllByGenre(pageRequest: PageRequest, genre: Genre?): Page<Movie> {
        val movieList = movieRepository.findAllByGenre(pageRequest, genre)
        return movieList.map { movieMapper.toDomain(it) }
    }

    override fun findById(id: Int): Movie? {
        val movie = movieRepository.findByIdOrNull(id)
        return movieMapper.toDomain(movie)
    }

}