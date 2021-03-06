package net.ecoporium.webhooks.command

import dev.triumphteam.cmd.bukkit.annotation.Permission
import dev.triumphteam.cmd.core.BaseCommand
import dev.triumphteam.cmd.core.annotation.Command
import dev.triumphteam.cmd.core.annotation.SubCommand
import net.ecoporium.webhooks.SpartanWebhooks
import net.ecoporium.webhooks.listener.ViolationHandler
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.CommandSender

@Command("webhooks")
class WebhookCommand(private val plugin: SpartanWebhooks, private val handler: ViolationHandler) : BaseCommand() {

    @SubCommand("reload")
    @Permission("spartan-webhooks.commands.reload")
    fun reload(sender: CommandSender) {
        plugin.reloadConfigurations()
        sender.sendMessage(Component.text("Reloaded configuration files.", NamedTextColor.GREEN))
    }

}