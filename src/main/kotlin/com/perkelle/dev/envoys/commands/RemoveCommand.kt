package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.format
import com.perkelle.dev.envoys.utils.getData
import com.perkelle.dev.envoys.utils.isInt
import org.bukkit.entity.Player

class RemoveCommand: ICommand {

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.remove")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        if(args.isEmpty() || !args[0].isInt()) {
            p.sendMessage(format("You need to specify an ID (see /envoys list for IDs)"))
            return
        }

        val id = args[0].toInt()
        getData().set("locations", (getData().getStringList("locations") ?: mutableListOf<String>()).removeIf { it.split(";")[0] == id.toString() })

        p.sendMessage(format("Removed envoy with ID $id"))
    }

    override fun getName() = "remove"

    override fun getInfo() = "Removed an envoy with a given ID (/envoys list)"

    override fun hasArgs() = true
}