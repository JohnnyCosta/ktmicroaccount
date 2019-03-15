package org.micro.scenario.validator

import org.micro.domain.exception.AccountValidationException
import org.micro.domain.entity.Account

object AccountValidator {

    fun validateCreateUser(account: Account) {
        if (account.usd<0) throw AccountValidationException("Account cannot be created with negative usd balance")
    }
}
