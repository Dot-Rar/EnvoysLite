package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.format
import com.perkelle.dev.envoys.utils.getData
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class ListCommand: ICommand {

    override fun onCommand(p: Player, args: Array<String>) {
        if(!p.hasPermission("envoys.list")) {
            p.sendMessage(format("You do not have permission for this command"))
            return
        }

        p.sendMessage(format("All envoy locations:"))

        val locations = getData().getStringList("locations") ?: listOf()
        locations.forEach {
            val split = it.split(";")

            val id = split[0]
            val world = Bukkit.getWorld(split[1])
            val x = split[2].toInt()
            val y = split[3].toInt()
            val z = split[4].toInt()

            p.sendMessage(format("ID: $id, World: ${world.name}, X: $x, Y: $y, Z: $z"))
        }
    }

    override fun getName() = "list"

    override fun getInfo() = "Provides a list of all envoys, their IDs and locations"

    override fun hasArgs() = false
}