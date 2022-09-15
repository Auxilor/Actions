package com.willfp.actions

import com.willfp.actions.actions.Actions
import com.willfp.actions.commands.CommandActions
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.libreforge.LibReforgePlugin
import org.bukkit.event.Listener

class ActionsPlugin : LibReforgePlugin() {
    init {
        instance = this
        registerHolderProvider { Actions.values().filter { it.enabled } }
    }

    override fun handleEnableAdditional() {
        this.copyConfigs("actions")
    }

    override fun loadListeners(): List<Listener> {
        return emptyList()
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

