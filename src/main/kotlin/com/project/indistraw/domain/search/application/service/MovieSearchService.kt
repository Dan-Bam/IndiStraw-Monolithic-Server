package com.project.indistraw.domain.search.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.movie.application.port.input.dto.MovieDto
import com.project.indistraw.domain.movie.application.port.input.dto.MoviePagingDto
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import com.project.indistraw.domain.search.application.port.input.MovieSearchUseCase
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

@ServiceWithReadOnlyTransaction
class MovieSearchService(
    private val queryMoviePort: QueryMoviePort
): MovieSearchUseCase {

    override fun execute(pageable: Pageable, keyword: String): MoviePagingDto {
        val pageRequest = PageRequest.of(pageable.pageNumber, pageable.pageSize, Sort.by(Sort.Order.desc("createdAt")))
        val movieList = queryMoviePort.findByPageableTitleContaining(pageRequest, keyword)

        val movieListDto = movieList.map{
            MovieDto(
                idx = it.idx,
                thumbnailUrl = it.thumbnailUrl
            )
        }.toList()

        return MoviePagingDto(
            isLast = movieList.isLast,
            list = movieListDto
        )
    }

}