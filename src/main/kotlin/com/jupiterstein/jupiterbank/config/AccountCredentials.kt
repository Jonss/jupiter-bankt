package com.jupiterstein.jupiterbank.config

import java.util.*
import javax.persistence.*

@Entity
@Table(name = "account_credentials")
data class AccountCredentials(
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     val id: Long = 0,
     val uuid: String = UUID.randomUUID().toString(),
     val username: String? = null,
     val password: String? = null
)
