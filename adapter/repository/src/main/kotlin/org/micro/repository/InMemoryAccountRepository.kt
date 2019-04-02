package org.micro.repository


import org.micro.domain.entity.Account
import org.micro.domain.port.AccountRepository
import java.util.*

class InMemoryAccountRepository : AccountRepository {

  private val inMemoryDb = HashMap<String, Account>()

  override fun create(account: Account): Account {
    inMemoryDb[account.id] = account
    return account
  }

  override fun findByName(name: String): Account? {
    return inMemoryDb.values.firstOrNull { it.name == name }
  }

  override fun findById(id: String): Account? {
    return inMemoryDb[id]

  }

  override fun findAll(): List<Account> {
    return ArrayList(inMemoryDb.values)
  }

  override fun update(accountId: String, btcPrice: Float): Account? {
    return findById(accountId)?.also { ac ->
      val usd = ac.usd
      if (usd > 0) {
        val btc = ac.usd / btcPrice
        ac.btc += btc
        ac.usd += usd
      }
    }
  }
}
