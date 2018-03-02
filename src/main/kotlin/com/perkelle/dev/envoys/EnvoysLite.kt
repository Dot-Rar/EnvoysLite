package com.perkelle.dev.envoys

import com.perkelle.dev.envoys.commands.CommandManager
import com.perkelle.dev.envoys.utils.FileManager
import com.perkelle.dev.envoys.utils.RefillManager
import org.bukkit.plugin.java.JavaPlugin

class EnvoysLite: JavaPlugin() {

    private val fm = FileManager()
    private val rm = RefillManager()

    override fun onEnable() {
        fm.setup(this)

        getCommand("envoy").executor = CommandManager()

        server.scheduler.runTaskTimer(this, {
            rm.refill()
        }, FileManager.config.getLong("refill-interval-seconds", 1800L) * 20,
        FileManager.config.getLong("refill-interval-seconds", 1800L) * 20)
    }

    override fun onDisable() {
        fm.saveData()
    }
}