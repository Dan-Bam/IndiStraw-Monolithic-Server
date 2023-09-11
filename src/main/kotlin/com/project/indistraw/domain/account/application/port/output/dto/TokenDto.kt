package com.project.indistraw.domain.account.application.port.output.dto

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TokenDto(
    val accessToken: String,
    val refreshToken: String,
    val accessTokenExpiredAt: Long,
    val refreshTokenExpiredAt: Long
) {

    override fun toString(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")
        return "{" +
                "\"accessToken\":" + "\"" + this.accessToken + "\"," +
                "\"refreshToken\":" + "\"" + this.refreshToken + "\"," +
                "\"accessTokenExpiredAt\":" + "\"" + LocalDateTime.now().plusSeconds(this.accessTokenExpiredAt).format(formatter) + "\"," +
                "\"refreshTokenExpiredAt\":" + "\"" + LocalDateTime.now().plusSeconds(this.refreshTokenExpiredAt).format(formatter) + "\"}"
    }

}
