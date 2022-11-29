package com.willfp.actions.actions

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.integrations.placeholder.PlaceholderManager
import com.willfp.eco.core.placeholder.PlayerPlaceholder
import com.willfp.libreforge.Holder
import com.willfp.libreforge.conditions.Conditions
import com.willfp.libreforge.effects.Effects
import java.util.Objects

class Action(
    plugin: EcoPlugin,
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

    init {
        PlaceholderManager.registerPlaceholder(
            PlayerPlaceholder(
                plugin,
                "${id}_is_met"
            ) { player ->
                val met = conditions.all { it.isMet(player) }
                if (met) "1" else "0"
            }
        )
    }

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
