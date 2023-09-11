package com.project.indistraw.domain.movie.adapter.output.persistence.repository

import com.project.indistraw.domain.movie.adapter.output.persistence.entity.MovieEntity
import org.springframework.data.repository.CrudRepository

interface MovieRepository: CrudRepository<MovieEntity, Int>