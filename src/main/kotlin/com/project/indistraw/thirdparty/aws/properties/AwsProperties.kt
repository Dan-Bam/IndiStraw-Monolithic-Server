package com.project.indistraw.thirdparty.aws.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("cloud.aws.credentials")
class AwsProperties(
    val accessKey: String,
    val secretKey: String
)