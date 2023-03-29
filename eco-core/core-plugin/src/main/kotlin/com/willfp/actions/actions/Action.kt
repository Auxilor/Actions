package com.willfp.actions.actions

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.integrations.placeholder.PlaceholderManager
import com.willfp.eco.core.placeholder.PlayerPlaceholder
import com.willfp.eco.core.registry.Registrable
import com.willfp.libreforge.EmptyProvidedHolder
import com.willfp.libreforge.Holder
import com.willfp.libreforge.ViolationContext
import com.willfp.libreforge.conditions.Conditions
import com.willfp.libreforge.effects.Effects
import java.util.Objects

class Action(
    plugin: EcoPlugin,
    id: String,
    val config: Config
) : Holder, Registrable {
    val enabled = config.getBool("enabled")

    override val effects = Effects.compile(
        config.getSubsections("effects"),
        ViolationContext(plugin, "Action $id")
    )

    override val conditions = Conditions.compile(
        config.getSubsections("conditions"),
        ViolationContext(plugin, "Action $id")
    )

    override val id = plugin.createNamespacedKey(id)

    init {
        PlaceholderManager.registerPlaceholder(
            PlayerPlaceholder(
                plugin,
                "${id}_is_met"
            ) { player ->
                val met = conditions.all { it.isMet(player, EmptyProvidedHolder) }
                if (met) "1" else "0"
            }
        )
    }

    override fun getID(): String {
        return this.id.key
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
