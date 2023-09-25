package com.project.indistraw.global.config

import com.project.indistraw.domain.account.adapter.output.message.properties.CoolSmsProperties
import com.project.indistraw.domain.pay.adapter.output.pay.property.BootPayProperties
import com.project.indistraw.thirdparty.aws.properties.AwsProperties
import com.project.indistraw.thirdparty.aws.properties.s3.AwsS3Properties
import com.project.indistraw.global.security.token.common.properties.JwtExpTimeProperties
import com.project.indistraw.global.security.token.common.properties.JwtProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class,
        BootPayProperties::class,
        CoolSmsProperties::class,
        AwsProperties::class,
        AwsS3Properties::class
    ]
)
class PropertiesScanConfig