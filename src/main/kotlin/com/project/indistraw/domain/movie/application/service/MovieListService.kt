package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.Genre
import com.project.indistraw.domain.movie.application.port.input.MovieListUseCase
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto
import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@ServiceWithReadOnlyTransaction
class MovieListService(
    private val queryMoviePort: QueryMoviePort
): MovieListUseCase {

    override fun execute(pageable: Pageable, genre: String?): MoviePagingDto {
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order.desc("createdAt")))
        val movieList = if (genre == null) {
            queryMoviePort.findAll(pageRequest)
        } else {
            queryMoviePort.findAllByGenre(pageRequest, Genre.valueOf(genre))
        }

        val movieListDto = movieList.map{
            MovieDto(
                id = it.id,
                thumbnailUrl = it.thumbnailUrl
            )
        }.toList()

        return MoviePagingDto(
            isLast = movieList.isLast,
            list = movieListDto
        )
    }

}