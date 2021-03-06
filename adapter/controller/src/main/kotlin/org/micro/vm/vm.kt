package org.micro.vm

data class CreateAccountVM(val name: String, val usd_balance: Float)

data class UpdateAccountVM(
  val accountId: String,
  val btcPrice: Float
)
