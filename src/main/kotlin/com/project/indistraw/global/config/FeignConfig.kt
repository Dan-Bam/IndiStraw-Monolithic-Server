package com.project.indistraw.global.config

import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration

@EnableFeignClients(basePackages = ["com.project.indistraw.thirdparty.feign"])
@Configuration
class FeignConfig