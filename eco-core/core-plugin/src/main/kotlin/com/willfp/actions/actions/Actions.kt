package com.willfp.actions.actions

import com.google.common.collect.ImmutableList
import com.willfp.actions.BuildConfig
import wiki.ecohub.config.Config
import wiki.ecohub.config.category.NativeConfigCategory
import wiki.ecohub.core.plugin.EcoPlugin
import wiki.ecohub.core.registry.Registry

object Actions : NativeConfigCategory("actions", "action") {
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

    override fun clear(plugin: EcoPlugin) {
        registry.clear()
    }

    override fun acceptConfig(plugin: EcoPlugin, id: String, config: Config) {
        if (BuildConfig.FREE_VERSION && registry.values().size >= 5) {
            plugin.logger.warning("The free version of Actions only supports 5 actions.")
            plugin.logger.warning("Purchase the full version of Actions to remove this restriction!")
            return
        }
        registry.register(Action(id, config))
    }
}
