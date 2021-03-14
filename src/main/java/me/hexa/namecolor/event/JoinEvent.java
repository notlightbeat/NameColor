package me.hexa.namecolor.event;

import me.hexa.namecolor.NameColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
        if (NameColor.getPlugin().getConfig().getString(event.getPlayer().getName()) != null) {
            player.setDisplayName(NameColor.getPlugin().getConfig().getString(player.getName()));
            player.setPlayerListName(NameColor.getPlugin().getConfig().getString(player.getName()));
        }
	}
	
}
