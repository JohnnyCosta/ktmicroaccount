package org.micro.scenario

import org.micro.domain.entity.Account
import org.micro.domain.port.AccountRepository
import java.util.Optional

class FindAccount(private val repository: AccountRepository) {

    fun fetchAccountDetails(account_id: String): Account? {
        return repository.findById(account_id)
    }

    fun findAllAccounts(): List<Account> {
        return repository.findAll()
    }


}
