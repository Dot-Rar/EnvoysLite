package com.perkelle.dev.envoys.commands

import com.perkelle.dev.envoys.utils.format
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CommandManager: CommandExecutor {

    companion object {
        val commands = setOf(
                CreateCommand(),
                HelpCommand(),
                ListCommand(),
                RefillCommand(),
                ReloadCommand(),
                RemoveCommand()
        )
    }

    override fun onCommand(sender: CommandSender, cmd: Command, label: String, args: Array<String>): Boolean {
        if(cmd.name.equals("envoy", true)) {
            if(sender !is Player) {
                sender.sendMessage(format("Only players can use this command"))
                return true
            }

            if(args.isEmpty()) {
                sender.sendMessage(format("Invalid command. For a full list of commands, type /envoys help"))
                return true
            }

            val subcmd = commands.firstOrNull { it.getName().equals(args[0], true) }

            if(subcmd == null) {
                sender.sendMessage(format("Invalid command. For a full list of commands, type /envoys help"))
                return true
            }

            val subArgs by lazy {
                if(args.size < 2) arrayOf()
                else args.copyOfRange(1, args.size)
            }

            subcmd.onCommand(sender, subArgs)
        }
        return true
    }
}