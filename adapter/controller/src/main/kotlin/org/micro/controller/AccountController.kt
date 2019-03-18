package org.micro.controller

import io.vertx.core.http.HttpServerResponse
import io.vertx.core.json.JsonObject
import org.micro.vm.CreateAccountVM
import org.micro.scenario.CreateAccount
import io.vertx.ext.web.RoutingContext
import org.slf4j.LoggerFactory

import java.util.Objects.isNull

class AccountController(private val createAccount: CreateAccount) {

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
