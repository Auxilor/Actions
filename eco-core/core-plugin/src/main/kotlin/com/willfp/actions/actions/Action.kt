package com.willfp.actions.actions

import com.willfp.actions.plugin
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.integrations.placeholder.PlaceholderManager
import com.willfp.eco.core.placeholder.PlayerPlaceholder
import com.willfp.eco.core.registry.Registrable
import com.willfp.libreforge.EntityDispatcher
import com.willfp.libreforge.Holder
import com.willfp.libreforge.SimpleProvidedHolder
import com.willfp.libreforge.ViolationContext
import com.willfp.libreforge.conditions.Conditions
import com.willfp.libreforge.effects.Effects
import java.util.Objects

class Action(
    id: String,
    config: Config
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
        val rawID = id

        PlaceholderManager.registerPlaceholder(
            PlayerPlaceholder(
                plugin,
                "${rawID}_is_met"
            ) { player ->
                // Resolve the current action rather than capturing this instance, so the
                // placeholder reflects the config after a reload.
                val action = Actions.getByID(rawID) ?: return@PlayerPlaceholder "0"

                // Disabled actions never dispatch their effects, so they are never met.
                if (!action.enabled) {
                    return@PlayerPlaceholder "0"
                }

                // Use the same provided holder as the effect dispatch, so conditions see
                // the action itself rather than a blank holder.
                val met = action.conditions.areMet(
                    EntityDispatcher(player),
                    SimpleProvidedHolder(action)
                )
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
