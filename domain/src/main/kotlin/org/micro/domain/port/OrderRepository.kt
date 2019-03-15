package org.micro.domain.port

import org.micro.domain.entity.Order
import java.util.*

interface OrderRepository {

  fun create(order: Order): Order

  fun findById(orderId: String): Optional<Order>

  fun finish(orderId: String, btcPrice: Float?): Optional<Order>

  fun findByAccountId(accountId: String): Optional<Order>

  fun findAll(): List<Order>

}
