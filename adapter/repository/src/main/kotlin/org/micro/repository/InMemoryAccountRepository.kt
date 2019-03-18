package org.micro.repository


import org.micro.domain.entity.Account
import org.micro.domain.entity.Order
import org.micro.domain.port.AccountRepository

import java.util.ArrayList
import java.util.HashMap
import java.util.Optional

class InMemoryAccountRepository : AccountRepository {

    private val inMemoryDb = HashMap<String, Account>()

    override fun create(account: Account): Account {
        inMemoryDb[account.id] = account
        return account
    }

    override fun findByName(name: String): Optional<Account> {
        return inMemoryDb.values.stream()
                .filter { (_, name1) -> name1 == name }
                .findAny()
    }

    override fun findById(id: String): Optional<Account> {
        return inMemoryDb.values.stream()
                .filter { (id1) -> id1 == id }
                .findAny()
    }

    override fun findAll(): List<Account> {
        return ArrayList(inMemoryDb.values)
    }

    override fun update(accountId: String, btcPrice: Float): Optional<Account> {
        return findById(accountId)
                .map { account ->
                    val usd = account.usd
                    if (usd > 0) {
                        val btc = account.usd / btcPrice
                        account.btc += btc
                        account.usd += usd
                    }
                    account
                }

    }
}
