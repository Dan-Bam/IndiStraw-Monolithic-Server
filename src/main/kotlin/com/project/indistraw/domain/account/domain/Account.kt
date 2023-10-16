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
    val authority: Authority,
    var actor: MutableList<Long>?,
    var director: MutableList<Long>?,
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

    fun addActor(idx: Long): Account {
        val updateActors = if (this.actor.isNullOrEmpty()) {
            mutableListOf(idx)
        } else {
            val mutableActors = this.actor!!.toMutableList()
            mutableActors.add(idx)
            mutableActors
        }
        return this.copy(actor = updateActors)
    }

    fun addDirector(idx: Long): Account {
        val updateDirectors = if (this.director.isNullOrEmpty()) {
            mutableListOf(idx)
        } else {
            val mutableDirectors = this.director!!.toMutableList()
            mutableDirectors.add(idx)
            mutableDirectors
        }
        return this.copy(director = updateDirectors)
    }

}