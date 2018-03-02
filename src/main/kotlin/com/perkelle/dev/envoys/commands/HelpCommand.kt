package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.format
import org.bukkit.entity.Player

class HelpCommand: ICommand {

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.help")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        p.sendMessage(format("-= Envoys Commands =-"))
        CommandManager.commands.forEach {
            p.sendMessage(format("/envoys ${it.getName()} - ${it.getInfo()}"))
        }
    }

    override fun getName() = "help"

    override fun getInfo() = "Displays a list of commands"

    override fun hasArgs() = false
}