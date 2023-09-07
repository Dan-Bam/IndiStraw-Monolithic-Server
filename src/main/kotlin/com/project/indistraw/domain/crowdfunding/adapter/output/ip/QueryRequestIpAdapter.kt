package com.project.indistraw.domain.crowdfunding.adapter.output.ip

import com.project.indistraw.domain.crowdfunding.application.port.output.QueryRequestIpPort
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import javax.servlet.http.HttpServletRequest

@Component
class QueryRequestIpAdapter: QueryRequestIpPort {

    override fun getCurrentRequestIp(): String {
        val requestContextHolder = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes
        val httpServletRequest = requestContextHolder.request
        return getIp(httpServletRequest)
    }

    private fun getIp(request: HttpServletRequest): String {
        var ip = request.getHeader("X-Forwarded-For")
        if (ip.isNullOrBlank() || "unknown".equals(ip, true)) ip = request.getHeader("Proxy-Client-IP")
        if (ip.isNullOrBlank() || "unknown".equals(ip, true)) ip = request.getHeader("WL-Proxy-Client-IP")
        if (ip.isNullOrBlank() || "unknown".equals(ip, true)) ip = request.getHeader("HTTP_CLIENT_IP")
        if (ip.isNullOrBlank() || "unknown".equals(ip, true)) ip = request.getHeader("HTTP_X_FORWARDED_FOR")
        if (ip.isNullOrBlank() || "unknown".equals(ip, true)) ip = request.remoteAddr
        return ip
    }

}