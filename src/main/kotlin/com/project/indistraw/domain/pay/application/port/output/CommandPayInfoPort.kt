package com.project.indistraw.domain.pay.application.port.output

import com.project.indistraw.domain.pay.domain.PayInfo

interface CommandPayInfoPort {

    fun savePayInfo(payInfo: PayInfo)
    fun deletePayInfo(payInfo: PayInfo)

}