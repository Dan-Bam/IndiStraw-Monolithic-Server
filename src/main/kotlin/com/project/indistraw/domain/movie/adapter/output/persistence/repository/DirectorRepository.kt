package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.DirectorEntity
import org.springframework.data.repository.CrudRepository

interface DirectorRepository: CrudRepository<DirectorEntity, Int>