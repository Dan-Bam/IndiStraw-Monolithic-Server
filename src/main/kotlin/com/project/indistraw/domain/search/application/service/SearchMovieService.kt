package com.project.indistraw.domain.search.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingPort
import com.project.indistraw.domain.movie.adapter.output.persistence.entity.toGenre
import com.project.indistraw.domain.search.application.port.input.SearchMovieUseCase
import com.project.indistraw.domain.movie.application.port.output.QueryActorPort
import com.project.indistraw.domain.movie.application.port.output.QueryDirectorPort
import com.project.indistraw.domain.movie.application.port.output.QueryMoviePort
import org.springframework.data.redis.core.RedisTemplate

@ServiceWithReadOnlyTransaction
class SearchMovieService(
    private val queryMoviePort: QueryMoviePort,
    private val queryActorPort: QueryActorPort,
    private val queryDirectorPort: QueryDirectorPort,
    private val queryCrowdfundingPort: QueryCrowdfundingPort,
    private val redisTemplate: RedisTemplate<String, String>
): SearchMovieUseCase {

    override fun execute(keyword: String): List<String> {
        val movieList = queryMoviePort.findByTitleContaining(keyword)
        val actorList = queryActorPort.findByNameContaining(keyword)
        val directorList = queryDirectorPort.findByNameContaining(keyword)
        val crowdfundingList = queryCrowdfundingPort.findByTitleContaining(keyword)
        val genre = keyword.toGenre()

        val result = if (queryMoviePort.existsByGenre(genre)) {
            val movieListByGenre = queryMoviePort.findByGenre(genre!!)
            val uniqueMovieTitles = (movieListByGenre.map { it.title } + movieList.map { it }).distinct()
            val allNames = (actorList.map { it.name } + directorList.map { it.name })
            val allTitles = (uniqueMovieTitles + crowdfundingList.map { it.title })
            val combinedResult = allNames + allTitles
            saveSearchResultToRedis(keyword)
            combinedResult
        } else {
            val allNames = (actorList.map { it.name } + directorList.map { it.name })
            val allTitles = (movieList.map { it } + crowdfundingList.map { it.title })
            val combinedResult = allNames + allTitles
            saveSearchResultToRedis(keyword)
            combinedResult
        }

        return result
    }

    private fun saveSearchResultToRedis(keyword: String) {
        redisTemplate.opsForZSet().incrementScore("ranking", keyword, 1.0);
    }

}