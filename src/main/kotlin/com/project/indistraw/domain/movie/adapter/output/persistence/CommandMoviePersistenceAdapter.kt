package com.project.indistraw.domain.movie.adapter.output.persistence

import com.project.indistraw.domain.movie.adapter.output.persistence.mapper.MovieMapper
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieRepository
import com.project.indistraw.domain.movie.application.port.output.CommandMoviePort
import com.project.indistraw.domain.movie.domain.Movie
import org.springframework.stereotype.Component

@Component
class CommandMoviePersistenceAdapter(
    private val movieRepository: MovieRepository,
    private val movieMapper: MovieMapper
): CommandMoviePort {

    override fun saveMovie(movie: Movie) {
        movieRepository.save(movieMapper.toEntity(movie))
    }

}