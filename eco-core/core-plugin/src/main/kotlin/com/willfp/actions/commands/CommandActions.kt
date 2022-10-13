package com.willfp.actions.commands

import com.willfp.actions.actions.Actions
import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.libreforge.LibReforgePlugin
import com.willfp.libreforge.lrcdb.CommandExport
import com.willfp.libreforge.lrcdb.CommandImport
import com.willfp.libreforge.lrcdb.ExportableConfig
import org.bukkit.command.CommandSender

class CommandActions(plugin: LibReforgePlugin) : PluginCommand(plugin, "actions", "actions.command.actions", false) {
    init {
        this.addSubcommand(CommandReload(plugin))
            .addSubcommand(CommandImport("actions", plugin))
            .addSubcommand(CommandExport(plugin) {
                Actions.values().map {
                    ExportableConfig(
                        it.id,
                        it.config
                    )
                }
            })
    }

    override fun onExecute(sender: CommandSender, args: List<String>) {
        sender.sendMessage(
            plugin.langYml.getMessage("invalid-command")
        )
    }
}
