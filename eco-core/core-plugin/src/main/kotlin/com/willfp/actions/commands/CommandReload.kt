package com.willfp.actions.commands

import com.willfp.actions.actions.Actions
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import wiki.ecohub.core.command.CommandBuilder
import wiki.ecohub.core.platform.runGlobal
import wiki.ecohub.core.stdlib.toNiceString
import wiki.ecohub.ui.text.StringUtils

/**
 * Reloading rebuilds shared registries, so it runs on the global region.
 */
internal fun CommandBuilder<CommandSender>.reloadCommand() = subcommand(
    name = "reload",
    permission = "actions.command.reload"
) {
    executes {
        Bukkit.getServer().runGlobal {
            sender.sendMessage(
                plugin.langYml.getMessage("reloaded", StringUtils.FormatOption.WITHOUT_PLACEHOLDERS)
                    .replace(oldValue = "%time%", newValue = plugin.reloadWithTime().toNiceString())
                    .replace(oldValue = "%count%", newValue = Actions.values().size.toString())
            )
        }
    }
}
