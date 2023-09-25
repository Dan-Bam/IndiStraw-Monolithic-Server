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

    fun execute(files: List<MultipartFile>): List<String> {
        fileValidator.verifyExtensions(files)
        val fileNameList = ArrayList<String>()

        files.map {
            it.let { uploadedFile ->
                val fileName = createFileName(uploadedFile.originalFilename.toString())
                val objectMetadata = ObjectMetadata()
                objectMetadata.contentLength = uploadedFile.size
                objectMetadata.contentType = uploadedFile.contentType
                try {
                    val inputStream: InputStream = uploadedFile.inputStream
                    amazonS3.putObject(
                        PutObjectRequest(awsS3Properties.bucket, fileName, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead)
                    )
                    fileNameList.add(fileName)
                } catch (e: IOException) {
                    throw IllegalStateException("파일 업로드에 실패하였습니다.")
                }
            }
        }

        return fileNameList
    }

    private fun createFileName(fileName: String) =
        UUID.randomUUID().toString() + fileName

}