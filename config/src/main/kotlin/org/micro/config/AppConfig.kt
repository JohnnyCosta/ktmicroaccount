package org.micro.config

import org.micro.controller.AccountController
import org.micro.domain.port.AccountRepository
import org.micro.domain.port.IdGenerator
import org.micro.idgenerator.UUIDGenerator
import org.micro.repository.InMemoryAccountRepository
import org.micro.scenario.CreateAccount

class AppConfig {

    private val accountRepository = InMemoryAccountRepository()
    private val idGenerator = UUIDGenerator()
    private val createAccount = CreateAccount(accountRepository, idGenerator)
    val accountController = AccountController(createAccount)

}
