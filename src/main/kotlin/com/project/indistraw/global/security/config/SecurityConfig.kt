package com.project.indistraw.global.security.config

import com.project.indistraw.global.security.handler.CustomAuthenticationEntryPoint
import com.project.indistraw.global.security.token.TokenParseAdapter
import com.project.indistraw.domain.account.domain.Authority
import com.project.indistraw.global.security.handler.CustomAccessDeniedHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtParserAdapter: TokenParseAdapter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors()
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .apply(FilterConfig(jwtParserAdapter))
        authorizeHttpRequests(http)
        exceptionHandling(http)
        return http.build()
    }

    private fun authorizeHttpRequests(http: HttpSecurity) {
        http.authorizeHttpRequests()
            .mvcMatchers("/api/v1/auth/**").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/qr-code/connect/{uuid}").permitAll()
            .mvcMatchers(HttpMethod.POST, "/api/v1/qr-code").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/qr-code/ping/{uuid}").permitAll()
            .mvcMatchers(HttpMethod.HEAD, "/api/v1/qr-code/check/{uuid}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)

            .mvcMatchers(HttpMethod.GET, "/api/v1/account/phone-number/{phoneNumber}").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/password").permitAll()
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/phone-number/{phoneNumber}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/address").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/account/info").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/info").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.DELETE, "/api/v1/account").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/account/actor/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/account/director/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/account/filmography").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)

            .mvcMatchers(HttpMethod.POST, "/api/v1/movie").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ADMIN.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/{movie_id}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.PATCH, "/api/v1/movie/{movie_id}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.DELETE, "/api/v1/movie/{movie_id}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/movie/history").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/history").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/actor").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/movie/actor").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/actor/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/director").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.POST, "/api/v1/movie/director").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/movie/director/{idx}").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)

            .mvcMatchers(HttpMethod.GET, "/api/v1/search").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)
            .mvcMatchers(HttpMethod.GET, "/api/v1/search/tag").hasAnyAuthority(Authority.ROLE_ACCOUNT.name, Authority.ROLE_ACCOUNT.name)

            .mvcMatchers(HttpMethod.POST, "/api/v1/file").permitAll()

            .mvcMatchers(HttpMethod.GET, "/").permitAll()
            .anyRequest().permitAll()
    }

    private fun exceptionHandling(http: HttpSecurity) {
        http.exceptionHandling()
            .authenticationEntryPoint(CustomAuthenticationEntryPoint())
            .accessDeniedHandler(CustomAccessDeniedHandler())
    }

}