package com.willfp.actions.actions

import com.willfp.actions.plugin
import wiki.ecohub.config.Config
import wiki.ecohub.config.ViolationContext
import wiki.ecohub.core.registry.Registrable
import wiki.ecohub.rules.condition.Conditions
import wiki.ecohub.rules.effect.Effects
import wiki.ecohub.rules.holder.EntityDispatcher
import wiki.ecohub.rules.holder.Holder
import wiki.ecohub.rules.holder.SimpleProvidedHolder
import wiki.ecohub.ui.placeholder.PlaceholderManager
import wiki.ecohub.ui.placeholder.type.PlayerPlaceholder
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
