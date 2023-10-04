package com.project.indistraw.domain.search.application.port.input

interface RelatedSearchUseCase {

    fun execute(keyword: String): List<String>

}