package com.project.indistraw.domain.account.adapter.input

import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAccountInfoRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdateAddressRequest
import com.project.indistraw.domain.account.adapter.input.data.request.UpdatePasswordRequest
import com.project.indistraw.domain.account.adapter.input.data.response.AccountInfoResponse
import com.project.indistraw.domain.account.adapter.input.mapper.AccountDataMapper
import com.project.indistraw.domain.account.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/account")
class AccountWebAdapter(
    private val accountDataMapper: AccountDataMapper,
    private val findAccountIdUseCase: FindAccountIdUseCase,
    private val updatePasswordUseCase: UpdatePasswordUseCase,
    private val updatePhoneNumberUseCase: UpdatePhoneNumberUseCase,
    private val updateAddressUseCase: UpdateAddressUseCase,
    private val updateAccountInfoUseCase: UpdateAccountInfoUseCase,
    private val findAccountInfoUseCase: FindAccountInfoUseCase,
    private val accountWithdrawUseCase: AccountWithdrawUseCase,
) {

    @GetMapping("/phone-number/{phoneNumber}")
    fun findAccountId(@PathVariable phoneNumber: String): ResponseEntity<Map<String, String>> =
        findAccountIdUseCase.execute(phoneNumber)
            .let { ResponseEntity.ok(mapOf("id" to it)) }

    @PatchMapping("/password")
    fun updatePassword(@RequestBody request: UpdatePasswordRequest): ResponseEntity<Void> =
        updatePasswordUseCase.execute(accountDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/phone-number/{phoneNumber}")
    fun updatePhoneNumber(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        updatePhoneNumberUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/address")
    fun updateAddress(@RequestBody request: UpdateAddressRequest): ResponseEntity<Void> =
        updateAddressUseCase.execute(accountDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @PatchMapping("/info")
    fun updateAccountInfo(@RequestBody request: UpdateAccountInfoRequest): ResponseEntity<Void> =
        updateAccountInfoUseCase.execute(accountDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

    @GetMapping("/info")
    fun findAccountInfo(): ResponseEntity<AccountInfoResponse> =
        findAccountInfoUseCase.execute()
            .let { accountDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping
    fun accountWithdraw(): ResponseEntity<Void> =
        accountWithdrawUseCase.execute()
            .run { ResponseEntity.status(HttpStatus.RESET_CONTENT).build() }

}