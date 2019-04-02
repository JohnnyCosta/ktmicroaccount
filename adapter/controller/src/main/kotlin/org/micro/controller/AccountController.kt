package org.micro.controller

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import org.micro.vm.CreateAccountVM
import org.micro.scenario.CreateAccount
import io.vertx.ext.web.RoutingContext
import org.micro.scenario.FindAccount
import org.micro.scenario.UpdateAccount
import org.micro.utils.JsonCollectors
import org.micro.vm.UpdateAccountVM
import org.slf4j.LoggerFactory

import java.util.Objects.isNull

class AccountController(private val createAccount: CreateAccount, private val findAccount: FindAccount,
                        private val updateAccount: UpdateAccount) {

    fun createAccount(routingContext: RoutingContext) {
        log.info("Request to create account")
        val response = routingContext.response()
        val body = routingContext.body
        if (isNull(body)) {
            sendError(400, response)
        } else {
            val createAccountVM = body.toJsonObject().mapTo<CreateAccountVM>(CreateAccountVM::class.java)
            val account = createAccount.create(createAccountVM.name, createAccountVM.usd_balance)
            val result = JsonObject.mapFrom(account)
            sendSuccess(result, response)
        }
    }

  fun findAccount(routingContext: RoutingContext) {
    log.info("Request to find account")
    val response = routingContext.response()
    val id = routingContext.request().getParam("id")
    if (isNull(id)) {
      sendError(400, response)
    } else {
      val account = findAccount.fetchAccountDetails(id)
      if (account!=null) {
        val result = JsonObject.mapFrom(account)
        sendSuccess(result, response)
      } else {
        sendError(404, response)
      }
    }
  }

  fun findAllAccounts(routingContext: RoutingContext) {
    log.info("Request to find all accounts")
    val response = routingContext.response()
    val accounts = findAccount.findAllAccounts()
    val result = JsonArray(accounts.map { JsonObject.mapFrom(it) })

    response
      .putHeader("content-type", "application/json")
      .end(result.encodePrettily())
  }

  fun updateAccount(routingContext: RoutingContext) {
    log.info("Request to update account")
    val response = routingContext.response()
    val body = routingContext.body
    if (isNull(body)) {
      sendError(400, response)
    } else {
      val updateAccountVM = body.toJsonObject().mapTo(UpdateAccountVM::class.java)
      val account = updateAccount.update(updateAccountVM.accountId, updateAccountVM.btcPrice)
      if (account == null) {
        sendError(400, response)
      } else {
        val result = JsonObject.mapFrom(account)
        sendSuccess(result, response)
      }
    }
  }

    private fun sendError(statusCode: Int, response: HttpServerResponse) {
        response
                .putHeader("content-type", "application/json")
                .setStatusCode(statusCode)
                .end()
    }

    private fun sendSuccess(body: JsonObject, response: HttpServerResponse) {
        response
                .putHeader("content-type", "application/json")
                .end(body.encodePrettily())
    }

  companion object {
    val log = LoggerFactory.getLogger(AccountController::class.java)
  }

}
