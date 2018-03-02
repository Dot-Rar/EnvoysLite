package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.RefillManager
import com.perkelle.dev.envoys.utils.format
import org.bukkit.entity.Player

class RefillCommand: ICommand {

    val rm = RefillManager()

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.list")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        rm.refill()
        p.sendMessage(format("Refilling all envoys..."))
    }

    override fun getName() = "refill"

    override fun getInfo() = "Forces all envoys to refill"

    override fun hasArgs() = false
}