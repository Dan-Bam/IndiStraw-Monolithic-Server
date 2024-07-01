package com.project.indistraw.thirdparty.feign.client

import com.project.indistraw.domain.movie.adapter.input.data.response.MoviePagingResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(value = "movie", url = "http://localhost:8080")
interface MovieClient {

    @GetMapping("/api/v1/movie")
    fun movieList(): MoviePagingResponse

}