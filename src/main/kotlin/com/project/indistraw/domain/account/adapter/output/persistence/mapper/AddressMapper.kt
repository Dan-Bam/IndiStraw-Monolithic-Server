package com.project.indistraw.domain.account.adapter.output.persistence.mapper

import com.project.indistraw.domain.account.adapter.output.persistence.entity.AddressEntity
import com.project.indistraw.domain.account.domain.Address
import org.springframework.stereotype.Component

@Component
class AddressMapper {

    fun toEntity(domain: Address?): AddressEntity? =
        domain?.let {
            AddressEntity(
                zipcode = domain.zipcode,
                streetAddress = domain.streetAddress,
                detailAddress = domain.detailAddress
            )
        }

    fun toDomain(entity: AddressEntity?): Address? =
        entity?.let {
            Address(
                zipcode = entity.zipcode,
                streetAddress = entity.streetAddress,
                detailAddress = entity.detailAddress
            )
        }

}