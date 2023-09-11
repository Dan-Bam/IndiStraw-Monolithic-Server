package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.adapter.output.persistence.repository.MovieRepository
import com.project.indistraw.domain.movie.application.port.input.FindMovieListUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto

@ServiceWithReadOnlyTransaction
class FindMovieListService(
    private val movieRepository: MovieRepository
): FindMovieListUseCase {

    override fun execute(genre: Genre?): List<MovieDto> {
        if (genre == null) {
           return movieRepository.findAll()
                .map {
                    MovieDto(
                        idx = it.idx,
                        thumbnailUrl = it.thumbnailUrl
                    )
                }
        }
        else {
            return movieRepository.findByGenre(genre)
                .map {
                    MovieDto(
                        idx = it.idx,
                        thumbnailUrl = it.thumbnailUrl
                    )
                }
        }
    }

}


