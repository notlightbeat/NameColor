package me.hexa.namecolor.gui;

import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public interface ISelectionInventory extends InventoryHolder {

    public void initializeItems();

	public void onGUIClick(Player player, int slot, ItemStack item);

    
}