package com.project.indistraw.domain.pay.application.port.output

interface PayPort {

    fun confirm(receiptId: String)
    fun cancel(receiptId: String)

}