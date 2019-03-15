package org.micro.domain.entity

data class Account(val id: String, val name: String, val usd: Float, val btc: Float)

data class Order(val orderId: String, val accountId: String, val priceLimit: Float, val finished: Boolean)
