package com.jupiterstein.jupiterbank.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import java.util.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class TokenAuthenticationService {

    companion object {

        val EXPIRATION_TIME: Long = 860000000
        val SECRET = "MySecret"
        val TOKEN_PREFIX = "Bearer"
        val HEADER_STRING = "Authorization"

        fun addAuthentication(httpServletResponse: HttpServletResponse, username: String) {
            val jwt = Jwts.builder()
                    .setSubject(username)
                    .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SECRET)
                    .compact()

            httpServletResponse.addHeader(HEADER_STRING, "$TOKEN_PREFIX $jwt")
        }

        fun getAuthentication(httpServletRequest: HttpServletRequest): Authentication? {
            val token = httpServletRequest.getHeader(HEADER_STRING)

            if (token != null) {
                val user = Jwts.parser()
                        .setSigningKey(SECRET)
                        .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                        .body.subject

                println(user)

                if (user != null) {
                    return UsernamePasswordAuthenticationToken(user, null, emptyList())
                }
            }
            return null
        }
    }
}