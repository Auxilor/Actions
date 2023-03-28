package com.willfp.actions

import com.willfp.actions.actions.Actions
import com.willfp.actions.commands.CommandActions
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.libreforge.SimpleProvidedHolder
import com.willfp.libreforge.loader.LibreforgePlugin
import com.willfp.libreforge.loader.configs.ConfigCategory
import com.willfp.libreforge.registerHolderProvider

class ActionsPlugin : LibreforgePlugin() {
    init {
        instance = this
    }

    override fun loadConfigCategories(): List<ConfigCategory> {
        return listOf(
            Actions
        )
    }

    override fun handleEnable() {
        registerHolderProvider {
            Actions.values()
                .filter { it.enabled }
                .map { SimpleProvidedHolder(it) }
        }
    }

    override fun loadPluginCommands(): List<PluginCommand> {
        return listOf(
            CommandActions(this)
        )
    }

    companion object {
        @JvmStatic
        lateinit var instance: ActionsPlugin
    }
}

