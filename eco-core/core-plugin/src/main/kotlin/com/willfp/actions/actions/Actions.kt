package com.willfp.actions.actions

import com.google.common.collect.ImmutableList
import com.willfp.actions.ActionsPlugin
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.eco.core.registry.Registry
import com.willfp.libreforge.loader.LibreforgePlugin
import com.willfp.libreforge.loader.configs.ConfigCategory

object Actions : ConfigCategory("action", "actions") {
    private val registry = Registry<Action>()

    /**
     * Get all registered [Action]s.
     *
     * @return A list of all [Action]s.
     */
    @JvmStatic
    fun values(): List<Action> {
        return ImmutableList.copyOf(registry.values())
    }

    /**
     * Get [Action] matching ID.
     *
     * @param name The name to search for.
     * @return The matching [Action], or null if not found.
     */
    @JvmStatic
    fun getByID(name: String): Action? {
        return registry[name]
    }

    override fun clear(plugin: LibreforgePlugin) {
        registry.clear()
    }

    override fun acceptConfig(plugin: LibreforgePlugin, id: String, config: Config) {
        registry.register(Action(plugin as ActionsPlugin, id, config))
    }
}
