package com.willfp.actions.commands

import com.willfp.actions.plugin
import com.willfp.eco.core.command.impl.PluginCommand
import org.bukkit.command.CommandSender

object CommandActions : PluginCommand(
    plugin,
    "actions",
    "actions.command.actions",
    false
) {
    init {
        this.addSubcommand(CommandReload)
    }

    override fun onExecute(sender: CommandSender, args: List<String>) {
        sender.sendMessage(
            plugin.langYml.getMessage("invalid-command")
        )
    }
}
