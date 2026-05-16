package com.willfp.actions

import com.willfp.actions.actions.Actions
import com.willfp.actions.commands.CommandActions
import com.willfp.eco.core.bstats.EcoMetricsChart
import com.willfp.eco.core.command.impl.PluginCommand
import com.willfp.libreforge.SimpleProvidedHolder
import com.willfp.libreforge.loader.LibreforgePlugin
import com.willfp.libreforge.loader.configs.ConfigCategory
import com.willfp.libreforge.registerGenericHolderProvider

internal lateinit var plugin: ActionsPlugin
    private set

class ActionsPlugin : LibreforgePlugin() {
    init {
        plugin = this
    }

    override fun loadConfigCategories(): List<ConfigCategory> {
        return listOf(
            Actions
        )
    }

    override fun handleEnable() {
        registerGenericHolderProvider {
            Actions.values()
                .filter { it.enabled }
                .map { SimpleProvidedHolder(it) }
        }
    }

    override fun loadPluginCommands(): List<PluginCommand> {
        return listOf(
            CommandActions
        )
    }

    override fun getCustomCharts() = listOf(
        EcoMetricsChart.SingleLine("total_actions") { Actions.values().size },
        EcoMetricsChart.SingleLine("enabled_actions") { Actions.values().count { it.enabled } },
        EcoMetricsChart.SimplePie("cooldown_message_enabled") {
            if (configYml.getBool("cooldown.message-enabled")) "enabled" else "disabled"
        },
        EcoMetricsChart.SimplePie("cooldown_actionbar") {
            if (configYml.getBool("cooldown.in-actionbar")) "enabled" else "disabled"
        },
        EcoMetricsChart.SimplePie("cooldown_sound") {
            if (configYml.getBool("cooldown.sound")) "enabled" else "disabled"
        },
        EcoMetricsChart.SimplePie("telekinesis_on_players") {
            if (configYml.getBool("effects.telekinesis.on-players")) "enabled" else "disabled"
        },
        EcoMetricsChart.SimplePie("use_setblock_break") {
            if (configYml.getBool("effects.use-setblock-break")) "enabled" else "disabled"
        }
    )
}

