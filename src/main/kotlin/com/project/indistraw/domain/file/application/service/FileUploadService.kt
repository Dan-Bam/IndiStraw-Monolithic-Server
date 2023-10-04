package com.project.indistraw.domain.file.application.service

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.proejct.indistraw.domain.crowdfunding.application.common.annotation.ServiceWithTransaction
import com.project.indistraw.domain.file.application.validator.FileValidator
import com.project.indistraw.thirdparty.aws.properties.s3.AwsS3Properties
import org.springframework.web.multipart.MultipartFile
import java.io.IOException
import java.io.InputStream
import java.util.*

@ServiceWithTransaction
class FileUploadService(
    private val amazonS3: AmazonS3,
    private val awsS3Properties: AwsS3Properties,
    private val fileValidator: FileValidator
) {

    fun execute(file: MultipartFile): String {
        fileValidator.verifyExtensions(listOf(file))
        val fileName = createFileName(file.originalFilename.toString())
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentLength = file.size
        objectMetadata.contentType = file.contentType

        try {
            val inputStream: InputStream = file.inputStream
            amazonS3.putObject(
                PutObjectRequest(awsS3Properties.bucket, fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead)
            )
        } catch (e: IOException) {
            throw IllegalStateException("파일 업로드에 실패하였습니다.")
        }

        return fileName
    }

    private fun createFileName(fileName: String) =
        UUID.randomUUID().toString() + fileName

}