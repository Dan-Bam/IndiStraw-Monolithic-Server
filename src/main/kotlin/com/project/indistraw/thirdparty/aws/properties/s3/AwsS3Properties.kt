package com.project.indistraw.thirdparty.aws.properties.s3

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "cloud.aws.s3")
@ConstructorBinding
class AwsS3Properties(
    val bucket: String
)