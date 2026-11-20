package com.willfp.actions.commands

import com.willfp.actions.ActionsPlugin
import wiki.ecohub.core.command.command

internal fun ActionsPlugin.actionsCommand() = command(name = "actions", permission = "actions.command.actions") {
    reloadCommand()
}
