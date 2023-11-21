package com.willfp.actions.commands

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.PluginCommand
import org.bukkit.command.CommandSender

class CommandActions(plugin: EcoPlugin) : PluginCommand(
    plugin,
    "actions",
    "actions.command.actions",
    false
) {
    init {
        this.addSubcommand(CommandReload(plugin))
    }

    override fun onExecute(sender: CommandSender, args: List<String>) {
        sender.sendMessage(
            plugin.langYml.getMessage("invalid-command")
        )
    }
}
