package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.format
import com.perkelle.dev.envoys.utils.getData
import org.bukkit.entity.Player

class CreateCommand: ICommand {

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.create")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        val currentLocations by lazy {
            if(getData().contains("locations")) getData().getStringList("locations")
            else mutableListOf()
        }

        val id by lazy {
            if(currentLocations.isEmpty()) 1
            else currentLocations.map { it.split(";")[0].toInt() }.max()!! + 1
        }

        currentLocations.add("$id;${p.world.name};${p.location.blockX};${p.location.blockY};${p.location.blockZ}")
        getData().set("locations", currentLocations)

        p.sendMessage(format("An envoy has been created at your current location"))
    }

    override fun getName() = "create"

    override fun getInfo() = "Creates an envoy at your current location"

    override fun hasArgs() = false
}