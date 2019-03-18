package org.micro.domain.entity

data class Account(val id: String, val name: String, var usd: Float, var btc: Float)

data class Order(val orderId: String, val accountId: String, var priceLimit: Float, var finished: Boolean)
