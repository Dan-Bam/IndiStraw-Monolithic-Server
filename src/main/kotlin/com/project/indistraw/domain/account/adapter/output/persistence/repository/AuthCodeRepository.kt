package com.project.indistraw.domain.account.adapter.output.persistence.repository

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AuthCodeEntity
import org.springframework.data.repository.CrudRepository

interface AuthCodeRepository: CrudRepository<AuthCodeEntity, String>