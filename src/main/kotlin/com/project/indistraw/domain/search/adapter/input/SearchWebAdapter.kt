package com.project.indistraw.domain.search.adapter.input

import com.project.indistraw.domain.search.application.port.input.PopularTagListUseCase
import com.project.indistraw.domain.search.application.port.input.SearchMovieUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("search")
class SearchWebAdapter(
    private val searchMovieUseCase: SearchMovieUseCase,
    private val popularTagListUseCase: PopularTagListUseCase
) {

    @GetMapping
    fun findMovieData(@RequestParam(name = "keyword") keyword: String): ResponseEntity<List<String>> =
        searchMovieUseCase.execute(keyword)
            .let { ResponseEntity.ok(it) }

    @GetMapping("tag")
    fun findPopularTag(): ResponseEntity<List<Map<String, String>>> {
        val let = popularTagListUseCase.execute()
        return ResponseEntity.ok(let.map { mapOf("tag_list" to it) })
    }

}