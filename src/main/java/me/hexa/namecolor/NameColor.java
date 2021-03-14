package me.hexa.namecolor;

import me.hexa.namecolor.command.NCCommand;
import me.hexa.namecolor.event.InventoryEvent;
import me.hexa.namecolor.event.JoinEvent;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class NameColor extends JavaPlugin {

	public void onEnable() {
		saveDefaultConfig();
		getCommand("nc").setExecutor(new NCCommand());
		
		Bukkit.getPluginManager().registerEvents(new InventoryEvent(), this);
        Bukkit.getPluginManager().registerEvents(new JoinEvent(), this);
		
		getLogger().info("NameColor enabled");
	}

	public void onDisable() {
		getLogger().info("NameColor disabled");
	}

	public static NameColor getPlugin() {
		return getPlugin(NameColor.class);
	}
	
}

