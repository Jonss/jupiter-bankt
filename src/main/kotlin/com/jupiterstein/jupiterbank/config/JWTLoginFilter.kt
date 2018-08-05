package com.jupiterstein.jupiterbank.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTLoginFilter: AbstractAuthenticationProcessingFilter {


    constructor(url: String, authManager: AuthenticationManager) : super(AntPathRequestMatcher(url)) {
        setAuthenticationManager { authManager  -> authManager }
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication {

        val accountCredentials = ObjectMapper().readValue(request.inputStream, AccountCredentials::class.java)

        return authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                accountCredentials.username,
                accountCredentials.password,
                emptyList()
        ))
    }


    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        TokenAuthenticationService.addAuthentication(response, authResult.name)
    }

}
