package com.tw.yys.backendrealworld.domain.common

import java.util.UUID

data class EntityId(
    val id: UUID
) {
    companion object {
        fun newId() = EntityId(UUID.randomUUID())
    }

    override fun toString(): String {
        return id.toString()
    }
}
