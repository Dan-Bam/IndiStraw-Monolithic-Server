package com.project.indistraw.domain.search.application.port.input

interface PopularTagListUseCase {

    fun execute(): List<String>

}