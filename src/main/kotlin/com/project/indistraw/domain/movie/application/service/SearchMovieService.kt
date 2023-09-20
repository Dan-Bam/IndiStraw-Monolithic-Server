package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.toGenre
import com.project.indistraw.domain.movie.application.port.input.SearchMovieUseCase
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import com.project.indistraw.domain.movie.domain.Movie

@ServiceWithReadOnlyTransaction
class SearchMovieService(
    private val queryMoviePort: QueryMoviePort,
    private val queryActorPort: QueryActorPort,
    private val queryDirectorPort: QueryDirectorPort,
    private val queryCrowdfundingPort: QueryCrowdfundingPort
): SearchMovieUseCase {

    override fun execute(keyword: String): List<String> {
        val movieList = queryMoviePort.findByTitleContaining(keyword)
        val actorList = queryActorPort.findByNameContaining(keyword)
        val directorList = queryDirectorPort.findByNameContaining(keyword)
        val crowdfundingList = queryCrowdfundingPort.findByTitleContaining(keyword)
        var movieListByGenre: List<Movie>
        val genre = keyword.toGenre()

        return if (queryMoviePort.existsByGenre(genre)) {
            movieListByGenre = genre?.let { queryMoviePort.findByGenre(it) }!!
            (actorList.map { it.name } + directorList.map { it.name }) +
                    (movieListByGenre.map { it.title } + movieList.map { it }).distinct() +
                    (crowdfundingList.map { it.title })
        } else {
            (actorList.map { it.name } + directorList.map { it.name }) +
                    (movieList.map { it }) +
                    (crowdfundingList.map { it.title })
        }
    }

}