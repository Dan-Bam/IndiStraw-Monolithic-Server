package com.project.indistraw.global.warmup

import com.project.indistraw.thirdparty.feign.client.MovieClient
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

@Component
class WarmUpRunner (
    private val movieClient: MovieClient
): ApplicationRunner {

    override fun run(args: ApplicationArguments?) {
        runCatching {
            movieClient.movieList()
        }.onFailure {  }
    }

}