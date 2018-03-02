package com.perkelle.dev.envoys.commands

import org.bukkit.entity.Player

interface ICommand {

    fun onCommand(p: Player, args: Array<String> = arrayOf())

    fun getName(): String

    fun getInfo(): String

    fun hasArgs(): Boolean
}