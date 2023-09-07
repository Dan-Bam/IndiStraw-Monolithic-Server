package com.project.indistraw.domain.account.adapter.input

import com.project.indistraw.domain.account.adapter.input.data.request.SignInRequest
import com.project.indistraw.domain.account.adapter.input.data.request.SignUpRequest
import com.project.indistraw.domain.account.adapter.input.data.response.TokenResponse
import com.project.indistraw.domain.account.adapter.input.mapper.AuthDataMapper
import com.project.indistraw.domain.account.application.common.enums.CheckPhoneNumberType
import com.project.indistraw.domain.account.application.port.input.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("api/v1/auth")
class AuthWebAdapter(
    private val authDataMapper: AuthDataMapper,
    private val signUpUseCase: SignUpUseCase,
    private val signInUseCase: SignInUseCase,
    private val reissueTokenUseCase: ReissueTokenUseCase,
    private val logoutAccountUseCase: LogoutAccountUseCase,
    private val checkAccountIdUseCase: CheckAccountIdUseCase,
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val sendAuthCodeUseCase: SendAuthCodeUseCase,
    private val verifyAuthCodeUseCase: VerifyAuthCodeUseCase
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid request: SignUpRequest): ResponseEntity<Void> =
        signUpUseCase.execute(authDataMapper toDto request)
            .run { ResponseEntity.status(HttpStatus.CREATED).build() }

    @PostMapping("/signin")
    fun signIn(@RequestBody @Valid request: SignInRequest): ResponseEntity<TokenResponse> =
        signInUseCase.execute(authDataMapper toDto request)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @PatchMapping("/reissue")
    fun reissueToken(@RequestHeader refreshToken: String): ResponseEntity<TokenResponse> =
        reissueTokenUseCase.execute(refreshToken)
            .let { authDataMapper toResponse it }
            .let { ResponseEntity.ok(it) }

    @DeleteMapping("/logout")
    fun logoutAccount(@RequestHeader refreshToken: String): ResponseEntity<Void> =
        logoutAccountUseCase.execute(refreshToken)
            .let { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @RequestMapping(value = ["/check/id/{id}"], method = [RequestMethod.HEAD])
    fun checkAccountId(@PathVariable id: String): ResponseEntity<Void> =
        checkAccountIdUseCase.execute(id)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @RequestMapping(value = ["/check/phone-number/{phoneNumber}/type/{type}"], method = [RequestMethod.HEAD])
    fun checkPhoneNumber(@PathVariable phoneNumber: String, @PathVariable type: CheckPhoneNumberType): ResponseEntity<Void> =
        checkPhoneNumberUseCase.execute(phoneNumber, type)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @PostMapping("/send/phone-number/{phoneNumber}")
    fun sendAuthCode(@PathVariable phoneNumber: String): ResponseEntity<Void> =
        sendAuthCodeUseCase.execute(phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

    @GetMapping("/auth-code/{authCode}/phone-number/{phoneNumber}")
    fun verifyAuthCode(@PathVariable authCode: Int, @PathVariable phoneNumber: String): ResponseEntity<Void> =
        verifyAuthCodeUseCase.execute(authCode, phoneNumber)
            .run { ResponseEntity.status(HttpStatus.NO_CONTENT).build() }

}