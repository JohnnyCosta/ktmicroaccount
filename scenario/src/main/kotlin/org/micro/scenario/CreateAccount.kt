package org.micro.scenario


import org.micro.domain.entity.Account
import org.micro.domain.exception.AccountAlreadyExistsException
import org.micro.domain.port.AccountRepository
import org.micro.domain.port.IdGenerator
import org.micro.scenario.validator.AccountValidator

class CreateAccount(private val repository: AccountRepository, private val idGenerator: IdGenerator) {

  fun create(name: String, usd_balance: Float): Account {
    val id = idGenerator.generate()
    val account = Account(id, name, usd_balance, 0f)

    AccountValidator.validateCreateUser(account)

    repository.findByName(name)?.let { throw AccountAlreadyExistsException("Account with name '$name' already exists") }

    repository.findById(id)?.let { throw AccountAlreadyExistsException("Account with id '$id' already exists") }

    return repository.create(account)
  }


}
