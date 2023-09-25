package com.project.indistraw.domain.file.adapter.input

import com.project.indistraw.domain.file.application.service.FileUploadService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/file")
class FileWebAdapter(
    private val fileUploadService: FileUploadService
) {

    @PostMapping
    fun uploadProfileImg(@RequestParam(name = "files") multipartFiles: List<MultipartFile>): ResponseEntity<List<Map<String, String>>> {
        return fileUploadService.execute(multipartFiles)
            .let { ResponseEntity.ok(it.map { mapOf("imageUrl" to it) }) }
    }

}