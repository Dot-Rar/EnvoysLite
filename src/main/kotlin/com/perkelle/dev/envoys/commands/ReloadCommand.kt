package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.FileManager
import com.perkelle.dev.envoys.utils.format
import org.bukkit.entity.Player

class ReloadCommand: ICommand {

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.reload")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        FileManager().reloadConfig()

        p.sendMessage(format("Reloaded config"))
    }

    override fun getName() = "reload"

    override fun getInfo() = "Reloads the config from disk"

    override fun hasArgs() = false
}