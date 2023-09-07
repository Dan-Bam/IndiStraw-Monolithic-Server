package com.project.indistraw.domain.pay.domain

import java.util.UUID

data class PayInfo(
    val receiptId: String,
    val accountIdx: UUID
)