package com.project.indistraw.domain.pay.adapter.output.pay

import com.project.indistraw.domain.pay.adapter.output.pay.exception.FailedPayCancelException
import com.project.indistraw.domain.pay.adapter.output.pay.exception.FailedPayConfirmException
import com.project.indistraw.domain.pay.adapter.output.pay.property.BootPayProperties
import com.project.indistraw.domain.pay.application.port.output.PayPort
import kr.co.bootpay.Bootpay
import kr.co.bootpay.model.request.Cancel
import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {  }

@Component
class PayAdapter(
    private val bootPayProperties: BootPayProperties
): PayPort {

    override fun confirm(receiptId: String) {
        val bootpay = Bootpay(bootPayProperties.restApplicationId, bootPayProperties.privateKey)
        // bootpay 연동 전 accessToken 발급
        bootpay.accessToken
        val res = bootpay.confirm(receiptId)
        if (res["error_code"] != null) {
            log.error("receiptId confirm failed $receiptId")
            log.error("bootpay confirm failed message is ${res["message"]}")
            throw FailedPayConfirmException()
        }

        log.info("receiptId confirm success $receiptId")
    }

    override fun cancel(receiptId: String) {
        val bootpay = Bootpay(bootPayProperties.restApplicationId, bootPayProperties.privateKey)
        // bootpay 연동 전 accessToken 발급
        bootpay.accessToken
        val cancel = Cancel()
        cancel.let {
            it.receiptId = receiptId
            it.cancelUsername = ""
            it.cancelMessage = "test"
        }

        val res = bootpay.receiptCancel(cancel)
        if (res["error_code"] != null) {
            log.error("receiptId cancel failed $receiptId")
            log.error("bootpay cancel failed message is ${res["message"]}")
            throw FailedPayCancelException()
        }

        log.info("receiptId cancel success $receiptId")
    }

}