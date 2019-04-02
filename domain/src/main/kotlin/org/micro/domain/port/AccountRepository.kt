package org.micro.domain.port

import org.micro.domain.entity.Account
import java.util.*

interface AccountRepository {

  fun create(account: Account): Account

  fun findByName(name: String): Account?

  fun findById(id: String): Account?

  fun findAll(): List<Account>

  fun update(accountId: String, btcPrice: Float): Account?
}
