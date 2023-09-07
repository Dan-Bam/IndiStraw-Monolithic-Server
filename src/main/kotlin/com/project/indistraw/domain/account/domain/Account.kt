package com.project.indistraw.domain.account.domain

import java.util.UUID

data class Account(
    val accountIdx: UUID,
    val id: String,
    var encodedPassword: String,
    var name: String,
    var phoneNumber: String,
    var address: Address?,
    var profileUrl: String?,
    val authority: Authority
) {

    fun updateInfo(name: String, profileUrl: String?): Account {
        this.name = name
        this.profileUrl = profileUrl
        return this
    }

    fun updateAddress(address: Address): Account {
        this.address = address
        return this
    }

    fun updateEncodedPassword(encodedPassword: String): Account {
        this.encodedPassword = encodedPassword
        return this
    }

    fun updatePhoneNumber(phoneNumber: String): Account {
        this.phoneNumber = phoneNumber
        return this
    }

}