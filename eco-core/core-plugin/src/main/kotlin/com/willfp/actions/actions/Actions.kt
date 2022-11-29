package com.willfp.actions.actions

import com.google.common.collect.BiMap
import com.google.common.collect.HashBiMap
import com.google.common.collect.ImmutableList
import com.willfp.actions.ActionsPlugin
import com.willfp.eco.core.config.updating.ConfigUpdater

object Actions {
    private val BY_ID: BiMap<String, Action> = HashBiMap.create()

    /**
     * Get all registered [Action]s.
     *
     * @return A list of all [Action]s.
     */
    @JvmStatic
    fun values(): List<Action> {
        return ImmutableList.copyOf(BY_ID.values)
    }

    /**
     * Get [Action] matching ID.
     *
     * @param name The name to search for.
     * @return The matching [Action], or null if not found.
     */
    @JvmStatic
    fun getByID(name: String): Action? {
        return BY_ID[name]
    }

    /**
     * Update all [Action]s.
     *
     * @param plugin Instance of Actions.
     */
    @ConfigUpdater
    @JvmStatic
    fun update(plugin: ActionsPlugin) {
        for (action in values()) {
            removeAction(action)
        }

        for ((id, actionConfig) in plugin.fetchConfigs("actions")) {
            addNewAction(Action(plugin, id, actionConfig))
        }
    }

    /**
     * Add new [Action] to Actions.
     *
     * @param action The [Action] to add.
     */
    @JvmStatic
    fun addNewAction(action: Action) {
        BY_ID.remove(action.id)
        BY_ID[action.id] = action
    }

    /**
     * Remove [Action] from Actions.
     *
     * @param action The [Action] to remove.
     */
    @JvmStatic
    fun removeAction(action: Action) {
        BY_ID.remove(action.id)
    }
}
