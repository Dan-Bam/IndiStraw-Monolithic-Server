package com.project.indistraw.domain.search.adapter.input

import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import com.project.indistraw.domain.movie.adapter.input.mapper.MovieDataMapper
import com.project.indistraw.domain.search.application.port.input.MovieSearchUseCase
import com.project.indistraw.domain.search.application.port.input.PopularTagListUseCase
import com.project.indistraw.domain.search.application.port.input.RelatedSearchUseCase
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchWebAdapter(
    private val relatedSearchUseCase: RelatedSearchUseCase,
    private val movieSearchUseCase: MovieSearchUseCase,
    private val popularTagListUseCase: PopularTagListUseCase,
    private val movieDataMapper: MovieDataMapper
) {

    @GetMapping
    fun findRelatedData(@RequestParam(name = "keyword") keyword: String): ResponseEntity<List<String>> =
        relatedSearchUseCase.execute(keyword)
            .let { ResponseEntity.ok(it) }

    @GetMapping("movie")
    fun findMovieList(@PageableDefault(size=10, page = 0) pageable: Pageable, @RequestParam("keyword") keyword: String): ResponseEntity<MoviePagingResponse> =
        movieSearchUseCase.execute(pageable, keyword)
            .let { movieDataMapper.toResponse(it) }
            .let { ResponseEntity.ok(it) }

    @GetMapping("tag")
    fun findPopularTag(): ResponseEntity<List<Map<String, String>>> {
        val let = popularTagListUseCase.execute()
        return ResponseEntity.ok(let.map { mapOf("tag_list" to it) })
    }

}