package com.perkelle.dev.envoys.utils

import com.google.common.io.ByteStreams
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.Plugin
import java.io.File

fun getConfig() = FileManager.config
fun getData() = FileManager.data

class FileManager {

    companion object {
        private lateinit var cfile: File
        private lateinit var dfile: File

        lateinit var config: YamlConfiguration
        lateinit var data: YamlConfiguration
    }

    fun setup(pl: Plugin) {
        if(!pl.dataFolder.exists()) pl.dataFolder.mkdir()

        cfile = File(pl.dataFolder, "config.yml")
        dfile = File(pl.dataFolder, "data.yml")

        if(!cfile.exists()) {
            cfile.createNewFile()
            ByteStreams.copy(pl.getResource("config.yml"), cfile.outputStream())
        }
        if(!dfile.exists()) dfile.createNewFile()

        config = YamlConfiguration.loadConfiguration(cfile)
        data = YamlConfiguration.loadConfiguration(dfile)
    }

    fun saveData() = data.save(dfile)

    fun reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cfile)
    }
}