package com.project.indistraw.domain.pay.application.port.output

interface QueryPayInfoPort {

    fun existByReceiptId(receiptId: String): Boolean

}