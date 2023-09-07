package com.project.indistraw.domain.crowdfunding.application.event

import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingPort
import com.project.indistraw.domain.crowdfunding.application.port.output.CommandCrowdfundingViewPort
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryCrowdfundingViewPort
import com.project.indistraw.domain.crowdfunding.application.port.output.QueryRequestIpPort
import com.project.indistraw.domain.crowdfunding.domain.CrowdfundingViewCount
import mu.KotlinLogging
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class QueryCrowdfundingEventHandler(
    private val queryCrowdfundingViewPort: QueryCrowdfundingViewPort,
    private val commandCrowdfundingViewPort: CommandCrowdfundingViewPort,
    private val commandCrowdfundingPort: CommandCrowdfundingPort,
    private val queryRequestIpPort: QueryRequestIpPort
) {

    @EventListener
    fun updateCrowdfundingView(queryCrowdfundingEvent: QueryCrowdfundingEvent) {
        log.info("updateCrowdfundingViewEvent is active")

        val crowdfunding = queryCrowdfundingEvent.crowdfunding
        val currentIp = queryRequestIpPort.getCurrentRequestIp()
        if (queryCrowdfundingViewPort.existsByIdx(crowdfunding.idx)) {
            val crowdfundingViewCount = queryCrowdfundingViewPort.findByIdx(crowdfunding.idx)

            // crowdfundingViewCount에 존재하지 않은 ip라면 viewCount를 증가시킨다.
            if (!crowdfundingViewCount.ips.contains(currentIp)) {
                commandCrowdfundingViewPort.saveCrowdfundingView(crowdfundingViewCount.addIp(currentIp))
                commandCrowdfundingPort.saveCrowdfunding(crowdfunding.increaseViewCount())
            }
        } else {
            commandCrowdfundingPort.saveCrowdfunding(crowdfunding.increaseViewCount())
            commandCrowdfundingViewPort.saveCrowdfundingView(CrowdfundingViewCount(crowdfunding.idx, setOf(currentIp)))
        }
    }

}