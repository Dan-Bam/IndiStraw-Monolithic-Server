package com.project.indistraw.domain.file.application.validator

import com.project.indistraw.domain.file.application.exception.NotValidExtensionException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class FileValidator {

    companion object {
        const val JPEG = "jpeg"
        const val JPG = "jpg"
        const val PNG = "png"
        const val MP4 = "mp4"
        const val GIF = "gif"
        const val BMP = "bmp"
        const val TIFF = "tiff"
        const val TIF = "tif"
        const val WEBP = "webp"
        const val PDF = "pdf"
        const val HWP = "hwp"
    }

    fun verifyExtensions(multipartFiles: List<MultipartFile>) {
        val allowedExtensions = setOf(JPEG, JPG, PNG, MP4, GIF, BMP, TIFF, TIF, WEBP, PDF, HWP)
        multipartFiles.map {
            val originalFileName = it.originalFilename ?: ""
            val fileExtension = originalFileName.substringAfterLast(".","").toLowerCase()

            if (fileExtension !in allowedExtensions)
                throw NotValidExtensionException()
        }
    }

}