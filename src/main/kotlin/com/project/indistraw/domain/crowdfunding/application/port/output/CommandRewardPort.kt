package com.project.indistraw.domain.crowdfunding.application.port.output

import com.project.indistraw.domain.crowdfunding.domain.Reward

interface CommandRewardPort {

    fun saveReward(reward: Reward)
    fun saveAllReword(rewordList: List<Reward>)

}