package com.project.indistraw.domain.movie.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations

@ServiceWithReadOnlyTransaction
class PopularTagListService(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun execute(): List<String> {
        val zSetOperations: ZSetOperations<String, String> = redisTemplate.opsForZSet()
        val typedTuples = zSetOperations.reverseRangeWithScores("ranking", 0, 3)
        return typedTuples!!.map { it.value!! }
    }

}