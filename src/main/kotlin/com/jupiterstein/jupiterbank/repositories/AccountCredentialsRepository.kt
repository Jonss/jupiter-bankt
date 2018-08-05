package com.jupiterstein.jupiterbank.repositories

import com.jupiterstein.jupiterbank.config.AccountCredentials
import org.springframework.data.jpa.repository.JpaRepository

interface AccountCredentialsRepository: JpaRepository<AccountCredentials, Long>
