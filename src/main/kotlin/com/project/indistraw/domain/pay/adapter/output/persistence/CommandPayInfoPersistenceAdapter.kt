package com.project.indistraw.domain.pay.adapter.output.persistence

import com.project.indistraw.domain.pay.application.port.output.CommandPayInfoPort
import com.project.indistraw.domain.pay.adapter.output.persistence.mapper.PayInfoMapper
import com.project.indistraw.domain.pay.adapter.output.persistence.repository.PayInfoRepository
import com.project.indistraw.domain.pay.domain.PayInfo
import org.springframework.stereotype.Component

@Component
class CommandPayInfoPersistenceAdapter(
    private val payInfoMapper: PayInfoMapper,
    private val payInfoRepository: PayInfoRepository
): CommandPayInfoPort {

    override fun savePayInfo(payInfo: PayInfo) {
        val payInfoEntity = payInfoMapper toEntity payInfo
        payInfoRepository.save(payInfoEntity)
    }

    override fun deletePayInfo(payInfo: PayInfo) {
        val payInfoEntity = payInfoMapper toEntity payInfo
        payInfoRepository.delete(payInfoEntity)
    }

}