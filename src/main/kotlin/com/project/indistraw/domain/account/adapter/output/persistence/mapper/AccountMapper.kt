package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AccountEntity
import com.project.indistraw.domain.account.domain.Account
import org.springframework.stereotype.Component

@Component
class AccountMapper(
    private val addressMapper: AddressMapper
) {

    fun toEntity(domain: Account): AccountEntity =
        AccountEntity(
            accountIdx = domain.accountIdx,
            id = domain.id,
            encodedPassword = domain.encodedPassword,
            name = domain.name,
            phoneNumber = domain.phoneNumber,
            address = addressMapper.toEntity(domain.address),
            profileUrl = domain.profileUrl,
            authority = domain.authority
        )

    fun toDomain(entity: AccountEntity?): Account? =
        entity?.let {
            Account(
                accountIdx = entity.accountIdx,
                id = entity.id,
                encodedPassword = entity.encodedPassword,
                name = entity.name,
                phoneNumber = entity.phoneNumber,
                address = addressMapper.toDomain(entity.address),
                profileUrl = entity.profileUrl,
                authority = entity.authority
            )
        }

}