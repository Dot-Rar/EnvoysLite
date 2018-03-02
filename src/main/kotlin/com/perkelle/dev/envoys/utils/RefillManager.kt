package com.perkelle.dev.envoys.utils

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack
import org.bukkit.material.MaterialData
import java.util.concurrent.ThreadLocalRandom

class RefillManager {

    fun refill() {
        val locations = getData().getStringList("locations") ?: listOf()
        locations.forEach {
            val split = it.split(";")

            val world = Bukkit.getWorld(split[1])
            val x = split[2].toInt()
            val y = split[3].toInt()
            val z = split[4].toInt()

            val block = world.getBlockAt(x, y, z)
            block.type = Material.AIR
            block.type = Material.CHEST

            val chest = block.state as Chest

            for((item, chance) in getAllItems()) {
                if(chance > ThreadLocalRandom.current().nextDouble(101.0)) {
                    chest.inventory.setItem(ThreadLocalRandom.current().nextInt(27), item)
                }
            }
        }

        Bukkit.broadcastMessage(format("All envoys have been refilled"))
    }

    private fun getAllItems(): Map<ItemStack, Double> {
        val section = getConfig().getConfigurationSection("items")
        return section.getKeys(false).map {
            val item = section.getConfigurationSection(it)

            val id = item.getInt("id", 0)
            val chance = item.getDouble("chance", 0.0)

            val stack = ItemStack(Material.getMaterial(id))

            stack.data = MaterialData(item.getInt("data", 0))
            stack.amount = item.getInt("amount", 0)

            val meta = stack.itemMeta

            meta.displayName = ChatColor.translateAlternateColorCodes('&', item.getString("name", stack.itemMeta.displayName ?: stack.type.name.capitalize().replace('_', ' ')))
            meta.lore = item.getStringList("lore").map { ChatColor.translateAlternateColorCodes('&', it) }
            stack.itemMeta = meta

            item.getStringList("enchants").forEach { enchantRaw ->
                val enchant = enchantRaw.split(",")[0]
                val level = enchantRaw.split(",")[1].toInt()
                stack.addUnsafeEnchantment(Enchantment.getByName(enchant), level)
            }

            stack to chance
        }.toMap()
    }
}