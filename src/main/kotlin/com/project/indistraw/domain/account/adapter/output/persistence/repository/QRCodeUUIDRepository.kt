package com.project.indistraw.domain.account.adapter.output.persistence.repository

import com.project.indistraw.domain.account.adapter.output.persistence.entity.QRCodeUUIDEntity
import org.springframework.data.repository.CrudRepository
import java.util.*

interface QRCodeUUIDRepository: CrudRepository<QRCodeUUIDEntity, UUID>