package org.micro.config

import org.micro.controller.AccountController
import org.micro.idgenerator.UUIDGenerator
import org.micro.repository.InMemoryAccountRepository
import org.micro.scenario.CreateAccount
import org.micro.scenario.FindAccount
import org.micro.scenario.UpdateAccount

class AppConfig {

  private val accountRepository = InMemoryAccountRepository()
  private val idGenerator = UUIDGenerator()
  private val createAccount = CreateAccount(accountRepository, idGenerator)
  private val findAccount = FindAccount(accountRepository)
  private val updateAccount = UpdateAccount(accountRepository)
  val accountController = AccountController(createAccount, findAccount, updateAccount)

}
