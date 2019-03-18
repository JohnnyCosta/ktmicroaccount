package org.micro.domain.port

import org.micro.domain.entity.Account
import java.util.*

interface AccountRepository {

  fun create(account: Account): Account

  fun findByName(name: String): Optional<Account>

  fun findById(id: String): Optional<Account>

  fun findAll(): List<Account>

  fun update(accountId: String, btcPrice: Float): Optional<Account>
}
