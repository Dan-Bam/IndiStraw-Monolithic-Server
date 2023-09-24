package com.project.indistraw.thirdparty.aws

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.project.indistraw.thirdparty.aws.properties.AwsProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig(
    private val awsProperties: AwsProperties
) {

    @Value("\${cloud.aws.region.static}")
    private lateinit var region: String

    @Bean
    fun amazonS3(): AmazonS3 {
        val awsCredentials: AWSCredentials = BasicAWSCredentials(awsProperties.accessKey, awsProperties.secretKey)
        return AmazonS3ClientBuilder.standard()
            .withRegion(region)
            .withCredentials(AWSStaticCredentialsProvider(awsCredentials))
            .build()
    }

}