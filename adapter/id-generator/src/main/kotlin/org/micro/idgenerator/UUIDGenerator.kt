package org.micro.idgenerator

import org.micro.domain.port.IdGenerator

import java.util.UUID

class UUIDGenerator : IdGenerator {

    override fun generate(): String {
        return UUID.randomUUID().toString()
    }
}
