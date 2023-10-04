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

    override fun findByActorContaining(actorIdx: Long): List<Movie> {
        val movieList = movieRepository.findByActorContaining(actorIdx)
        return movieList.map { movieMapper.toDomain(it)!! }
    }

    override fun findByMovieContaining(directorIdx: Long): List<Movie> {
        val movieList = movieRepository.findByDirectorContaining(directorIdx)
        return movieList.map { movieMapper.toDomain(it)!! }
    }

    override fun findAllByGenre(pageRequest: PageRequest, genre: Genre?): Page<Movie> {
        val movieList = movieRepository.findByGenre(pageRequest, genre)
        return movieList.map { movieMapper.toDomain(it) }
    }

    override fun findByGenre(genre: Genre): List<Movie> {
        val movieList = movieRepository.findByGenre(genre)
        return movieList.map { movieMapper.toDomain(it)!! }
    }

    override fun findById(idx: Long): Movie? {
        val movie = movieRepository.findByIdOrNull(idx)
        return movieMapper.toDomain(movie)
    }

    override fun findByTitleContaining(keyword: String): List<String> {
        val movie = movieRepository.findByTitleContaining(keyword)
        return movie.map { it.title }
    }

    override fun existsByGenre(genre: Genre?): Boolean {
        return movieRepository.existsByGenre(genre)
    }

}