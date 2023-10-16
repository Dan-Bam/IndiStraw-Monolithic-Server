package com.project.indistraw.domain.account.adapter.output.persistence.entity

import com.project.indistraw.common.entity.BaseUUIDEntity
import com.project.indistraw.domain.account.domain.Authority
import org.hibernate.annotations.SQLDelete
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account")
@SQLDelete(sql = "update account set deleted_at = CURRENT_TIMESTAMP where account_idx = ?")
class AccountEntity(
    @Column(name = "account_idx", columnDefinition = "BINARY(16)", nullable = false)
    override val accountIdx: UUID,

    @Column(nullable = false, length = 20)
    val id: String,

    @Column(nullable = false, length = 60)
    val encodedPassword: String,

    @Column(nullable = false, length = 10)
    val name: String,

    @Column(nullable = false, length = 15)
    val phoneNumber: String,

    @Embedded
    val address: AddressEntity?,

    @Column(nullable = true, columnDefinition = "TEXT")
    val profileUrl: String?,

    @Enumerated(EnumType.STRING)
    val authority: Authority,

    @ElementCollection
    @Column(name = "account_actor")
    var actor: MutableList<Long>?
): BaseUUIDEntity(accountIdx)