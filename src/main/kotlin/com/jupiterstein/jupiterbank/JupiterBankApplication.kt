package com.jupiterstein.jupiterbank

import com.jupiterstein.jupiterbank.config.AccountCredentials
import com.jupiterstein.jupiterbank.repositories.AccountCredentialsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@SpringBootApplication
@RestController
class JupiterBankApplication {

    @Autowired
    private lateinit var accountCredentialsRepository: AccountCredentialsRepository

    @RequestMapping("/hello")
    fun hello() = "Hello Júpiter!"


    @RequestMapping("/users")
    @ResponseBody
    fun getUsers(): String {
        return "{\"users\":[{\"name\":\"Jupiter\", \"country\":\"Brazil\"}," + "{\"name\":\"Stein\",\"country\":\"Lietchtenstein\"}]}"
    }

    @PostMapping("/signup")
    fun createUser(@RequestBody accountCredentials: AccountCredentials)
            = ResponseEntity.created(URI("")).body(accountCredentialsRepository.save(accountCredentials))

}

fun main(args: Array<String>) {
    runApplication<JupiterBankApplication>(*args)
}
