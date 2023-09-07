package com.project.indistraw.global.error

enum class ErrorCode(
    val message: String,
    val status: Int
) {

    // ACCOUNT
    DUPLICATE_PHONE_NUMBER("중복된 전화번호 입니다.", 409),
    DUPLICATE_ACCOUNT_ID("중복된 id 입니다.", 409),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다.", 400),
    ACCOUNT_NOT_FOUND("계정을 찾을 수 없습니다.", 404),
    DUPLICATE_NEW_PASSWORD("기존 비밀번호와 같은 새 비밀번호 입니다.", 409),

    // AUTH CODE
    AUTH_CODE_NOT_FOUND("인증 코드를 찾을 수 없습니다.", 404),
    AUTH_CODE_NOT_MATCH("인증 코드가 일치 하지 않습니다.", 400),
    TOO_MANY_AUTH_CODE_REQUEST("인증 코드 확인 요청을 5번 초과 한 사용자 입니다.", 429),

    // AUTHENTICATION
    AUTHENTICATION_NOT_FOUND("인증되지 않은 사용자 입니다.", 404),
    TOO_MANY_AUTHENTICATION_REQUEST("인증 메세지 요청을 5번 초과 한 사용자 입니다.", 429),

    // MESSAGE
    MESSAGE_SEND_FAILED("coolsms 메세지 전송에 실패하였습니다.", 500),

    // TOKEN
    INVALID_TOKEN("유효하지 않은 토큰입니다.", 401),
    INVALID_TOKEN_TYPE("유효하지 않은 토큰 타입 입니다.", 401),
    EXPIRED_ACCESS_TOKEN("만료된 accessToken 입니다.", 401),
    EXPIRED_REFRESH_TOKEN("만료된 refreshToken 입니다.", 401),

    // QRCODE
    INVALID_QRCODE_UUID("유효하지 않은 QR UUID 입니다.", 400),

    // SSE
    INVALID_SSE_CONNECTION("유효하지 않은 sse 커넥션 입니다.", 400),

    // CROWDFUNDING
    CROWDFUNDING_NOT_FOUND("크라우드 펀딩을 찾을 수 없습니다.", 404),
    NOT_MY_CROWDFUNDING("현재 사용자가 작성한 크라우드 펀딩이 아닙니다.", 400),

    // FUNDING
    FUNDING_NOT_FOUND("펀딩을 찾을 수 없습니다.", 404),

    // REWARD
    REWARD_NOT_FOUND("리워드를 찾을 수 없습니다.", 404),

    // PAY
    FAILED_PAY_CONFIRM("결제 정보 검증에 실패하였습니다.", 400),
    FAILED_PAY_CANCEL("결제 취소에 실패하였습니다.", 400),
    RECEIPT_ID_NOT_FOUND("존재하지 않은 receiptId 입니다.", 404),

}