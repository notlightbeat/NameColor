package me.hexa.namecolor.event;

import me.hexa.namecolor.gui.ISelectionInventory;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class InventoryEvent implements Listener {
	
	@EventHandler
    public void onInventoryClick(final InventoryClickEvent event) {
		if (event.getInventory().getHolder() instanceof ISelectionInventory) {
			event.setCancelled(true);
			ISelectionInventory gui = (ISelectionInventory) event.getInventory().getHolder();
        	if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
            gui.onGUIClick((Player)event.getWhoClicked(), event.getRawSlot(), event.getCurrentItem());
		}
    }

    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory().getHolder() instanceof ISelectionInventory) {
          e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryClick(final InventoryPickupItemEvent e) {
        if (e.getInventory().getHolder() instanceof ISelectionInventory) {
            e.setCancelled(true);
        }
    }
}
