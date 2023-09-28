package com.project.indistraw.domain.search.application.service

import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithReadOnlyTransaction
import com.project.indistraw.domain.search.application.port.input.PopularTagListUseCase
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.ZSetOperations

@ServiceWithReadOnlyTransaction
class PopularTagListService(
    private val redisTemplate: RedisTemplate<String, String>
): PopularTagListUseCase {

    override fun execute(): List<String> {
        val zSetOperations: ZSetOperations<String, String> = redisTemplate.opsForZSet()
        val typedTuples = zSetOperations.reverseRangeWithScores("ranking", 0, 3)
        return typedTuples!!.map { it.value!! }
    }

}