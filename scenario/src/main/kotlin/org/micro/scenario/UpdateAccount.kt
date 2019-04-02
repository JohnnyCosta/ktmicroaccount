package org.micro.scenario

import org.micro.domain.entity.Account
import org.micro.domain.port.AccountRepository

import java.util.Optional

class UpdateAccount(private val repository: AccountRepository) {

    fun update(accountId: String, btcPrice: Float): Account? {
        return repository.update(accountId, btcPrice)
    }

}
