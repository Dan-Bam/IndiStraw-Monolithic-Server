package com.project.indistraw.domain.pay.adapter.output.persistence.repository

import com.project.indistraw.domain.pay.adapter.output.persistence.entity.PayInfoEntity
import org.springframework.data.repository.CrudRepository

interface PayInfoRepository: CrudRepository<PayInfoEntity, String>