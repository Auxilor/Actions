package com.willfp.actions.actions

import com.willfp.eco.core.config.interfaces.Config
import com.willfp.libreforge.Holder
import com.willfp.libreforge.conditions.Conditions
import com.willfp.libreforge.effects.Effects
import java.util.*

class Action(
    override val id: String,
    val config: Config
) : Holder {
    val enabled = config.getBool("enabled")

    override val effects = Effects.compile(
        config.getSubsections("effects"),
        "Action $id"
    )

    override val conditions = Conditions.compile(
        config.getSubsections("conditions"),
        "Action $id"
    )

    override fun equals(other: Any?): Boolean {
        if (other !is Action) {
            return false
        }

        return this.id == other.id
    }

    override fun hashCode(): Int {
        return Objects.hash(this.id)
    }
}
