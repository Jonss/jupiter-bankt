package com.jupiterstein.jupiterbank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.ResponseBody



@SpringBootApplication
@RestController
class JupiterBankApplication {

    @RequestMapping("/hello")
    fun hello() = "Hello Júpiter!"


    @RequestMapping("/users")
    @ResponseBody
    fun getUsers(): String {
        return "{\"users\":[{\"name\":\"Jupiter\", \"country\":\"Brazil\"}," + "{\"name\":\"Stein\",\"country\":\"Lietchtenstein\"}]}"
    }
}

fun main(args: Array<String>) {
    runApplication<JupiterBankApplication>(*args)
}
